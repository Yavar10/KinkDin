from django.contrib.auth.models import User
from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAuthenticated, AllowAny
from rest_framework.response import Response
from rest_framework import status
from .serializers import CustomRegisterSerializer, PlayerSerializer, LeaderboardSerializer
from .models import Player, Leaderboard
from .utils import (
    fetch_leetcode_profile, 
    fetch_leetcode_contest_info, 
    fetch_skill_stats,
    update_player_scores
)
from dj_rest_auth.registration.views import RegisterView
from .serializers import CustomRegisterSerializer

class CustomRegisterView(RegisterView):
    serializer_class = CustomRegisterSerializer


@api_view(["POST"])
@permission_classes([AllowAny])
def register(request):
    serializer = CustomRegisterSerializer(data=request.data)
    if serializer.is_valid():
        try:
            user = serializer.save(request)
            # Update scores immediately after registration if leetcode_username is provided
            if hasattr(user, 'player_profile') and user.player_profile.leetcode_username:
                update_player_scores(user.player_profile)
            
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
    try:
        # First update all player scores
        players_with_leetcode = Player.objects.filter(
            leetcode_username__isnull=False
        ).exclude(leetcode_username='')
        
        for player in players_with_leetcode:
            try:
                update_player_scores(player)
            except Exception as e:
                print(f"Failed to update {player.user.username}: {e}")
        
        # Now get the updated leaderboard
        players = Player.objects.select_related('user').order_by("-total_score", "created_at")
        enriched_data = []
        
        for idx, player in enumerate(players, 1):
            player.rank = idx
            player.save(update_fields=['rank'])
            
            # Fetch fresh leetcode profile data
            leetcode_profile = {}
            if player.leetcode_username:
                leetcode_profile = fetch_leetcode_profile(player.leetcode_username)
                if "error" in leetcode_profile:
                    leetcode_profile = {"error": leetcode_profile["error"]}
            
            player_data = {
                "rank": player.rank,
                "username": player.user.username,
                "leetcode_username": player.leetcode_username or "",
                "total_score": player.total_score,
                "total_solved": player.total_solved,
                "easy_solved": player.easy_solved,
                "medium_solved": player.medium_solved,
                "hard_solved": player.hard_solved,
                "ranking": player.ranking,
                "contribution_points": player.contribution_points,
                "reputation": player.reputation,
                "leetcode_profile": leetcode_profile,
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
    try:
        player = Player.objects.get(user=request.user)
        
        if not player.leetcode_username:
            return Response(
                {"error": "No LeetCode username set. Please set your LeetCode username first."}, 
                status=status.HTTP_400_BAD_REQUEST
            )
        
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
    try:
        player = Player.objects.get(user=request.user)
        
        # Update scores before returning profile
        if player.leetcode_username:
            player = update_player_scores(player)
        
        serializer = PlayerSerializer(player)
        
        leetcode_profile = {}
        if player.leetcode_username:
            leetcode_profile = fetch_leetcode_profile(player.leetcode_username)
        
        response_data = serializer.data
        response_data['leetcode_profile'] = leetcode_profile
        
        return Response(response_data)
    except Player.DoesNotExist:
        return Response(
            {"error": "Player profile not found"}, 
            status=status.HTTP_404_NOT_FOUND
        )
    except Exception as e:
        return Response(
            {"error": f"Failed to fetch profile: {str(e)}"}, 
            status=status.HTTP_500_INTERNAL_SERVER_ERROR
        )


@api_view(["GET"])
@permission_classes([IsAuthenticated])
def leetcode_profile_detail(request, username):
    try:
        profile_data = fetch_leetcode_profile(username)
        return Response(profile_data)
    except Exception as e:
        return Response(
            {"error": f"Failed to fetch LeetCode profile: {str(e)}"}, 
            status=status.HTTP_500_INTERNAL_SERVER_ERROR
        )


@api_view(["GET"])
@permission_classes([IsAuthenticated])
def leetcode_contest_info(request, username):
    try:
        contest_data = fetch_leetcode_contest_info(username)
        return Response(contest_data)
    except Exception as e:
        return Response(
            {"error": f"Failed to fetch contest info: {str(e)}"}, 
            status=status.HTTP_500_INTERNAL_SERVER_ERROR
        )


@api_view(["GET"])
@permission_classes([IsAuthenticated])
def leetcode_skill_stats(request, username):
    try:
        skill_data = fetch_skill_stats(username)
        return Response(skill_data)
    except Exception as e:
        return Response(
            {"error": f"Failed to fetch skill stats: {str(e)}"}, 
            status=status.HTTP_500_INTERNAL_SERVER_ERROR
        )


@api_view(["POST"])
@permission_classes([IsAuthenticated])
def update_leetcode_username(request):
    try:
        player = Player.objects.get(user=request.user)
        new_username = request.data.get('leetcode_username')
        
        if not new_username:
            return Response(
                {"error": "LeetCode username is required"}, 
                status=status.HTTP_400_BAD_REQUEST
            )
        
        # Validate username by fetching profile
        profile_data = fetch_leetcode_profile(new_username)
        if "error" in profile_data:
            return Response(
                {"error": f"Invalid LeetCode username: {profile_data['error']}"}, 
                status=status.HTTP_400_BAD_REQUEST
            )
        
        # Update username
        player.leetcode_username = new_username
        player.save()
        
        # Update scores with new username
        updated_player = update_player_scores(player)
        serializer = PlayerSerializer(updated_player)
        
        return Response({
            "message": "LeetCode username updated successfully",
            "player": serializer.data
        })
        
    except Player.DoesNotExist:
        return Response(
            {"error": "Player profile not found"}, 
            status=status.HTTP_404_NOT_FOUND
        )
    except Exception as e:
        return Response(
            {"error": f"Failed to update username: {str(e)}"}, 
            status=status.HTTP_500_INTERNAL_SERVER_ERROR
        )
