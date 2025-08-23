import React from 'react'
import './NavBar.css'
import sin from "../../assets/s-in.svg"
import sup from "../../assets/s-up.svg"
const NavBar = () => {
  return (
    <div className='nav'>
        <div className="title"><p>KinkDin</p></div>
        <div className="btns">
            <div className='themeicon'><img className='svg' src={sin} alt="" /></div>
            <div className='s-in'><img className='svg' src={sin} alt="" /><p className='btext'>Sign In</p></div>
            <div className='s-up'><img className='svg' src={sup} alt="" /><p className='btext'>Sign Up</p></div>
            </div>
    </div>
  )
}

export default NavBar