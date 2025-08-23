from django.urls import path
from .views import leaderboard, register, update_scores, my_profile

urlpatterns = [
    path('leaderboard/', leaderboard, name='leaderboard'),
    path('register/', register, name='register'),
    path('update-scores/', update_scores, name='update-scores'),
    path('my-profile/', my_profile, name='my-profile'),
]
