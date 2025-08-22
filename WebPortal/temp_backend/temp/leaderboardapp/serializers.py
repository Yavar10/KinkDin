from django.contrib.auth.models import User
from rest_framework import serializers
from .models import Player,Leaderboard
from dj_rest_auth.registration.serializers import RegisterSerializer

'''class RegisterSerializer(serializers.ModelSerializer):
    password = serializers.CharField(write_only=True)
    leetcode_username = serializers.CharField(required=False, allow_blank=True)
    github_username = serializers.CharField(required=False, allow_blank=True)

    class Meta:
        model = User
        fields = ("username", "email", "password", "leetcode_username", "github_username")

    def create(self, validated_data):
        leetcode_username = validated_data.pop("leetcode_username", "")
        github_username = validated_data.pop("github_username", "")

        # Create User
        user = User.objects.create_user(
            username=validated_data["username"],
            email=validated_data.get("email"),
            password=validated_data["password"],
        )

        # Create Player linked with User.username
        Player.objects.create(
            name=user.username,
            leetcode_username=leetcode_username,
            github_username=github_username,
            score=0,
        )

        return user
'''
class CustomRegisterSerializer(RegisterSerializer):
    leetcode_username = serializers.CharField(required=True)
    github_username = serializers.CharField(required=True)

    def save(self, request):
        user = super().save(request)
        # Create Player profile linked with User
        Player.objects.create(
            user=user,
            leetcode_username=self.validated_data.get("leetcode_username", ""),
            github_username=self.validated_data.get("github_username", "")
        )
        return user

class PlayerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Player
        fields = "__all__"

class LeaderboardSerializer(serializers.ModelSerializer):
    user = serializers.CharField(source="user.name")  

    class Meta:
        model = Leaderboard
        fields = ["id", "user", "platform", "score", "rank", "last_updated"]
