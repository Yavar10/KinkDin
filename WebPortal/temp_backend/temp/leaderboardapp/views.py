from django.contrib.auth.models import User
from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAuthenticated, AllowAny
from rest_framework.response import Response
from rest_framework import status
from .serializers import RegisterSerializer, PlayerSerializer
from .models import Player
import requests



@api_view(["POST"])
@permission_classes([AllowAny])
def register(request):
    serializer = RegisterSerializer(data=request.data)
    if serializer.is_valid():
        serializer.save()
        return Response({"message": "User created successfully"}, status=status.HTTP_201_CREATED)
    return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(["GET"])
@permission_classes([IsAuthenticated])
def leaderboard(request):
    players = Player.objects.all()
    for player in players:
        leet = get_leetcode_stats(player.leetcode_username) if player.leetcode_username else 0
        git = get_github_stats(player.github_username) if player.github_username else 0
        player.score = leet + git
        player.save()

    players = Player.objects.order_by("-score")
    serializer = PlayerSerializer(players, many=True)
    return Response(serializer.data)
