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
                const response  =await axios.get(`http://localhost:8080/demo/user/${id}`,
                 {
                    headers: {
                        Authorization: 'Bearer ' + localStorage.getItem('token')
                    }
                })
                
                  
                console.log(response.data)
                setuserData(response.data)
            } catch(error){
                //print("invalid user")
            }
        }

        fetchUserData();

    },[]);





  return (
    
    
    <div className='personal-container'>
         <Navbar />

    <div className='mainbody'>

        <div className='sidebar'></div>

        <div className='details--container'>
            <h1>PERS<span className='o'>O</span>NAL</h1>
            <div className='information--container'>
            <div className='details'>
            
                <p className='information'>{userData.name}</p>
                <p className='information'>{userData.email}</p>
                <p className='information'>{userData.altEmail}</p>
                <p className='information'>{userData.phone}</p>
            </div>
                
                <a href = {`/editdetails`}><button className='edit'>EDIT</button></a>
                <a href= {`/`}><button className='edit'>LOG</button></a>
            </div>
        </div>

        <div className='sidebar'></div>
        
        
        
       
    </div>   
   
   <Footer />
    </div>
    
    
  )
}

export default Personal