from rest_framework import generics,status
from rest_framework.decorators import api_view
from rest_framework.response import Response
from django.db,.models import Q
from .models import LeaderboardEntry, UserActivity, Contest

from .serializers import LeaderboardEntrySerializer, UserActivitySerializer, ContestSerializer

class LeaderboardView(generics.ListAPIView):
    serializer_class = LeaderboardEntrySerializer
    
    def get_queryset(self):
        platform = self.kwargs