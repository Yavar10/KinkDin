
# admin.py
from django.contrib import admin
from .models import Player, Leaderboard


@admin.register(Player)
class PlayerAdmin(admin.ModelAdmin):
    list_display = ['user', 'github_username', 'leetcode_username', 'total_score', 'rank', 'created_at']
    list_filter = ['created_at', 'total_score']
    search_fields = ['user__username', 'github_username', 'leetcode_username']
    readonly_fields = ['rank', 'created_at', 'updated_at']
    ordering = ['-total_score']


@admin.register(Leaderboard)
class LeaderboardAdmin(admin.ModelAdmin):
    list_display = ['player', 'platform', 'score', 'rank', 'last_updated']
    list_filter = ['platform', 'last_updated']
    search_fields = ['player__user__username']