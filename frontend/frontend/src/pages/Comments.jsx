import React from 'react'
import { useState, useEffect } from 'react';
import axios from 'axios';




const Comments = (props) =>{
    const [userData, setUserData] = useState('');
    const versionId = props.versionId;
    const paperId = props.paperId;
  
    useEffect(() => {
      const fetchUserData = async () => {
        try {
          const response = await axios.get(`http://localhost:8080/comments/version/${versionId}`, {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem('token')
            },
          });
          console.log(response.data!==null ? 'response data is : ' + response.data : 'no data');
          setUserData(response.data);
        } catch (error) {
        setUserData('');
          console.log(error);
        }
      };
  
      fetchUserData();
    }, [paperId,versionId]);
    console.log('paper id is : ' + paperId);
    console.log('version id is : ' + versionId);
    console.log(userData.length !==0 ? 'user data is : ' + userData : 'no userdata');
  
    return (
      <div>
        <h1 className='heading'>Comments</h1>
        <ul className='list-head'>
          {userData && userData.length !==0 && userData !==null? (
            userData.map((comment) => (
              <li key={comment}>{comment}</li>
            ))
          ) : (
            <p>No comments</p>
          )}
        </ul>
      </div>
    );
}

export default Comments;