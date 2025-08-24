import React from 'react'
import './TitleScreen.css'
import trophy from "../../assets/tro.svg"
import codeslash from "../../assets/cod.svg"
const TitleScreen = () => {
  return (
    <div className='title-box'>
        <div className="title"><p>KinkDin</p></div>
        <div><p className='title-para'>
            Compete in the ultimate Leetcode championship. Track your progress, climb the rankings, and prove your coding supremacy.
            </p></div>
        <div className="Tbtns">
            <div className='view-leaderboard'><img className='svg' src={trophy} alt="" /><p className='btext'>View Leaderboard</p></div>
            <div className='gettingStarted'><img className='svg' src={codeslash} alt="" /><p className='btext'>Get Started</p></div>
            </div>
    </div>
  )
}

export default TitleScreen