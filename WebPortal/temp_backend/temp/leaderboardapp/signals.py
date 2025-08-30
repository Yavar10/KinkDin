from django.db.models.signals import post_save
from django.dispatch import receiver
from django.contrib.auth.models import User
from .models import Player


@receiver(post_save, sender=User)
def create_player_profile(sender, instance, created, **kwargs):
    """Create Player profile when User is created"""
    if created and not hasattr(instance, 'player_profile'):
        Player.objects.get_or_create(user=instance)


# apps.py (update to load signals)
from django.apps import AppConfig


class LeaderboardappConfig(AppConfig):
    default_auto_field = 'django.db.models.BigAutoField'
    name = 'leaderboardapp'

    def ready(self):
        import leaderboardapp.signals