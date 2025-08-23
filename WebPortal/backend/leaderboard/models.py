from django.db import models
from django.contrib.auth import get_user_model

User = get_user_model()

class LeaderboardEntry(models.Model):
    PLATFORM_CHOICES = [
        ('overall', 'Overall'),
        ('github', 'GitHub'),
        ('leetcode', 'LeetCode'),
        ('codeforces', 'Codeforces'),
    ]
    
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    platform = models.CharField(max_length=20, choices=PLATFORM_CHOICES)
    score = models.FloatField(default=0)
    rank = models.IntegerField(default=0)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    
    class Meta:
        unique_together = ('user', 'platform')
        ordering = ['rank']

class UserActivity(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    platform = models.CharField(max_length=20)
    activity_type = models.CharField(max_length=50)  # 'commit', 'problem_solved', 'contest_participated'
    details = models.JSONField(default=dict)
    timestamp = models.DateTimeField(auto_now_add=True)
    
    class Meta:
        ordering = ['-timestamp']

class Contest(models.Model):
    name = models.CharField(max_length=200)
    platform = models.CharField(max_length=20)
    start_time = models.DateTimeField()
    duration = models.DurationField()
    participants = models.ManyToManyField(User, blank=True)
    contest_id = models.CharField(max_length=100)
    
    def __str__(self):
        return f"{self.name} - {self.platform}"