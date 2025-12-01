import { useState, useMemo, useEffect } from "react"; 
import RankingBox from "../RankingBox/RankingBox";
import axios from "axios";


import arr from "../../assets/arrowW.svg"

const Rankings = () => {
  const [username, setUsername] = useState("");
  const [users, setUsers] = useState([]);
 
 const load = ()=> { 
  for(let i=0;i<2;i++)
  {
    setUsername(arr[i])
    console.log(arr[i])
    handleAddUser();
  }
}

/* useEffect(() => {
  return () => {
    load()
    console.log(username)
  };
}, []); */

   const handleAddUser2 = async () => {
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

useEffect(() => {
  const arr = ["zuri10","divyanshi_dhangar2005","Syed_Ali_Raza786","IdPoTqX4HA","Aditi_singh16","Kratikajaiswal_25"];

  arr.forEach(name => handleAddUser(name));
}, []);



  const handleAddUser = async (uname = username) => {
  if (uname.trim() === "") return;

  if (users.some(user => user.name.toLowerCase() === uname.toLowerCase())) {
    alert("User is already in the list!");
    return;
  }

  try {
    const res = await axios.get(
      `https://kinkdin.onrender.com/${uname}/solved`
    );
    const details = await axios.get(
      `https://kinkdin.onrender.com/${uname}`
    );

    const newUser = {
      name: uname,
      easy: res.data.easySolved,
      medium: res.data.mediumSolved,
      hard: res.data.hardSolved,
      img: details.data.avatar,
    };

    setUsers(prev => [...prev, newUser]);
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
          onKeyUp={(event) => { if (event.key === 'Enter') handleAddUser2(); }}
        />
        <button  style={{display:"flex",alignItems:"center",justifyContent:"center",width:"50px" ,height:"36px",borderRadius:"5px",backdropFilter:"blur 10px",opacity:"80%",backgroundColor:"black",border:"purple solid 2px",boxShadow:"purple 0px 0px 10px"}} onClick={handleAddUser2}> <img style={{height:"20px"}} src={arr} alt="" /></button>
     
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