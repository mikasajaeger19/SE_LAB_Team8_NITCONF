import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Comments = ({ versionId }) => {
  const [userData, setUserData] = useState([]);

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/version/${versionId}`, {
          headers: {
            Authorization: `Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYWVlbV9iMjEwNDc0Y3NAbml0Yy5hYy5pbiIsImlhdCI6MTcwNjU5NjMwOCwiZXhwIjoxNzA3NjM1NTM3fQ.SJF7Vapwc6sMO4ouPnRjaDjhf5STQtNlnnRsunxrumk`,
          },
        });
        console.log(response.data);
        setUserData(response.data);
      } catch (error) {
        console.log(error);
      }
    };

    fetchUserData();
  }, [versionId]);

  return (
    <div>
      <h1 className='heading'>Comments</h1>
      <ul className='list-head'>
        {userData && userData[0].comment.length > 0 ? (
          userData.map((comment, index) => (
            <li key={index}>{comment.comment}</li>
          ))
        ) : (
          <p>No comments</p>
        )}
      </ul>
    </div>
  );
};

export default Comments;
