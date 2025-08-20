import './About.css'
function About() {

  return (
   <div className = "mainDiv">
    <div className = "box">
      <div className = "pic"></div>
      <div className = "title">Live Rankings</div>
      <div className = "text">
        <div>Track progress across Leetcode's </div>
        <div>weekly programming </div>
        <div>challenges</div>
    </div>
    </div>
    <div className = "box">
      <div className = "pic"></div>
      <div className = "title">Smart Points</div>
      <div className = "text">
        <div>Advanced scoring system that</div>
        <div>rewards consistency and</div>
        <div>difficulty</div>
      </div>
    </div>
    <div className = "box">
      <div className = "pic"></div>
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