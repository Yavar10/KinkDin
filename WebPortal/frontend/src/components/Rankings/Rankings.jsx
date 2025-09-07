import { useState, useMemo } from "react"; 
import RankingBox from "../RankingBox/RankingBox";
import axios from "axios";

import arr from "../../assets/arrowW.svg"

const Rankings = () => {
  const [username, setUsername] = useState("");
  const [users, setUsers] = useState([]);

  const handleAddUser = async () => {
    if (username.trim() === "") {
      return;
    }

    if (users.some(user => user.name.toLowerCase() === username.toLowerCase())) {
        alert("User is already in the list!");
        setUsername("");
        return;
    }

    try {
      const res = await axios.get(
        `https://kinkdin.onrender.com/${username}/solved`
      );
      const details = await axios.get(
        `https://kinkdin.onrender.com/${username}`
      );

      const newUser = {
        name: username,
        easy: res.data.easySolved,
        medium: res.data.mediumSolved,
        hard: res.data.hardSolved,
        img: details.data.avatar,
      };

      setUsers((prev) => [...prev, newUser]);
      setUsername("");
    } catch (e) {
      console.log("Error:", e.message);
      alert("Failed to fetch user data. Please check the username.");
    }
  };

  const sortedUsers = useMemo(() => {
    return [...users].sort((a, b) => {
      const locksB = (b.easy * 50) + (b.medium * 100) + (b.hard * 200);
      const locksA = (a.easy * 50) + (a.medium * 100) + (a.hard * 200);

      return locksB - locksA;
    });
  }, [users]); 
  return (
    <div>
      <p style={{color:"purple",textShadow:"0px 0px 10px purple",fontSize:"65px",width:"100%",alignItems:"center",justifyContent:"center",display:"flex"}}>Rankings</p>
      <div style={{display:"flex",alignItems:"center",justifyContent:"center"}}>
        <input style={{color:"white",width:"50%",height:"30px",borderRadius:"5px",backdropFilter:"blur 10px",opacity:"80%",backgroundColor:"black",border:"purple solid 2px",boxShadow:"purple 0px 0px 10px"}}
          type="text"
          value={username}
          placeholder="Enter username"
          onChange={(e) => setUsername(e.target.value)}
          onKeyPress={(event) => { if (event.key === 'Enter') handleAddUser(); }}
        />
        <button  style={{display:"flex",alignItems:"center",justifyContent:"center",width:"50px" ,height:"36px",borderRadius:"5px",backdropFilter:"blur 10px",opacity:"80%",backgroundColor:"black",border:"purple solid 2px",boxShadow:"purple 0px 0px 10px"}} onClick={handleAddUser}> <img style={{height:"20px"}} src={arr} alt="" /></button>
     
      </div>

  
      <div>
       
        {sortedUsers.map((user, index) => (
          <RankingBox
            key={user.name}
            value={{
              img: user.img,
              rank: index + 1, 
              name: user.name,
              easy: user.easy,
              medium: user.medium,
              hard: user.hard,
              locks: (user.easy * 50) + (user.medium * 100) + (user.hard * 200),
            }}
          />
        ))}
      </div>
    </div>
  );
};

export default Rankings;