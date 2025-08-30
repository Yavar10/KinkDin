import React, { useState } from 'react'
import './NavBar.css'
import sin from "../../assets/s-in.svg"
import sup from "../../assets/s-up.svg"
import sun from '../../assets/sun.svg'
import moon from '../../assets/moon.svg'
import { useNavigate } from 'react-router-dom'

import { themeContext } from '../Context/context'
import { useContext } from 'react'

const NavBar = () => {

  const { colo, setColo } =useContext(themeContext);

  const[theme,setTheme]=useState("dark")
  const[path,setPath]=useState(sun)
    const navigate=useNavigate();

  const sign=(x)=>{
    if(x==="in")
    navigate('/SignIn')
  else if(x==="up")
    navigate('/SignUp')
  else if(x==="home")
    navigate('/')
  }

  const themeHandler=()=>{
    console.log(colo)
    if(theme==="dark")
    {
      setTheme("light")
      setColo("#c6a0ffff")
      setPath(moon)
    }
    
    else
      {setTheme("dark")
        setColo("#0f051e")
    setPath(sun)}
  }

  return (
    <div className='nav'>
        <div onClick={()=>{sign("home")}} className="title">LockdIn</div>
        <div className="btns">
            <button 
  onClick={() => { themeHandler() }} 
  className="themeicon"
>
  <img 
    className={`svg ${theme === "light" ? "rotate" : ""}`} 
    src={path} 
    alt="theme icon" 
  />
</button>
            <div onClick={()=>{sign("in")}} className='s-in'><img className='svg' src={sin} alt=""  /><p className='btext'>Sign In</p></div>
            <div onClick={()=>{sign("up")}} className='s-up'><img className='svg' src={sup} alt="" /><p className='btext'>Sign Up</p></div>
            </div>
    </div>
  )
}

export default NavBar