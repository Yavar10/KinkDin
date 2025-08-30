from .models import Player
from .utils import fetch_github_stats, fetch_leetcode_stats

def update_leaderboard():
    players = Player.objects.all()
    for player in players:
        leet_stats = fetch_leetcode_stats(player.leetcode_username) if player.leetcode_username else {}
        git_stats = fetch_github_stats(player.github_username) if player.github_username else {}

        # scoring system
        leet_score = leet_stats.get("total_solved", 0)
        git_score = git_stats.get("repos", 0) + git_stats.get("followers", 0)

        # update cached score
        player.score = leet_score + git_score
        player.save()

    return "Leaderboard updated successfully"
