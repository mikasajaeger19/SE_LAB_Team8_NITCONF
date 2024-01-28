import React from 'react'
import { useState,useEffect } from 'react'
import axios from 'axios'
import {Link} from 'react-router-dom'



export const Personal = () => {

    const [userData,setuserData]=useState({})
    

    useEffect( () => {

        //fetch author user id from local data (implement encryption later?)
        


        const fetchUserData = async() =>{
            try {
                const response  =await axios.get(`http://localhost:3000/api/users/${userId}`)
                console.log(response.data)
                setuserData(response.data)
            } catch(errror){
                //print("invalid user")
            }
        }

        fetchUserData();

    },[]);

    

  return (
    <>
    <Navbar />
    <div className='container'>
    <div className='div'>
        <h1 className = 'heading'>Account Details</h1>
        <div className = 'underlinecont'>
        <p className = 'underline'> </p>
        </div>
        <img  className = 'pfp' src = {userData.pfp} alt = 'not loaded'/>
        <p className='information'>User : {userData.username}</p>
        <p className='information'>Email : {userData.email}</p>
        <p className='information'>Name : {userData.fname+' '+userData.lname}</p>
        <p className='information'>Phone : {userData.phone}</p>
        <p className='information'>Alt email : {userData.alt}</p>
        <h2><Link className = 'homebuttom' to = '/home'>Go Home</Link></h2>
        <h2><Link className = 'editpage' to = '/editdetails'>Edit Details</Link></h2>
        
       
        
    </div>
    </div>
    </>
    
  )
}