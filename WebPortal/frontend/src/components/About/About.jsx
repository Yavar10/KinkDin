import './About.css'
import cod from '../../assets/cod.svg'
import lig from '../../assets/lig.svg'
import tro from '../../assets/tro.svg'
function About() {

  return (
   <div className = "mainDivAbout">
    <div className = "box">
      <div className = "pic" style={{ backgroundColor: '#a752f7' }}><img src={tro} alt="trophy"></img></div>
      <div className = "title">Live Rankings</div>
      <div className = "text">
        <div>Track progress across Leetcode's </div>
        <div>weekly programming </div>
        <div>challenges</div>
    </div>
    </div>
    <div className = "box">
      <div className = "pic" style={{ backgroundColor: '#5d27cb' }}><img src={lig} alt="trophy"></img></div>
      <div className = "title">Smart Points</div>
      <div className = "text">
        <div>Advanced scoring system that</div>
        <div>rewards consistency and</div>
        <div>difficulty</div>
      </div>
    </div>
    <div className = "box">
      <div className = "pic" style={{ backgroundColor: '#d178ff' }}><img src={cod} alt="trophy"></img></div>
      <div className = "title">Weekly Contests</div>
      <div className = "text">
        <div>Track progress across</div>
        <div>Leetcode's weekly</div>
        <div>programming challenges</div>
      </div>
    </div>
   </div>
  )
}

export default About