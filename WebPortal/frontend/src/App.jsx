import './App.css'

import { Routes,Route } from 'react-router-dom'

import Home from './pages/Home/Home.jsx'
import SignUpPage from './pages/Sign/SignUpPage.jsx'
import SignInPage from './pages/Sign/SignInPage.jsx'

import { ToastContainer } from 'react-toastify'

function App() {

  return (
   <div>
     <Routes>
        <Route path='/'element={<Home/>} />
        <Route path='/SignUp'element={<SignUpPage/>} />
        <Route path='/SignIn'element={<SignInPage/>} />
      </Routes>
      <ToastContainer position="top-center" autoClose={3000} />
   </div>
  )
}

export default App
