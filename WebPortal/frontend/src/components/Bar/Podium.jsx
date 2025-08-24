import './Podium.css'
import gold from '../../assets/gold.svg'
import sil from '../../assets/silver.svg'
import bron from '../../assets/bronze.svg'
import lig from '../../assets/lig.svg'
import Bar from "./Bar.jsx"
function Podium() {

  return (
   <div className="finalPodium">
    <Bar pos={sil} name="user_name" user="@user_id" icon={lig} sco="12345" place="2" high={175}/>
    <Bar pos={gold} name="user_name" user="@user_id" icon={lig} sco="12345" place="1" high={250}/>
    <Bar pos={bron} name="user_name" user="@user_id" icon={lig} sco="12345" place="3" high={125}/>
   </div>
  )
}

export default Podium