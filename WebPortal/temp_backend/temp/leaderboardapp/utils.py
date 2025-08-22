import requests
import os
from dotenv import load_dotenv

load_dotenv()  # Load environment variables from a .env file if present

def fetch_github_stats(username):
    url = f"https://api.github.com/users/{username}"
    headers = {}
    token = os.getenv("GITHUB_TOKEN")  # set this in your environment
    if token:
        headers["Authorization"] = f"token {token}"
    try:
        response = requests.get(url, headers=headers, timeout=5)
        response.raise_for_status()
        data = response.json()
        return {
            "followers": data.get("followers", 0),
            "repos": data.get("public_repos", 0),
        }
    except Exception as e:
        return {"error": str(e)}


def fetch_leetcode_stats(username):
    url = f"https://leetcode-stats-api.herokuapp.com/{username}"
    try:
        response = requests.get(url, timeout=5)
        response.raise_for_status()
        data = response.json()
        return {
            "ranking": data.get("ranking"),
            "total_solved": data.get("totalSolved", 0),
            "easy_solved": data.get("easySolved", 0),
            "medium_solved": data.get("mediumSolved", 0),
            "hard_solved": data.get("hardSolved", 0),
        }
    except Exception as e:
        return {"error": str(e)}
