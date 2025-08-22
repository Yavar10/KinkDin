from .models import Player, Leaderboard
from .utils import fetch_github_stats, fetch_leetcode_stats
def update_leaderboard():
    players = Player.objects.all()
    for player in players:
        leet = get_leetcode_stats(player.leetcode_username) if player.leetcode_username else 0
        git = get_github_stats(player.github_username) if player.github_username else 0
        player.score = leet + git
        player.save()
    return "Leaderboard updated successfully"