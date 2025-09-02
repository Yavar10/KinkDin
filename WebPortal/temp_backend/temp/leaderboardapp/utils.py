import requests

def fetch_leetcode_profile(username):
    if not username:
        return {"error": "No username"}
    
    query = """
    query($username: String!) {
        matchedUser(username: $username) {
            username
            submitStatsGlobal {
                acSubmissionNum {
                    difficulty
                    count
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
            }
        )
        
        if response.status_code != 200:
            return {"error": f"HTTP {response.status_code}: {response.text[:100]}"}
        
        data = response.json()
        
        if "errors" in data:
            return {"error": data["errors"][0]["message"]}
        
        if not data.get("data", {}).get("matchedUser"):
            return {"error": "User not found"}
            
        user_data = data["data"]["matchedUser"]
        submissions = user_data["submitStatsGlobal"]["acSubmissionNum"]
        
        easy = next((s["count"] for s in submissions if s["difficulty"] == "Easy"), 0)
        medium = next((s["count"] for s in submissions if s["difficulty"] == "Medium"), 0)
        hard = next((s["count"] for s in submissions if s["difficulty"] == "Hard"), 0)
        total = easy + medium + hard
        
        return {
            "totalSolved": total,
            "easySolved": easy,
            "mediumSolved": medium,
            "hardSolved": hard,
            "score": easy + (medium * 3) + (hard * 5)
        }
        
    except Exception as e:
        return {"error": f"Request failed: {str(e)}"}

def fetch_leetcode_contest_info(username):
    return {"error": "Not implemented"}

def fetch_skill_stats(username):
    return {"error": "Not implemented"}

def update_player_scores(player):
    if not player.leetcode_username:
        return player
        
    data = fetch_leetcode_profile(player.leetcode_username)
    if "error" in data:
        print(f"Error updating {player.user.username}: {data['error']}")
        return player
    
    player.total_solved = data.get("totalSolved", 0)
    player.easy_solved = data.get("easySolved", 0)
    player.medium_solved = data.get("mediumSolved", 0)
    player.hard_solved = data.get("hardSolved", 0)
    player.total_score = data.get("score", 0)
    player.save()
    
    return player