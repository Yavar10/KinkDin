from celery import shared_task
from .models import Player
from .utils import update_player_scores


@shared_task
def update_all_player_scores():
    players = Player.objects.filter(leetcode_username__isnull=False).exclude(leetcode_username='')
    updated_count = 0
    
    for player in players:
        try:
            update_player_scores(player)
            updated_count += 1
        except Exception as e:
            print(f"Failed to update scores for {player.user.username}: {str(e)}")
    
    return f"Updated scores for {updated_count} players"


@shared_task
def update_single_player_score(player_id):
    try:
        player = Player.objects.get(id=player_id)
        if player.leetcode_username:
            update_player_scores(player)
            return f"Updated scores for {player.user.username}"
        else:
            return f"No LeetCode username for {player.user.username}"
    except Player.DoesNotExist:
        return f"Player with id {player_id} not found"
    except Exception as e:
        return f"Failed to update player {player_id}: {str(e)}"