import requests
import json


LEETCODE_API_URL = "https://leetcode.com/graphql"

USER_PROFILE_QUERY = """
query getUserProfile($username: String!) {
    allQuestionsCount {
        difficulty
        count
    }
    matchedUser(username: $username) {
        contributions {
            points
        }
        profile {
            reputation
            ranking
        }
        submitStats {
            acSubmissionNum {
                difficulty
                count
                submissions
            }
            totalSubmissionNum
        }
        submissionCalendar
    }
    recentSubmissionList(username: $username) {
        title
        titleSlug
        timestamp
        statusDisplay
        lang
    }
}
"""

SKILL_STATS_QUERY = """
query skillStats($username: String!) {
    matchedUser(username: $username) {
        tagProblemCounts {
            advanced {
                tagName
                tagSlug
                problemsSolved
            }
            intermediate {
                tagName
                tagSlug
                problemsSolved
            }
            fundamental {
                tagName
                tagSlug
                problemsSolved
            }
        }
    }
}
"""

CONTEST_RANKING_QUERY = """
query userContestRankingInfo($username: String!) {
    userContestRanking(username: $username) {
        attendedContestsCount
        rating
        globalRanking
        totalParticipants
        topPercentage
        badge {
            name
        }
    }
    userContestRankingHistory(username: $username) {
        attended
        trendDirection
        problemsSolved
        totalProblems
        finishTimeInSeconds
        rating
        ranking
        contest {
            title
            startTime
        }
    }
}
"""


def query_leetcode_api(query, variables):
    try:
        headers = {
            'Content-Type': 'application/json',
            'User-Agent': 'Mozilla/5.0 (compatible; LeetCodeAPI/1.0)'
        }
        response = requests.post(
            LEETCODE_API_URL, 
            json={'query': query, 'variables': variables},
            headers=headers,
            timeout=10
        )
        response.raise_for_status()
        data = response.json()
        
        if 'errors' in data:
            return {"error": data['errors'][0]['message']}
        
        return data['data']
    except requests.exceptions.RequestException as e:
        return {"error": f"LeetCode API error: {str(e)}"}


def fetch_leetcode_profile(username):
    if not username or not username.strip():
        return {"error": "No LeetCode username provided"}
    
    data = query_leetcode_api(USER_PROFILE_QUERY, {"username": username})
    
    if "error" in data:
        return data
    
    if not data.get("matchedUser"):
        return {"error": "LeetCode user not found"}
    
    matched_user = data["matchedUser"]
    all_questions = data["allQuestionsCount"]
    submit_stats = matched_user["submitStats"]
    ac_submissions = submit_stats["acSubmissionNum"]
    
    # Safely extract data with proper indexing
    total_solved = ac_submissions[0]["count"] if len(ac_submissions) > 0 else 0
    easy_solved = ac_submissions[1]["count"] if len(ac_submissions) > 1 else 0
    medium_solved = ac_submissions[2]["count"] if len(ac_submissions) > 2 else 0
    hard_solved = ac_submissions[3]["count"] if len(ac_submissions) > 3 else 0
    
    total_questions = all_questions[0]["count"] if len(all_questions) > 0 else 0
    total_easy = all_questions[1]["count"] if len(all_questions) > 1 else 0
    total_medium = all_questions[2]["count"] if len(all_questions) > 2 else 0
    total_hard = all_questions[3]["count"] if len(all_questions) > 3 else 0
    
    score = (easy_solved * 1) + (medium_solved * 3) + (hard_solved * 5)
    
    return {
        "username": username,
        "totalSolved": total_solved,
        "totalSubmissions": submit_stats["totalSubmissionNum"],
        "totalQuestions": total_questions,
        "easySolved": easy_solved,
        "totalEasy": total_easy,
        "mediumSolved": medium_solved,
        "totalMedium": total_medium,
        "hardSolved": hard_solved,
        "totalHard": total_hard,
        "ranking": matched_user["profile"]["ranking"],
        "contributionPoint": matched_user["contributions"]["points"],
        "reputation": matched_user["profile"]["reputation"],
        "submissionCalendar": json.loads(matched_user["submissionCalendar"]) if matched_user["submissionCalendar"] else {},
        "recentSubmissions": data.get("recentSubmissionList", []),
        "matchedUserStats": submit_stats,
        "score": score
    }


def fetch_leetcode_contest_info(username):
    if not username or not username.strip():
        return {"error": "No LeetCode username provided"}
    
    data = query_leetcode_api(CONTEST_RANKING_QUERY, {"username": username})
    
    if "error" in data:
        return data
    
    contest_ranking = data.get("userContestRanking")
    contest_history = data.get("userContestRankingHistory", [])
    
    if not contest_ranking:
        return {"error": "Contest data not found"}
    
    return {
        "attended_contests": contest_ranking.get("attendedContestsCount", 0),
        "rating": contest_ranking.get("rating", 0),
        "global_ranking": contest_ranking.get("globalRanking", 0),
        "total_participants": contest_ranking.get("totalParticipants", 0),
        "top_percentage": contest_ranking.get("topPercentage", 0),
        "badge": contest_ranking.get("badge", {}).get("name", ""),
        "contest_history": contest_history[:10]
    }


def fetch_skill_stats(username):
    if not username or not username.strip():
        return {"error": "No LeetCode username provided"}
    
    data = query_leetcode_api(SKILL_STATS_QUERY, {"username": username})
    
    if "error" in data:
        return data
    
    matched_user = data.get("matchedUser")
    if not matched_user:
        return {"error": "User skill stats not found"}
    
    tag_counts = matched_user.get("tagProblemCounts", {})
    
    return {
        "advanced": tag_counts.get("advanced", []),
        "intermediate": tag_counts.get("intermediate", []),
        "fundamental": tag_counts.get("fundamental", [])
    }


def update_player_scores(player):
    """Update a single player's scores from LeetCode API"""
    profile_data = fetch_leetcode_profile(player.leetcode_username)
    
    if "error" not in profile_data:
        # Update player fields with correct mapping
        player.leetcode_score = profile_data.get("score", 0)
        player.total_solved = profile_data.get("totalSolved", 0)
        player.total_submissions = profile_data.get("totalSubmissions", 0)
        player.total_questions = profile_data.get("totalQuestions", 0)
        player.easy_solved = profile_data.get("easySolved", 0)
        player.medium_solved = profile_data.get("mediumSolved", 0)
        player.hard_solved = profile_data.get("hardSolved", 0)
        player.total_easy = profile_data.get("totalEasy", 0)
        player.total_medium = profile_data.get("totalMedium", 0)
        player.total_hard = profile_data.get("totalHard", 0)
        player.ranking = profile_data.get("ranking")
        player.contribution_points = profile_data.get("contributionPoint", 0)
        player.reputation = profile_data.get("reputation", 0)
        
        # Calculate and save total score
        player.calculate_total_score()
        
        # Update or create leaderboard entry
        from .models import Leaderboard
        leaderboard_entry, created = Leaderboard.objects.get_or_create(
            player=player,
            platform="LeetCode",
            defaults={'score': player.total_score}
        )
        if not created:
            leaderboard_entry.score = player.total_score
            leaderboard_entry.save()
        
        print(f"Updated scores for {player.user.username}: {profile_data.get('score', 0)} points")
    else:
        print(f"Failed to update {player.user.username}: {profile_data['error']}")
    
    return player


def refresh_leaderboard_rankings():
    """Refresh all leaderboard rankings"""
    from .models import Leaderboard
    
    leaderboard_entries = Leaderboard.objects.all().order_by('-score')
    
    for idx, entry in enumerate(leaderboard_entries, 1):
        entry.rank = idx
        entry.save(update_fields=['rank'])
        
        # Also update player rank
        entry.player.rank = idx
        entry.player.save(update_fields=['rank'])
    
    print(f"Updated rankings for {leaderboard_entries.count()} leaderboard entries")

