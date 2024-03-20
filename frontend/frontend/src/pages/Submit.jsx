import React from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { useEffect } from 'react';
import './Submit.css';
import emailjs from 'emailjs-com';
import Navbar from './Navbar.jsx';
import Footer from './Footer.jsx';

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
          console.error('Error submitting abstract:', error.response);
        }
      };


    return (
        <div className='submit-container'>
           <Navbar />
          <div className='submit-content'>
            <div className='sidebar-submit'></div>
            <div className='submit-mainbody'>
            <div className='submit-header'>
            <h1 className='submit-text'>SUBMIT</h1>
          <h1 className='abstract-text'>ABSTR<span className='submit-a'>A</span>CT</h1>
          </div>
            <form className='submit-form'>
        <div className="submit-form-top">
        <span>
        <h3>TITLE</h3>
        <input placeholder = 'TITLE' type="text"  name="title"   onChange={handleInputChange} /></span>
        <span>
        <h3>URL</h3>
        <input placeholder = 'URL' type="text" name="abstractUrl"  onChange={handleInputChange} /></span>
        </div>
        <div className="submit-form-bottom">
        <span>
        <h3>DESCRIPTION</h3>
        <input placeholder = 'DESCRIPTION' type="text" name="shortdesc"  onChange={handleInputChange} /></span>
        <span>
        <h3>TAGS</h3>
        <input placeholder = 'TAGS' type="text" name="tags"  onChange={handleInputChange} /></span>
       
        </div>
        <button className ='submit-button' type="button" onClick={handleEditSubmit}>SUBMIT</button>
      </form>
            </div>
          <div className='sidebar-submit'></div>
          </div>
      <Footer />
        </div>
    );
};

export default Submit;