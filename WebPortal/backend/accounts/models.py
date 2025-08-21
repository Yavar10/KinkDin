from django.db import models

from django.contrib.auth.models import AbstractUser

class User(AbstractUser):

    email= models.EmailField(unique=True)
    college_id = models.CharField(max_length=100, blank=True, null=True)
    year=models.IntegerField(choices=[(1, 'First Year'), (2, 'Second Year'), (3, 'Third Year'), (4, 'Fourth Year')])
    branch = models.CharField(max_length=50, blank=True, null=True)
    avatar = models.ImageField(upload_to='avatars/', blank=True, null=True)
    is_verified = models.BooleanField(default=False)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = ['username,college_id]']

class Profile(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    github_username= models.CharField(max_length=100, blank=True, null=True)
    leetcode_username = models.CharField(max_length=100, blank=True, null=True)

    github_repositories = models.IntegerField(default=0)
    github_contributions = models.IntegerField(default=0)

    leetcode_solved = models.IntegerField(default=0)
    leetcode_easy = models.IntegerField(default=0)
    leetcode_medium = models.IntegerField(default=0)
    leetcode_hard = models.IntegerField(default=0)
    leetcode_streak = models.IntegerField(default=0)
    leetcode_rating = models.IntegerField(default=0)

    last_updated = models.DateTimeField(auto_now=True)

    def __str__(self):
        return f"{self.user.username}'s Profile"
    
