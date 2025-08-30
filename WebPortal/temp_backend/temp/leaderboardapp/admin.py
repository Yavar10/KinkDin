from django.contrib import admin
from .models import Player, Leaderboard


@admin.register(Player)
class PlayerAdmin(admin.ModelAdmin):
    list_display = [
        'user', 'leetcode_username', 'total_score', 'total_solved',
        'easy_solved', 'medium_solved', 'hard_solved', 'rank', 'updated_at'
    ]
    list_filter = ['created_at', 'updated_at', 'rank']
    search_fields = ['user__username', 'leetcode_username']
    readonly_fields = [
        'total_score', 'leetcode_score', 'total_solved', 
        'easy_solved', 'medium_solved', 'hard_solved',
        'ranking', 'contribution_points', 'reputation', 'rank'
    ]
    ordering = ['-total_score', 'created_at']
    
    fieldsets = (
        ('User Information', {
            'fields': ('user', 'leetcode_username')
        }),
        ('Scores', {
            'fields': ('total_score', 'leetcode_score'),
            'classes': ('collapse',)
        }),
        ('Problem Statistics', {
            'fields': ('total_solved', 'easy_solved', 'medium_solved', 'hard_solved'),
            'classes': ('collapse',)
        }),
        ('LeetCode Profile', {
            'fields': ('ranking', 'contribution_points', 'reputation', 'rank'),
            'classes': ('collapse',)
        }),
        ('Timestamps', {
            'fields': ('created_at', 'updated_at'),
            'classes': ('collapse',)
        })
    )


@admin.register(Leaderboard)
class LeaderboardAdmin(admin.ModelAdmin):
    list_display = ['player', 'platform', 'score', 'rank', 'last_updated']
    list_filter = ['platform', 'last_updated']
    search_fields = ['player__user__username', 'player__leetcode_username']
    ordering = ['-score', 'rank']