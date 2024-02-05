import React, { useState, useEffect } from 'react';
//import axios from 'axios';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

import Navbar from './Navbar.jsx';
import './EditDetails.css';


const EditDetails = () => {
  const [editedUserData, setEditedUserData] = useState({
   
    username: '',
    email: '',
    fname: '',
    lname: '',
    phone: '',
    alt: '',
  });

  const navigate = useNavigate();
  /*
  useEffect(() => {
   
    const fetchUserData = async () => {
      try {
        const response = await axios.get('http://localhost:3000/api/users/authorId'); 
        setEditedUserData(response.data);
      } catch (error) {
        console.error('Error fetching user data:', error);
      }
    };

    fetchUserData();
  }, []);
  */

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setEditedUserData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleEditSubmit = async () => {
    try {
     
      //await axios.put(`http://localhost:3000/api/users/${userId}`, editedUserData);
      navigate('/personal');
    
    } catch (error) {
      console.error('Error updating user data:', error);
    }
  };

  return (
    <>
       <Navbar />
      <form>
        <input type="text"  name="username" value={editedUserData.username}  onChange={handleInputChange} />
        <input type="text" name="email" value={editedUserData.email} onChange={handleInputChange} />
        <input type="text" name="fname" value={editedUserData.fname} onChange={handleInputChange} />
        <input type="text" name="lname" value={editedUserData.lname} onChange={handleInputChange} />
        <input type="text" name="phone" value={editedUserData.phone} onChange={handleInputChange} />
        <input type="text" name="alt" value={editedUserData.alt} onChange={handleInputChange} />
        <button type="button" onClick={handleEditSubmit}>Save Changes</button>
      </form>
      <Link to="/personal">Back to Personal Page</Link>
    </>
  );
};

export default EditDetails;
