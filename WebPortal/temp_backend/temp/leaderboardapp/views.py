from django.contrib.auth.models import User
from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAuthenticated, AllowAny
from rest_framework.response import Response
from rest_framework import status
from .serializers import CustomRegisterSerializer, PlayerSerializer
from .models import Player
from .utils import fetch_github_stats, fetch_leetcode_stats

# --- Register View ---
@api_view(["POST"])
@permission_classes([AllowAny])
def register(request):
    serializer = CustomRegisterSerializer(data=request.data)
    if serializer.is_valid():
        serializer.save()
        return Response({"message": "User & Player created successfully"}, status=status.HTTP_201_CREATED)
    return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


# --- Leaderboard View ---
@api_view(["GET"])
@permission_classes([IsAuthenticated])
def leaderboard(request):
    players = Player.objects.order_by("-score")
    serializer = PlayerSerializer(players, many=True)
    player_data = serializer.data

    enriched_data = []
    for player in player_data:
        github_username = player.get("github_username")
        leetcode_username = player.get("leetcode_username")

        github_stats = fetch_github_stats(github_username) if github_username else {}
        leetcode_stats = fetch_leetcode_stats(leetcode_username) if leetcode_username else {}

        enriched_data.append({
            **player,
            "github_stats": github_stats,
            "leetcode_stats": leetcode_stats,
        })

    return Response(enriched_data)
