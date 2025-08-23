from django.contrib.auth.models import User
from rest_framework import serializers
from .models import Player, Leaderboard
from dj_rest_auth.registration.serializers import RegisterSerializer
import requests


class CustomRegisterSerializer(RegisterSerializer):
    leetcode_username = serializers.CharField(required=False, allow_blank=True, max_length=50)
    github_username = serializers.CharField(required=False, allow_blank=True, max_length=39)

    def validate_github_username(self, value):
        """Validate GitHub username exists"""
        if value and value.strip():
            try:
                response = requests.get(f'https://api.github.com/users/{value}', timeout=5)
                if response.status_code == 404:
                    raise serializers.ValidationError("GitHub username does not exist.")
            except requests.RequestException:
                # If API is down, just validate format - don't fail registration
                pass
        return value

    def custom_signup(self, request, user):
        """Create Player profile linked with User"""
        Player.objects.create(
            user=user,
            leetcode_username=self.validated_data.get("leetcode_username", ""),
            github_username=self.validated_data.get("github_username", "")
        )


class PlayerSerializer(serializers.ModelSerializer):
    username = serializers.CharField(source='user.username', read_only=True)
    email = serializers.EmailField(source='user.email', read_only=True)

    class Meta:
        model = Player
        fields = [
            'id', 'username', 'email', 'github_username', 'leetcode_username', 
            'total_score', 'github_score', 'leetcode_score', 'rank', 'created_at'
        ]
        read_only_fields = ['total_score', 'github_score', 'leetcode_score', 'rank']


class LeaderboardSerializer(serializers.ModelSerializer):
    username = serializers.CharField(source="player.user.username", read_only=True)
    github_username = serializers.CharField(source="player.github_username", read_only=True)
    leetcode_username = serializers.CharField(source="player.leetcode_username", read_only=True)

    class Meta:
        model = Leaderboard
        fields = ["id", "username", "github_username", "leetcode_username", "platform", "score", "rank", "last_updated"]

