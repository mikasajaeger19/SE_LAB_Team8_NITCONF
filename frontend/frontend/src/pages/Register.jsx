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
    navigate("/dashboard")
  };


 

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Name:
        <input
          type="text"
          name="name"
          value={formData.name}
          onChange={handleChange}
        />
      </label>
      <br />
      <label>
        Email:
        <input
          type="email"
          name="email"
          value={formData.email}
          onChange={handleChange}
        />
      </label>
      <br />
      <label>
        Alternative Email:
        <input
          type="email"
          name="altEmail"
          value={formData.altEmail}
          onChange={handleChange}
        />
      </label>
      <br />
      <label>
        Password:
        <input
          type="password"
          name="password"
          value={formData.password}
          onChange={handleChange}
        />
      </label>
      <br />
      <button type="submit">Submit</button>
    </form>
  );
};

export default Register;
