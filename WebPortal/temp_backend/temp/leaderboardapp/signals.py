from django.db.models.signals import post_save
from django.dispatch import receiver
from django.contrib.auth.models import User
from .models import Player


@receiver(post_save, sender=User)
def create_player_profile(sender, instance, created, **kwargs):
    if created and not hasattr(instance, 'player_profile'):
        Player.objects.get_or_create(user=instance)