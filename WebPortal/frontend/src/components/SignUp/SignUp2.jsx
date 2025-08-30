import React, { useState } from 'react'
import { useForm } from 'react-hook-form';
import { FaUserCircle } from "react-icons/fa";
import { MdOutlineRemoveRedEye } from "react-icons/md";
import { useNavigate } from 'react-router-dom';
import './SignUp2.css';   // <-- external CSS file

const SignUp2 = () => {
  const navigate =useNavigate();

const [showPassword, setShowPassword] = useState(false);
const [showPassword1, setShowPassword1] = useState(false);



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
          <h3>Create your account and get started</h3>
        </div>
        
        {/* Name & Username */}
        <div className="signup-row">
          <div className="signup-field">
            <label>Full Name</label>
            <input type="text" placeholder="Enter Name" />
          </div>
          <div className="signup-field">
            <label>UserName</label>
            <input type="text" placeholder="Enter Username..." />
          </div>
        </div>

        {/* Email */}
        <div className="signup-field full">
          <label>Email</label>
          <input type="text" placeholder="Enter Email..." />
        </div>

        {/* LeetCode Username */}
        <div className="signup-field full">
          <label>LeetCode UserName</label>
          <input type="text" placeholder="Enter username..." />
        </div>

        {/* Password */}
        <div className="signup-field full">
          <label>Password</label>
          <div className="input-with-icon">
            <input style={{width:"97%"}} type={showPassword1 ? "text" : "password"} placeholder="Create a strong password..." />
            <MdOutlineRemoveRedEye onClick={() => setShowPassword1(!showPassword1)} className="eye-icon" />
          </div>
        </div>

        {/* Confirm Password */}
        <div className="signup-field full">
          <label>Confirm Password</label>
          <div className="input-with-icon">
            <input style={{width:"97%"}} type={showPassword ? "text" : "password"} placeholder="Confirm your password..." />
            <MdOutlineRemoveRedEye onClick={() => setShowPassword(!showPassword)} className="eye-icon" />
          </div>
        </div>

        {/* Radio + Terms */}
        <div className="terms">
          <input type="radio" />
          <p>
            I agree to the 
            <span className="highlight"> Terms Of Service </span> and 
            <span className="highlight"> Privacy Policy</span>
          </p>
        </div>

        {/* Button */}
        <button className="signup-btn">Create Account</button>

        {/* Footer */}
        <div className="signup-footer">
          <p>Already have an account? <span onClick={()=>{navigate("/SignIn")}} className="highlight">SignIn</span></p>
        </div>

      </div>
    </div>
  )
}

export default SignUp2;
