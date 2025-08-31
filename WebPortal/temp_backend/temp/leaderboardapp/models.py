from django.db import models
from django.contrib.auth.models import User
from django.core.validators import RegexValidator


class Player(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE, related_name='player_profile')
    leetcode_username = models.CharField(
        max_length=50, 
        blank=True, 
        null=True,
        validators=[RegexValidator(
            regex=r'^[a-zA-Z0-9_-]+$',
            message='LeetCode username can only contain letters, numbers, hyphens, and underscores.'
        )]
    )
    total_score = models.IntegerField(default=0)
    leetcode_score = models.IntegerField(default=0)
    total_solved = models.IntegerField(default=0)
    easy_solved = models.IntegerField(default=0)
    medium_solved = models.IntegerField(default=0)
    hard_solved = models.IntegerField(default=0)
    ranking = models.IntegerField(null=True, blank=True)
    contribution_points = models.IntegerField(default=0)
    reputation = models.IntegerField(default=0)
    rank = models.IntegerField(default=0)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    class Meta:
        ordering = ['-total_score', 'created_at']

    def __str__(self):
        return f"{self.user.username} - Score: {self.total_score}"

    def calculate_total_score(self):
        self.total_score = (self.easy_solved * 1) + (self.medium_solved * 3) + (self.hard_solved * 5)
        self.save(update_fields=['total_score'])


class Leaderboard(models.Model):
    player = models.ForeignKey(Player, on_delete=models.CASCADE, related_name='leaderboard_entries')
    platform = models.CharField(max_length=50, default="LeetCode")
    score = models.IntegerField(default=0)
    rank = models.IntegerField(null=True, blank=True)
    last_updated = models.DateTimeField(auto_now=True)

    class Meta:
        unique_together = ['player', 'platform']

    def __str__(self):
        return f"{self.player.user.username} - {self.platform} - {self.score}"