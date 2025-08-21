from django.urls import path
from .views import leaderboard,register
urlpatterns = [

    path('leaderboard/',leaderboard, name='leaderboard'),
    path('register/', register, name='register'),
]