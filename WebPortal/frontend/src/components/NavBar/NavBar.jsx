import React, { useState } from 'react'
import './NavBar.css'
import sin from "../../assets/s-in.svg"
import sup from "../../assets/s-up.svg"
import sun from '../../assets/sun.svg'
import moon from '../../assets/moon.svg'
const NavBar = () => {

  const[theme,setTheme]=useState("themeicond")
  const[path,setPath]=useState(moon)


  const themeHandler=()=>{
    if(theme==="dark")
    {
      setTheme("light")
      setPath(moon)
    }
    
    else
    {setTheme("dark")
    setPath(sun)}
  }

  return (
    <div className='nav'>
        <div className="title">KinkDin</div>
        <div className="btns">
            <button onClick={()=>{themeHandler()}} className="themeicon"><img className='svg' src={path} alt="" /></button>
            <div className='s-in'><img className='svg' src={sin} alt="" /><p className='btext'>Sign In</p></div>
            <div className='s-up'><img className='svg' src={sup} alt="" /><p className='btext'>Sign Up</p></div>
            </div>
    </div>
  )
}

export default NavBar