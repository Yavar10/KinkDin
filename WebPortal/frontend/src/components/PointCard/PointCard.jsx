import './PointCard.css'
import lig from '../../assets/lig.svg'

function PointCard(){
    return (
        <div className="mainDiv">
            <div className="heading">
                <div className="lig"><img src={lig} alt="trophy"></img></div>
                <div className="word">Point System</div>
            </div>
            <div className="content">
                <div className="cardbox">
                    <div className="easy">Easy</div>
                    <div className="pointEasy">50 pts</div>
                </div>
                <div className="cardbox">
                    <div className="medium">Medium</div>
                    <div className="pointMedium">100 pts</div>
                </div>
                <div className="cardbox">
                    <div className="hard">Hard</div>
                    <div className="pointHard">200 pts</div>
                </div>
            </div>
        </div>
    )
}

export default PointCard