import requests
import os
from dotenv import load_dotenv

load_dotenv()


def fetch_github_stats(username):
    """Fetch GitHub statistics for a user"""
    if not username or not username.strip():
        return {"error": "No GitHub username provided"}
        
    url = f"https://api.github.com/users/{username}"
    headers = {}
    token = os.getenv("GITHUB_TOKEN")
    if token:
        headers["Authorization"] = f"token {token}"
    
    try:
        response = requests.get(url, headers=headers, timeout=10)
        response.raise_for_status()
        data = response.json()
        
        # Calculate score based on GitHub metrics
        followers = data.get("followers", 0)
        repos = data.get("public_repos", 0)
        score = (followers * 2) + (repos * 1)  # Custom scoring formula
        
        return {
            "username": data.get("login"),
            "followers": followers,
            "repos": repos,
            "score": score,
            "avatar_url": data.get("avatar_url"),
            "bio": data.get("bio"),
        }
    except requests.exceptions.RequestException as e:
        return {"error": f"GitHub API error: {str(e)}"}


def fetch_leetcode_stats(username):
    """Fetch LeetCode statistics for a user"""
    if not username or not username.strip():
        return {"error": "No LeetCode username provided"}
        
    url = f"https://leetcode-stats-api.herokuapp.com/{username}"
    
    try:
        response = requests.get(url, timeout=10)
        response.raise_for_status()
        data = response.json()
        
        if data.get("status") == "error":
            return {"error": "LeetCode user not found"}
        
        # Calculate score based on problems solved
        total_solved = data.get("totalSolved", 0)
        easy_solved = data.get("easySolved", 0)
        medium_solved = data.get("mediumSolved", 0)
        hard_solved = data.get("hardSolved", 0)
        
        # Custom scoring: Easy=1, Medium=3, Hard=5
        score = (easy_solved * 1) + (medium_solved * 3) + (hard_solved * 5)
        
        return {
            "username": username,
            "ranking": data.get("ranking"),
            "total_solved": total_solved,
            "easy_solved": easy_solved,
            "medium_solved": medium_solved,
            "hard_solved": hard_solved,
            "score": score,
        }
    except requests.exceptions.RequestException as e:
        return {"error": f"LeetCode API error: {str(e)}"}


def update_player_scores(player):
    """Update player's GitHub and LeetCode scores"""
    github_stats = fetch_github_stats(player.github_username)
    leetcode_stats = fetch_leetcode_stats(player.leetcode_username)
    
    # Update scores if no errors
    if "error" not in github_stats:
        player.github_score = github_stats.get("score", 0)
    
    if "error" not in leetcode_stats:
        player.leetcode_score = leetcode_stats.get("score", 0)
    
    player.calculate_total_score()
    return player
