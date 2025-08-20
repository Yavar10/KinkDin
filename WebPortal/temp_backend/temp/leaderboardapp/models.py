from django.db import models

class Player(models.Model):
    username = models.CharField(max_length=100, unique=True)
    leetcode_username = models.CharField(max_length=100, blank=True, null=True)
    github_username = models.CharField(max_length=100, blank=True, null=True)
    score = models.IntegerField(default=0)

    def __str__(self):
        return self.username
