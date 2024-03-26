import React, { useState, useMemo, useEffect} from 'react';
import { useTable } from 'react-table';
import { useNavigate } from 'react-router-dom';
import Navbar from './Navbar.jsx';
 import axios from 'axios'; // uncomment if you need axios
 import Footer from './Footer.jsx';

import './Dashboard.css';


import dummy from '../dummy.json';
import Card from '../components/Card.jsx';

const  Dashboard = () => {
    const [userData, setUserData] = useState([]);
    const history = useNavigate();

    const data = userData;
    const authorId = localStorage.getItem('authorId');

    
    useEffect(() => {
        const fetchUserData = async () =>{
            try {

                const response  = await axios.get(`http://localhost:8080/paper/author/${authorId}`, {
                    headers: {
                        Authorization: 'Bearer ' + localStorage.getItem('token')
                    }
                })  
                console.log(response.data)
                setUserData(response.data)
            } catch(errror){
                //print("invalid user")
            
            }
        }

        fetchUserData();

    },[]);


console.log(authorId)


const handleReupload = () => {
    history('/reupload')
}

  

   

    
    

    
    return (

        <div className='dashboard-container'>
             <Navbar />
             <div className='dashboard-content'>
                <div className='dashboard-sidebar'></div>
                <div className='dashboard-details'>
                <h1 className='dashboard--header'>DASHB<span className='dashboard-o'>O</span>ARD</h1>
                <div className='dashboard-cards'>{userData ? (userData.map((data) => {
                    return (
                        <Card data={data} />
                    )
                })) : <h1>NO PAPER UPLOADED</h1>
            }
                </div>
                </div>
                <div className='dashboard-sidebar'></div>
            </div>      
            <Footer />
            
            </div>
    );
}

export default Dashboard