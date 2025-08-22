from django.db import models

class Player(models.Model):
    name = models.CharField(max_length=100, unique=True)
    leetcode_username = models.CharField(max_length=100, blank=True, null=True)
    github_username = models.CharField(max_length=100, blank=True, null=True)
    score = models.IntegerField(default=0)

    def __str__(self):
        return self.username

class Leaderboard(models.Model):
    user = models.ForeignKey(Player, on_delete=models.CASCADE)
    platform = models.CharField(max_length=50, choices=[("GitHub", "GitHub"), ("LeetCode", "LeetCode")])
    score = models.IntegerField(default=0)
    rank = models.IntegerField(null=True, blank=True)
    last_updated = models.DateTimeField(auto_now=True)

    def __str__(self):
        return f"{self.user.username} - {self.platform} - {self.score}"
    