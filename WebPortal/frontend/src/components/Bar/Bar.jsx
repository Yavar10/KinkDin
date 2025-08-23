import "./Bar.css"

function Bar({
    pic,
    pos,
    name,
    user,
    icon,
    sco,
    place,
}){
    return (
        <div className="mainBar">
            <div className="pic">{pic}</div>
            <div className="pos"><img src={pos} alt="position" ></img></div>
            <div className="name">{name}</div>
            <div className="user">{user}</div>
            <div className="pts">
                <div className="icon"><img src={icon} alt="icon" ></img></div>
                <div className="sco">{sco}</div>
            </div>
            <div className="podium">
                <div className="place">{place}</div>
            </div>
        </div>
    )
}

export default Bar