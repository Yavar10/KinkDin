from django.urls import path
from .views import (
    leaderboard, 
    register, 
    update_scores, 
    my_profile,
    leetcode_profile_detail,
    leetcode_contest_info,
    leetcode_skill_stats,
    update_leetcode_username
)

urlpatterns = [
    path('leaderboard/', leaderboard, name='leaderboard'),
    path('register/', register, name='register'),
    path('update-scores/', update_scores, name='update-scores'),
    path('my-profile/', my_profile, name='my-profile'),
    path('update-leetcode-username/', update_leetcode_username, name='update-leetcode-username'),
    path('leetcode-profile/<str:username>/', leetcode_profile_detail, name='leetcode-profile-detail'),
    path('leetcode-contest/<str:username>/', leetcode_contest_info, name='leetcode-contest-info'),
    path('leetcode-skills/<str:username>/', leetcode_skill_stats, name='leetcode-skill-stats'),
]