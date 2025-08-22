from django.contrib.auth.models import User
from rest_framework import serializers
from .models import Player, Leaderboard

class RegisterSerializer(serializers.ModelSerializer):
    password = serializers.CharField(write_only=True)

    class Meta:
        model = User
        fields = ("username", "email", "password")

    def create(self, validated_data):
        user = User.objects.create_user(
            username=validated_data["username"],
            email=validated_data.get("email"),
            password=validated_data["password"],
        )
        Player.objects.create(username=user.username)  
        return user


class PlayerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Player
        fields = ["id", "name", "leetcode_username", "github_username", "score"]

class LeaderboardSerializer(serializers.ModelSerializer):
    user = serializers.CharField(source="user.name")  

    class Meta:
        model = Leaderboard
        fields = ["id", "user", "platform", "score", "rank", "last_updated"]
