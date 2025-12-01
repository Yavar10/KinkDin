import React from 'react'
import LeaderBoardTitle from '../../components/LeaderboardTitle/LeaderBoardTitle'
import NavBar from '../../components/NavBar/NavBar'
import Podium from "../../components/Bar/Podium"
import Pointcard from "../../components/PointCard/PointCard"
import RankingBox from '../../components/RankingBox/RankingBox'
import Rankings from '../../components/Rankings/Rankings'
const Leaderboard = () => {
  return (
    <div>
    <NavBar/>
   
   {/*  <Podium/> */}
    
    <Rankings/>
    <Pointcard/>
    </div>
  )
}

export default Leaderboard
