/* import React from 'react'
import { SubmitHandler, useForm } from 'react-hook-form';
import { FaUserCircle } from "react-icons/fa";
import { MdOutlineRemoveRedEye } from "react-icons/md";


interface FormData {
  FullName : string;
  UserName ?: string;
  CollegeEmail : string;
  LeetCodeUserName : string;
  GithubUserName : string;
  Password : string;
}

const SignUp = () => {

  const {
    register,
    handleSubmit,
    formState : {errors, isSubmitting}
  } = useForm<FormData>()

  const onSumbit : SubmitHandler<FormData> = (data) => {
    console.log(data);
  }


  return (
    <div className=' flex items-center
    justify-center border-2 h-screen 
    '>
      <div className="bg-[#0c0b0e]  p-8 rounded-2xl shadow-lg w-96">
        <div className="text-2xl flex flex-col justify-center items-center font-semibold text-center mb-6 text-white">
         <FaUserCircle  style = {{color : '#5D3FD3'}}
         className='size-11'/>

         <h2 className="font-semibold text-xl p-3 ">
          Join KinkDin
         </h2>
         <h3 className="text-sm font-thin">
                Create your account and get started
         </h3>
        </div>
        
        <div className="flex justify-between gap-5 text-white">
          <div className="flex flex-col justify-between text-xs">
            Full Name
            <input type="text"
             className='text-white w-35 text-sm p-1 
             rounded placeholder:text-xs placeholder-gray-500 mt-1
             outline-none focus:ring-1 focus:ring-[#592A8A]
             bg-[#110f12]
             
             '
             placeholder='Enter Name'
            />
            </div>
            
          <div className="flex flex-col justify-between text-xs">
            UserName
            <input type="text"
             className='text-white w-35 text-sm p-1 placeholder:text-xs placeholder-gray-500 mt-1
             rounded outline-none focus:ring-1 focus:ring-[#592A8A] bg-[#110f12]'
             placeholder='Enter Username...'
             />
          </div>
        </div>


        <div className='text-white text-xs mt-4'>
          <p>Email</p>
          <input type="text"
            className='text-white w-full text-sm rounded p-1 placeholder:text-xs placeholder-gray-500 mt-1
            outline-none focus:ring-1 focus:ring-[#592A8A]
             bg-[#110f12]'
            placeholder='Enter Email...'
          />
        </div>

        <div className='text-white text-xs mt-4'>
          <p>LeetCode UserName</p>
          <input type="text"
            className='text-white w-full text-sm rounded p-1 placeholder:text-xs placeholder-gray-500 mt-1
            bg-[#110f12]
            outline-none focus:ring-1 focus:ring-[#592A8A]'
            placeholder='Enter username...'
          />
        </div>

        <div className='text-white text-xs mt-4'>
          <p>Password</p>
          <div className=" relative flex">
            <input type="password"
            className='text-white w-full text-sm p-1 rounded  placeholder:text-xs placeholder-gray-500 mt-1
            outline-none bg-[#110f12] focus:ring-1 focus:ring-[#592A8A]'
            placeholder='Create a strong password...'
          />
          <MdOutlineRemoveRedEye className='absolute right-3 top-1/2 -translate-y-1/2 cursor-pointer' />
          </div>
        </div>

          <div className='text-white text-xs mt-4'>
          <p>Confirm Password</p>
          <div className="relative flex">
              <input type="password"
              className='text-white w-full text-sm p-1 rounded placeholder:text-xs bg-[#110f12] placeholder-gray-500 mt-1
              outline-none focus:ring-1 focus:ring-[#592A8A]'
              placeholder='Confirm your password...'
            />
            <MdOutlineRemoveRedEye className='absolute right-3 top-1/2 -translate-y-1/2 cursor-pointer' />

          </div>
         
        </div>

        <div className='flex mt-4 text-xs justify-between'>
          <input type="radio"
          style={{color : '#5D3FD3'}}
            className='w-3 h-3'
          />
          <p className='mr-14 text-white text-[10px]'
          >I agree to the 
          <span style={{color : '#592A8A'}}> Terms Of Service </span>and 
          <span style={{color : '#592A8A'}}> Privacy Policy</span></p>
        </div>
          <button
             className="bg-[#5D3FD3] text-white w-full py-2 rounded-lg font-medium hover:bg-[#4c32aa] cursor-pointer mt-6"
            >
            Create Account
          </button>

          <div className="flex text-white text-xs justify-center items-center mt-2">
            <p>Already have an account ? <span style={{color : '#592A8A'}}>SignIn</span></p>
          </div>                
      </div>
      
    </div>
  )
}

export default SignUp */