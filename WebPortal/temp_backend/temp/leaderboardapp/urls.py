from django.urls import path
from .views import leaderboard,register
from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView    

urlpatterns = [

    path('leaderboard/',leaderboard, name='leaderboard'),
    path('register/', register, name='register'),
    path('token/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
]