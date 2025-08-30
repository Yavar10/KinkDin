from django.contrib.auth.models import User
from rest_framework import serializers
from .models import Player, Leaderboard
from dj_rest_auth.registration.serializers import RegisterSerializer


class CustomRegisterSerializer(RegisterSerializer):
    leetcode_username = serializers.CharField(required=False, allow_blank=True, max_length=50)

    def custom_signup(self, request, user):
        Player.objects.create(
            user=user,
            leetcode_username=self.validated_data.get("leetcode_username", "")
        )


class PlayerSerializer(serializers.ModelSerializer):
    username = serializers.CharField(source='user.username', read_only=True)
    email = serializers.EmailField(source='user.email', read_only=True)

    class Meta:
        model = Player
        fields = [
            'id', 'username', 'email', 'leetcode_username', 
            'total_score', 'leetcode_score', 'total_solved',
            'easy_solved', 'medium_solved', 'hard_solved',
            'ranking', 'contribution_points', 'reputation',
            'rank', 'created_at'
        ]
        read_only_fields = [
            'total_score', 'leetcode_score', 'total_solved',
            'easy_solved', 'medium_solved', 'hard_solved',
            'ranking', 'contribution_points', 'reputation', 'rank'
        ]


class LeaderboardSerializer(serializers.ModelSerializer):
    username = serializers.CharField(source="player.user.username", read_only=True)
    leetcode_username = serializers.CharField(source="player.leetcode_username", read_only=True)

    class Meta:
        model = Leaderboard
        fields = ["id", "username", "leetcode_username", "platform", "score", "rank", "last_updated"]