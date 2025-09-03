import { useState } from "react";
import RankingBox from "../RankingBox/RankingBox";
import axios from "axios";

const Rankings = () => {
  const [username, setUsername] = useState("");
  const [users, setUsers] = useState([]);

  const handleAddUser = async () => {
    if (username.trim() === "") {return};

    try {
      const res = await axios.get(
        `https://kinkdin.onrender.com/${username}/solved`
      );

      const details= await axios.get(
        `https://kinkdin.onrender.com/${username}`
      );
      
      console.log(res.data);
      console.log(details.data);

      const newUser = {
        name: username,
        easy: res.data.easySolved,
        medium: res.data.mediumSolved,
        hard: res.data.hardSolved,
        img:details.data.avatar
      };

      setUsers((prev) => [...prev, newUser]); // update only after fetching
      setUsername(""); // clear input
      
    } catch (e) {
      console.log("Error:", e.message);
    }
  };

  return (
    <div>
      {/* Input */}
      <div>
        <input
          type="text"
          value={username}
          placeholder="Enter username"
          onChange={(e) => setUsername(e.target.value)}
        />
        <button onClick={handleAddUser}>Add</button>
      </div>

      {/* Spawn components */}
      <div>
        {users.map((user, index) => (
          <RankingBox
            key={index}
            value={{
              img:user.img,
              rank: index + 1,
              name: user.name,
              easy: user.easy,
              medium: user.medium,
              hard: user.hard,
            }}
          />
        ))}
      </div>
    </div>
  );
};

export default Rankings;
