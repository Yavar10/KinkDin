import React from 'react'
import { useForm } from 'react-hook-form';
import { FaUserCircle } from "react-icons/fa";
import { MdOutlineRemoveRedEye } from "react-icons/md";
import { useNavigate } from 'react-router-dom';

import "../SignUp/SignUp2.css"

const SignIn = () => {
  const navigate =useNavigate();
 const {
     register,
     handleSubmit,
     formState: { errors, isSubmitting }
   } = useForm();
 
   const onSumbit = (data) => {
     console.log(data);
   }

 
   return (
     <div className="signup-container">
       <div className="signup-card">
         
         {/* Header */}
         <div className="signup-header">
           <FaUserCircle className="signup-icon" />
           <h2>Join KinkDin</h2>
           <h3>Sign into your account and get started</h3>
         </div>
         
         
         {/* Email */}
         <div className="signup-field full">
           <label>Email</label>
           <input type="text" placeholder="Enter Email or Username..." />
         </div>

         {/* Password */}
         <div className="signup-field full">
           <label>Password</label>
           <div className="input-with-icon">
             <input style={{width:"97%"}} type="password" placeholder="Create a strong password..." />
             <MdOutlineRemoveRedEye className="eye-icon" />
           </div>
         </div>
 
 
 
         {/* Button */}
         <button className="signup-btn">SignIn</button>
 
         {/* Footer */}
         <div className="signup-footer">
           <p>Don't have an account? <span onClick={()=>{navigate("/SignUp")}} className="highlight">SignUp</span></p>
         </div>                
 
       </div>
     </div>
   )
}

export default SignIn
