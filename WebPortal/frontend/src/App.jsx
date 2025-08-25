import './App.css'

import { Routes,Route } from 'react-router-dom'

import Home from './pages/Home/Home.jsx'
import SignUpPage from './pages/Sign/SignUpPage.jsx'
import SignInPage from './pages/Sign/SignInPage.jsx'

import { ToastContainer } from 'react-toastify'
import { useState } from 'react'
import { useContext } from 'react'
import { themeContext } from './components/Context/context.js'

function App() {

  const [colo,setColo]=useState("#0f051e")

  return (
   <div style={{background:colo}}>
    <themeContext.Provider value={{colo,setColo}}>
     <Routes>
        <Route path='/'element={<Home/>} />
        <Route path='/SignUp'element={<SignUpPage/>} />
        <Route path='/SignIn'element={<SignInPage/>} />
      </Routes>
      </themeContext.Provider>
      <ToastContainer position="top-center" autoClose={3000} />
   </div>
  )
}

export default App
