import React from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { useEffect } from 'react';

const Submit = () => {


    const [submission, setSubmission] = useState({
   
        title: '',
        approved: false,
        shortdesc: '',
        abstracturl: '',
        tags: '',
        uploadDate: Date.now(),
        authorId : localStorage.getItem('authorId'),
      });
    
      const navigate = useNavigate();





    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setSubmission((prevData) => ({
          ...prevData,
          [name]: value,
        }));
      };
    
      const handleEditSubmit = async () => {
        console.log(submission);
        if (submission.title === '' || submission.shortdesc === '' || submission.abstracturl === '' || submission.tags === '') {
          alert('Please fill in all fields!');
          return;
        }
        try {
         
          //await axios.put(`http://localhost:3000/api/users/${userId}`, submission);
          
          navigate('/dashboard');
          alert('Abstract submitted successfully!')
        
        } catch (error) {
          console.error('Error updating user data:', error);
        }
      };
    






    return (
        <div className='container'>
          <h1>Submit Paper</h1>
            <form>
        <h3>Title</h3>
        <input type="text"  name="title"   onChange={handleInputChange} />
        <h3>Short Description</h3>
        <input type="text" name="shortdesc"  onChange={handleInputChange} />
        <h3>Abstract URL</h3>
        <input type="text" name="abstracturl"  onChange={handleInputChange} />
        <h3>Tags</h3>
        <input type="text" name="tags"  onChange={handleInputChange} />
        <h3></h3>
        <button type="button" onClick={handleEditSubmit}>Submit Abstract</button>
      </form>
        </div>
    );
};

export default Submit;