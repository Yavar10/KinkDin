from django.contrib import admin
from .models import Player, Leaderboard


@admin.register(Player)
class PlayerAdmin(admin.ModelAdmin):
    list_display = [
        'user', 'leetcode_username', 'total_score', 'total_solved', 'total_submissions',
        'easy_solved', 'medium_solved', 'hard_solved', 'rank', 'updated_at'
    ]
    list_filter = ['created_at', 'updated_at', 'rank']
    search_fields = ['user__username', 'leetcode_username']
    readonly_fields = [
        'total_score', 'leetcode_score', 'total_solved', 'total_submissions', 'total_questions',
        'easy_solved', 'medium_solved', 'hard_solved', 'total_easy', 'total_medium', 'total_hard',
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
            'fields': (
                ('total_solved', 'total_submissions', 'total_questions'), 
                ('easy_solved', 'total_easy'),
                ('medium_solved', 'total_medium'), 
                ('hard_solved', 'total_hard')
            ),
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

    actions = ['update_selected_scores']

    def update_selected_scores(self, request, queryset):
        from .utils import update_player_scores
        updated_count = 0
        for player in queryset:
            if player.leetcode_username:
                try:
                    update_player_scores(player)
                    updated_count += 1
                except Exception as e:
                    self.message_user(request, f"Failed to update {player.user.username}: {str(e)}", level='ERROR')
        
        self.message_user(request, f"Successfully updated {updated_count} players.")
    
    update_selected_scores.short_description = "Update LeetCode scores for selected players"


@admin.register(Leaderboard)
class LeaderboardAdmin(admin.ModelAdmin):
    list_display = ['player', 'platform', 'score', 'rank', 'last_updated']
    list_filter = ['platform', 'last_updated']
    search_fields = ['player__user__username', 'player__leetcode_username']
    ordering = ['-score', 'rank']
    readonly_fields = ['score', 'rank', 'last_updated']

    actions = ['refresh_leaderboard']

    def refresh_leaderboard(self, request, queryset):
        from .utils import refresh_leaderboard_rankings
        refresh_leaderboard_rankings()
        self.message_user(request, "Leaderboard rankings refreshed successfully.")
    
    refresh_leaderboard.short_description = "Refresh leaderboard rankings"

