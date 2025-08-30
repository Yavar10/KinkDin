# views.py
from django.contrib.auth.models import User
from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAuthenticated, AllowAny
from rest_framework.response import Response
from rest_framework import status
from django.db import transaction
from .serializers import CustomRegisterSerializer, PlayerSerializer, LeaderboardSerializer
from .models import Player, Leaderboard
from .utils import fetch_github_stats, fetch_leetcode_stats, update_player_scores


@api_view(["POST"])
@permission_classes([AllowAny])
def register(request):
    """Register a new user with Player profile"""
    serializer = CustomRegisterSerializer(data=request.data)
    if serializer.is_valid():
        try:
            user = serializer.save(request)
            return Response({
                "message": "User & Player created successfully",
                "user_id": user.id,
                "username": user.username
            }, status=status.HTTP_201_CREATED)
        except Exception as e:
            return Response(
                {"error": f"Registration failed: {str(e)}"}, 
                status=status.HTTP_500_INTERNAL_SERVER_ERROR
            )
    return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(["GET"])
@permission_classes([IsAuthenticated])
def leaderboard(request):
    """Get leaderboard with live stats"""
    try:
        players = Player.objects.select_related('user').order_by("-total_score", "created_at")
        enriched_data = []
        
        for idx, player in enumerate(players, 1):
            # Update rank
            player.rank = idx
            player.save(update_fields=['rank'])
            
            # Fetch live stats
            github_stats = fetch_github_stats(player.github_username) if player.github_username else {}
            leetcode_stats = fetch_leetcode_stats(player.leetcode_username) if player.leetcode_username else {}
            
            player_data = {
                "rank": player.rank,
                "username": player.user.username,
                "github_username": player.github_username,
                "leetcode_username": player.leetcode_username,
                "total_score": player.total_score,
                "github_score": player.github_score,
                "leetcode_score": player.leetcode_score,
                "github_stats": github_stats,
                "leetcode_stats": leetcode_stats,
                "created_at": player.created_at,
            }
            enriched_data.append(player_data)
        
        return Response({
            "count": len(enriched_data),
            "results": enriched_data
        })
        
    except Exception as e:
        return Response(
            {"error": f"Failed to fetch leaderboard: {str(e)}"}, 
            status=status.HTTP_500_INTERNAL_SERVER_ERROR
        )


@api_view(["POST"])
@permission_classes([IsAuthenticated])
def update_scores(request):
    """Update scores for the authenticated user"""
    try:
        player = Player.objects.get(user=request.user)
        updated_player = update_player_scores(player)
        
        serializer = PlayerSerializer(updated_player)
        return Response({
            "message": "Scores updated successfully",
            "player": serializer.data
        })
        
    except Player.DoesNotExist:
        return Response(
            {"error": "Player profile not found"}, 
            status=status.HTTP_404_NOT_FOUND
        )
    except Exception as e:
        return Response(
            {"error": f"Failed to update scores: {str(e)}"}, 
            status=status.HTTP_500_INTERNAL_SERVER_ERROR
        )


@api_view(["GET"])
@permission_classes([IsAuthenticated])
def my_profile(request):
    """Get current user's player profile"""
    try:
        player = Player.objects.get(user=request.user)
        serializer = PlayerSerializer(player)
        return Response(serializer.data)
    except Player.DoesNotExist:
        return Response(
            {"error": "Player profile not found"}, 
            status=status.HTTP_404_NOT_FOUND
        )
