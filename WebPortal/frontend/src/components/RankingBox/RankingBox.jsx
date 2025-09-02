import React from 'react'

import "./RankingBox.css"

//import yavar from '../../assets/temp pfps/yavar.jpg'

const RankingBox = ({value}) => {
    const {rank,name,easy,medium,hard,img}=value;
    const locks=easy*50+ medium*100 + hard*200;

  return (
    <div>
      <div className='RankBox'>
        <div className='pd'>
        <p>{rank}</p>
        <img className='img-rank' src={img} alt="" />
        <p>{name}</p>
        </div>
        <div className="ques-data">
            <div style={{width:"50px"}} className='easy'>{easy}</div>
            <div style={{width:"50px"}}className='medium'>{medium}</div>
            <div style={{width:"50px"}}className='hard'>{hard}</div>
            <div className='locks'>Locks: {locks}</div>
        </div>
        </div>
      </div>
  )
}

export default RankingBox
