import React from 'react'
import LeaderBoardTitle from '../../components/LeaderboardTitle/LeaderBoardTitle'
import NavBar from '../../components/NavBar/NavBar'
import Podium from "../../components/Bar/Podium"
import Pointcard from "../../components/PointCard/PointCard"
import RankingBox from '../../components/RankingBox/RankingBox'
import Rankings from '../../components/Rankings/Rankings'
const Leaderboard = () => {
  return (
    <div style={{height:"200vh",overflowY:"hidden"}}>
    <NavBar/>
    <LeaderBoardTitle/>
    <Pointcard/>
    <Podium/>
    <Rankings/>
    <RankingBox value={{rank:1,name:"Zuri",easy:139,medium:102,hard:5}}/>
    </div>
  )
}

export default Leaderboard
