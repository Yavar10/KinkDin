import React from 'react'
import './IDentryBox.css'
import lc from '../../assets/download.png'
import gh from '../../assets/gt.png'

const IDentryBox = () => {
  return (
    <div className='iDentryBox'>
        <div className="entryCont">
            <p>Get Started</p>
            <div className='entryBox'>
               
               <div className='Entry leet' >
                <input placeholder='Enter Leetcode id' type="text" name="" id="" />
               <button>Submit</button>
                </div>

               <div className='Entry git'>
                <input placeholder='Enter Github id' type="text" name="" id="" />
               <button>Submit</button>
                </div>
        <img className='lo' src={lc} alt="" />
        <img className='gt' src={gh} alt="" />
            </div>
        </div>
    </div>
  )
}

export default IDentryBox