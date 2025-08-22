import requests

def fetch_github_stats(username):
    url = f"https://api.github.com/users/{username}"
    try:
        response = requests.get(url, timeout=5)
        response.raise_for_status()
        data = response.json()
        return {
            "followers": data.get("followers", 0),
            "repos": data.get("public_repos", 0),
        }
    except Exception as e:
        return {"error": str(e)}

def fetch_leetcode_stats(username):
    url = "https://leetcode.com/graphql"
    query = {
        "query": f"""
            query {{
              matchedUser(username: "{username}") {{
                username
                profile {{ ranking reputation }}
                submitStats {{
                  acSubmissionNum {{ difficulty count }}
                }}
              }}
            }}
        """
    }
    try:
        response = requests.post(url, json=query, headers={"Content-Type": "application/json"})
        response.raise_for_status()
        data = response.json()["data"]["matchedUser"]
        total_solved = sum([item["count"] for item in data["submitStats"]["acSubmissionNum"]])
        return {
            "ranking": data["profile"]["ranking"],
            "total_solved": total_solved
        }
    except Exception as e:
        return {"error": str(e)}
