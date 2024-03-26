import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

import emailjs from 'emailjs-com';
import './Register.css';

const Register = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    altEmail: '',
    password: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
    console.log(formData);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Form data submitted:', formData);
    // You can add logic here to send the form data to your server for authentication
    axios.post('http://localhost:8080/register', formData).then((res) => {
      console.log(res);
      localStorage.setItem('token', res.data.token);
      localStorage.setItem('authorId',(res.data.id))
      //emailjs.sendForm('service_ndmh9hs', 'template_kt5cpce', e.target, 'Fu40AmkAkR7VbMApW')

      navigate("/dashboard")

    }
    ).catch((err) => {
      console.log(err);
    });

  };


 

  return (
    <div className='register-container'>
    <form className ='register-form' onSubmit={handleSubmit}>
    
        <input
          type="text"
          name="name"
          placeholder='NAME'
          value={formData.name}
          onChange={handleChange}
        />

        <div className='register-emails'>
        <input
          type="email"
          name="email"
          placeholder='EMAIL'
          value={formData.email}
          onChange={handleChange}
        />
      
        
    
       
        <input
          type="email"
          name="altEmail"
          placeholder='ALT EMAIL'
          value={formData.altEmail}
          onChange={handleChange}
        />
      
      </div>
     
   
        
        <input
          type="password"
          name="password"
          placeholder='PASSWORD'
          value={formData.password}
          onChange={handleChange}
        />
    
      
      <button type="submit">SUBMIT</button>
    </form>
    <h1>REGISTER</h1>
    </div>
  );
};

export default Register;
