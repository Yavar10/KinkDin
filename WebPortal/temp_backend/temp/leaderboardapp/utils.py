import requests
import time

def fetch_leetcode_profile(username):
    if not username:
        return {"error": "No username provided"}
    
    query = """
    query getUserProfile($username: String!) {
        matchedUser(username: $username) {
            username
            profile {
                ranking
                userAvatar
                realName
                aboutMe
                school
                websites
                countryName
                company
                jobTitle
                skillTags
                postViewCount
                postViewCountDiff
                reputation
                reputationDiff
                solutionCount
                solutionCountDiff
                categoryDiscussCount
                categoryDiscussCountDiff
            }
            submitStatsGlobal {
                acSubmissionNum {
                    difficulty
                    count
                    submissions
                }
            }
        }
    }
    """
    
    try:
        response = requests.post(
            "https://leetcode.com/graphql",
            json={
                "query": query,
                "variables": {"username": username}
            },
            headers={
                "Content-Type": "application/json",
                "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36",
                "Referer": "https://leetcode.com",
                "Origin": "https://leetcode.com"
            },
            timeout=10
        )
        
        if response.status_code != 200:
            return {"error": f"HTTP {response.status_code}: Failed to fetch data"}
        
        data = response.json()
        
        if "errors" in data:
            return {"error": f"GraphQL Error: {data['errors'][0]['message']}"}
        
        if not data.get("data", {}).get("matchedUser"):
            return {"error": "User not found on LeetCode"}
            
        user_data = data["data"]["matchedUser"]
        
        # Parse submission statistics
        submissions = user_data.get("submitStatsGlobal", {}).get("acSubmissionNum", [])
        
        easy = next((s["count"] for s in submissions if s["difficulty"] == "Easy"), 0)
        medium = next((s["count"] for s in submissions if s["difficulty"] == "Medium"), 0)
        hard = next((s["count"] for s in submissions if s["difficulty"] == "Hard"), 0)
        total = easy + medium + hard
        
        # Calculate score (Easy: 1pt, Medium: 3pts, Hard: 5pts)
        score = easy + (medium * 3) + (hard * 5)
        
        # Get profile info
        profile = user_data.get("profile", {})
        
        return {
            "username": user_data.get("username"),
            "totalSolved": total,
            "easySolved": easy,
            "mediumSolved": medium,
            "hardSolved": hard,
            "score": score,
            "ranking": profile.get("ranking"),
            "reputation": profile.get("reputation", 0),
            "realName": profile.get("realName"),
            "aboutMe": profile.get("aboutMe"),
            "school": profile.get("school"),
            "company": profile.get("company"),
            "jobTitle": profile.get("jobTitle"),
            "countryName": profile.get("countryName"),
            "userAvatar": profile.get("userAvatar"),
            "postViewCount": profile.get("postViewCount", 0),
            "solutionCount": profile.get("solutionCount", 0)
        }
        
    except requests.exceptions.Timeout:
        return {"error": "Request timed out"}
    except requests.exceptions.ConnectionError:
        return {"error": "Connection error"}
    except Exception as e:
        return {"error": f"Request failed: {str(e)}"}


def fetch_leetcode_contest_info(username):
    if not username:
        return {"error": "No username provided"}
    
    query = """
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
    
    try:
        response = requests.post(
            "https://leetcode.com/graphql",
            json={
                "query": query,
                "variables": {"username": username}
            },
            headers={
                "Content-Type": "application/json",
                "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36"
            },
            timeout=10
        )
        
        if response.status_code != 200:
            return {"error": f"HTTP {response.status_code}"}
        
        data = response.json()
        
        if "errors" in data:
            return {"error": data["errors"][0]["message"]}
        
        return data.get("data", {})
        
    except Exception as e:
        return {"error": f"Request failed: {str(e)}"}


def fetch_skill_stats(username):
    if not username:
        return {"error": "No username provided"}
    
    query = """
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
    
    try:
        response = requests.post(
            "https://leetcode.com/graphql",
            json={
                "query": query,
                "variables": {"username": username}
            },
            headers={
                "Content-Type": "application/json",
                "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36"
            },
            timeout=10
        )
        
        if response.status_code != 200:
            return {"error": f"HTTP {response.status_code}"}
        
        data = response.json()
        
        if "errors" in data:
            return {"error": data["errors"][0]["message"]}
        
        return data.get("data", {})
        
    except Exception as e:
        return {"error": f"Request failed: {str(e)}"}


def update_player_scores(player):
    """Update player scores from LeetCode API"""
    if not player.leetcode_username:
        print(f"No LeetCode username for player {player.user.username}")
        return player
        
    print(f"Updating scores for {player.user.username} (LeetCode: {player.leetcode_username})")
    
    # Fetch latest data from LeetCode
    data = fetch_leetcode_profile(player.leetcode_username)
    
    if "error" in data:
        print(f"Error updating {player.user.username}: {data['error']}")
        return player
    
    # Update player fields
    player.total_solved = data.get("totalSolved", 0)
    player.easy_solved = data.get("easySolved", 0)
    player.medium_solved = data.get("mediumSolved", 0)
    player.hard_solved = data.get("hardSolved", 0)
    player.total_score = data.get("score", 0)
    player.leetcode_score = data.get("score", 0)
    player.ranking = data.get("ranking")
    player.reputation = data.get("reputation", 0)
    
    # Save the updated player
    player.save()
    
    print(f"Updated {player.user.username}: Score={player.total_score}, Solved={player.total_solved}")
    
    return player


def refresh_leaderboard_rankings():
    """Refresh rankings for all players"""
    players = Player.objects.all().order_by('-total_score', 'created_at')
    
    for idx, player in enumerate(players, 1):
        player.rank = idx
        player.save(update_fields=['rank'])
    
    print(f"Updated rankings for {len(players)} players")