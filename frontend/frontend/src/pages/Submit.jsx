import React from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { useEffect } from 'react';
import './Submit.css';
import emailjs from 'emailjs-com';
import Navbar from './Navbar.jsx';

const Submit = () => {

    const authorId  =localStorage.getItem('authorId')
    let today = new Date();
    const uploadDate = today.getFullYear()+ "-"+ (today.getMonth()+1>=10 ? ( parseInt(today.getMonth()+1)) : ("0" + parseInt(today.getMonth()+1))) +"-"+ (today.getDate()+1>=10 ? ( parseInt(today.getDate()+1)) : ("0" + parseInt(today.getDate()+1)))
    const [submission, setSubmission] = useState({
   
        title: '',
        approved: false,
        shortdesc: '',
        abstractUrl: '',
        tags: '',
        uploadDate: uploadDate,
        authorId : authorId
      });
    
      const navigate = useNavigate();





    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setSubmission((prevData) => ({
          ...prevData,
          [name]: value,
        }));
      };
    
      const handleEditSubmit = async (e) => {
        console.log(submission);
      
        if (
          submission.title === '' ||
          submission.shortdesc === '' ||
          submission.abstractUrl === '' ||
          submission.tags === ''
        ) {
          alert('Please fill in all fields!');
          return;
        }
      
        try {
          console.log(localStorage.getItem('token'))
          const response = await axios.post(
            `http://localhost:8080/paper/add`,
            submission,
            {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token')
              },
            }
          );
          emailjs.sendForm('service_ndmh9hs', 'template_xllzwct', e.target, 'Fu40AmkAkR7VbMApW')
          navigate('/dashboard');
          alert('Abstract submitted successfully!');
        } catch (error) {
          console.error('Error submitting abstract:', error.response.data);
        }
      };


    return (
        <div className='container'>
           <Navbar />
          <h1>Submit Paper</h1>
            <form>
        <h3>Title</h3>
        <input type="text"  name="title"   onChange={handleInputChange} />
        <h3>Short Description</h3>
        <input type="text" name="shortdesc"  onChange={handleInputChange} />
        <h3>Abstract URL</h3>
        <input type="text" name="abstractUrl"  onChange={handleInputChange} />
        <h3>Tags</h3>
        <input type="text" name="tags"  onChange={handleInputChange} />
        <h3></h3>
        <button type="button" onClick={handleEditSubmit}>Submit Abstract</button>
      </form>
        </div>
    );
};

export default Submit;