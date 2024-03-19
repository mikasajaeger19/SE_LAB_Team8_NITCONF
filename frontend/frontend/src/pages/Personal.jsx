import React from 'react'
import { useState,useEffect } from 'react'
import axios from 'axios'
import {Link} from 'react-router-dom'
import Navbar from './Navbar.jsx'
import './Personal.css'
import Footer from './Footer.jsx'


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

    <div className='mainbody'>

        <div className='sidebar'></div>

        <div className='details--container'>
            <h1>PERS<span className='o'>O</span>NAL</h1>
            <div className='information--container'>
            <div className='details'>
            
                <p className='information'>MADHAV{userData.name}</p>
                <p className='information'>madhav@gmail.com{userData.email}</p>
                <p className='information'>alt@gmail.com{userData.altEmail}</p>
                <p className='information'>8861796877 {userData.phone}</p>
            </div>
                
                <a href = {`/editdetails`}><button className='edit'>EDIT</button></a>
            </div>
        </div>

        <div className='sidebar'></div>
        
        
        
       
    </div>   
   
   <Footer />
    </div>
    
    
  )
}

export default Personal