import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Navbar from './Navbar.jsx';
import axios from 'axios'; 
import './Dashboard.css';
import Card from '../components/Card.jsx';

export const Dashboard = () => {
    const [userData, setUserData] = useState([]);
    const history = useNavigate();
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
            } catch(error){
                console.error(error);
            }
        }
        fetchUserData();
    }, [authorId]);

    const handleReupload = () => {
        // reupload logic here
        localStorage.setItem('paperId', userData.paperId);
        history('/reupload');
    };

    const handleComment = () => {
        // comment logic here
        localStorage.setItem('paperId', userData.paperId);
        history('/comments');
    };

    return (
        <div className='container'>
            <Navbar />
            <h1 className='dashboard--header'>Dashboard</h1>
            {userData.length === 0 ? (
                <h1 className='no--paper'>No papers uploaded</h1>
            ) : (
                userData.map((paper) => (
                    <Card
                        key={paper.paperId} // Add key prop for React list rendering
                        title={paper.title}
                        abstractUrl={paper.abstractUrl}
                        status={paper.approved}
                        uploadDate={paper.uploadDate}
                        paperId={paper.paperId}
                    />
                ))
            )}
        </div>
    );
};

export default Dashboard;
