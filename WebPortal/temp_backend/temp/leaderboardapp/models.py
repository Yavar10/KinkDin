from django.db import models
from django.contrib.auth.models import User
from django.core.validators import RegexValidator


class Player(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE, related_name='player_profile')  # Fixed: Link to User model
    github_username = models.CharField(
        max_length=39,  # GitHub max username length
        blank=True, 
        null=True,
        validators=[RegexValidator(
            regex=r'^[a-zA-Z0-9](?:[a-zA-Z0-9]|-(?=[a-zA-Z0-9])){0,38}$',
            message='Invalid GitHub username format.'
        )]
    )
    leetcode_username = models.CharField(
        max_length=50, 
        blank=True, 
        null=True,
        validators=[RegexValidator(
            regex=r'^[a-zA-Z0-9_-]+$',
            message='LeetCode username can only contain letters, numbers, hyphens, and underscores.'
        )]
    )
    total_score = models.IntegerField(default=0)  # Combined score
    github_score = models.IntegerField(default=0)
    leetcode_score = models.IntegerField(default=0)
    rank = models.IntegerField(default=0)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    class Meta:
        ordering = ['-total_score', 'created_at']

    def __str__(self):
        return f"{self.user.username} - Score: {self.total_score}"

    def calculate_total_score(self):
        """Calculate total score from GitHub and LeetCode scores"""
        self.total_score = self.github_score + self.leetcode_score
        self.save(update_fields=['total_score'])


class Leaderboard(models.Model):
    player = models.ForeignKey(Player, on_delete=models.CASCADE, related_name='leaderboard_entries')  # Fixed: should be player
    platform = models.CharField(max_length=50, choices=[("GitHub", "GitHub"), ("LeetCode", "LeetCode")])
    score = models.IntegerField(default=0)
    rank = models.IntegerField(null=True, blank=True)
    last_updated = models.DateTimeField(auto_now=True)

    class Meta:
        unique_together = ['player', 'platform']  # One entry per player per platform

    def __str__(self):
        return f"{self.player.user.username} - {self.platform} - {self.score}"
