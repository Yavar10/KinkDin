import './App.css'
import NavBar from './components/NavBar/NavBar'
import TitleScreen from './components/TitleScreen/TitleScreen'
import About from "./components/About/About"
import Bar from "./components/Bar/Bar.jsx"
import SignUp from "./components/SignUpForm/SignUp"

function App() {

  return (
   <div>
    <NavBar/>
    <TitleScreen/>
    <About/>
    <Bar pos={gold} name="user_name" user="@user_id" icon={lig} sco="12345" place="1" />
    <SignUp/>
   </div>
  )
}

export default App
