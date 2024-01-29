import React from 'react';
import { useState, useEffect } from 'react';    

const Comments = () => {


    const paperId = localStorage.getItem('paperId')
    const [userData,setuserData]=useState({})
    
    /*
    useEffect( () => {

        //fetch author user id from local data (implement encryption later?) and change the paper id to call comments into userData
        


        const fetchUserData = async() =>{
            try {

                const response  =await axios.get(`http://localhost:3000/api/users/${paperId}`)
                console.log(response.data)
                setuserData(response.data)
            } catch(errror){
                //print("invalid user")
            }
        }

        fetchUserData();

    },[]);
    */
    return (
        <div>
            <h1 className='heading'>Comments</h1>
            <ul className='list-head'>
            {userData.comments && userData.comments.length > 0 ? (userData.comments.map((comment,index) => {
                <li key = {index}>{comment}</li>
            })) : (<p>No comments</p>)}
            </ul>
           
            
            
        </div>
    );
};

//commit message
export default Comments;