import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './login.css'
const LoginForm = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    email: '',
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
    axios
      .post('http://localhost:8080/login', formData)
      .then((res) => {
        console.log(res);
        localStorage.setItem('token', res.data.token);
        localStorage.setItem('authorId',res.data.id)
        navigate("/dashboard");
      })
      .catch((err) => {
        console.log(err);
      });

  };


  

  return (
    <div className="container">
      <div className="container--login">
      <form onSubmit={handleSubmit}>
        <label>
          Email:
          <div className="login">    
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
          />
          </div>
        </label>
        <br />
        <label>
          Password:
          <div className="login">  
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
          </div>
        </label>
        <br />
        <button type="submit">Login</button>
      </form>
    </div>
    </div>
  );
};

export default LoginForm;
