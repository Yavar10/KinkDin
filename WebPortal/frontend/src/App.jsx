import './App.css'

import { Routes,Route } from 'react-router-dom'

import Home from './pages/Home/Home.jsx'
import SignUpPage from './pages/Sign/SignUpPage.jsx'
import SignInPage from './pages/Sign/SignInPage.jsx'

import { ToastContainer } from 'react-toastify'
import { useState } from 'react'
import { useContext } from 'react'
import { themeContext } from './components/Context/context.js'
import Leaderboard from './pages/Leaderboard/Leaderboard.jsx'
import IDentry from './pages/IDentry/IDentry.jsx'

function App() {

  const [colo,setColo]=useState("#000000ff")

  return (
   <div style={{overflowY:"hidden",background:colo}} >
    <themeContext.Provider value={{colo,setColo}}>
     <Routes>
        <Route path='/'element={<Home/>} />
        <Route path='/SignUp'element={<SignUpPage/>} />
        <Route path='/SignIn'element={<SignInPage/>} />
        <Route path='/Leaderboard'element={<Leaderboard/>} />
        <Route path='/getStarted'element={<IDentry/>} />
      </Routes>
      </themeContext.Provider>
      <ToastContainer position="top-center" autoClose={3000} />
   </div>
  )
}

export default App
