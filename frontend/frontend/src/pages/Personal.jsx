import React from 'react'
import { useState,useEffect } from 'react'
import axios from 'axios'
import {Link} from 'react-router-dom'
import Navbar from './Navbar.jsx'
import './Personal.css'


export const Personal = () => {

    const [userData,setuserData]=useState([])
    const id = localStorage.getItem('authorId')
    
    useEffect( () => {

        //fetch author user id from local data (implement encryption later?)
        


        const fetchUserData = async() =>{
            try {
                const response  =await axios.get(`http://localhost:8080/demo/user/${id}`)
                console.log(response.data)
                setuserData(response.data)
            } catch(error){
                //print("invalid user")
            }
        }

        fetchUserData();

    },[]);





  return (
    
    
    <div className='container'>
         <Navbar />
    <div className='div'>
        <h1 className = 'heading'>Account Details</h1>
        <div className = 'underlinecont'>
        <p className = 'underline'> </p>
        </div>
        <p className='information'>Name : {userData.name}</p>
        <p className='information'>Email : {userData.email}</p>
        <p className='information'>Phone :  8861796877 {userData.phone}</p>
        <p className='information'>Alt email : {userData.altEmail}</p>
        <h2><Link className = 'homebuttom' to = '/dashboard'>Go to Dashboard</Link></h2>
        <h2><Link className = 'editpage' to = '/editdetails'>Edit Details</Link></h2>
        
       
    </div>   
    </div>
    
    
  )
}

export default Personal