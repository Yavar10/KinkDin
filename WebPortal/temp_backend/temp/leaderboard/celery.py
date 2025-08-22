import os
from celery import Celery
from celery.schedules import crontab
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "leaderboard.settings")

app = Celery("leaderboard")
app.config_from_object("django.conf:settings", namespace="CELERY")
app.autodiscover_tasks()

app.conf.beat_schedule = {
    "update-leaderboard-every-30-mins": {
        "task": "leaderboard.tasks.update_leaderboard",
        "schedule": crontab(minute="*/30"),  # every 30 minutes
    },
}