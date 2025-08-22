from celery import Celery
from celery.schedules import crontab

app = Celery("your_project")

app.conf.beat_schedule = {
    "update-leaderboard-every-10-mins": {
        "task": "your_app.tasks.update_leaderboard",
        "schedule": crontab(minute="*/10"),  # every 10 minutes
    },
}
