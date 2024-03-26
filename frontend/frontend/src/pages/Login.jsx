import React, {useState} from 'react'
import axios from 'axios'
import './Login.css'
import { useNavigate } from 'react-router-dom'

const Login = () => {

  const [userDetails, setUserDetails] = useState({
    email : '',
    password : '',
  });

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    if (!userDetails.email || !userDetails.password) {
        alert('Please fill all the fields');
        return;
    }
    e.preventDefault();
    try {
        const response = await axios.post('http://localhost:8080/login', userDetails);
        console.log(response);
        if(response.data.token)
            localStorage.setItem('token', response.data.token);
            localStorage.setItem('authorId', response.data.id);
        navigate('/dashboard');
    }
    catch (err) {
        console.log(err);
    }
    
    
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUserDetails({
        ...userDetails,
        [name] : value,
    });
    console.log(userDetails);
  };
  
  return (
    <div className='login-container'>
      <div className='login-header'>
        <h1>
            LOGIN
        </h1>
      </div>
      <div className='login-inputs'>
      
            <input 
                type='text' 
                name = 'email'
                placeholder='EMAIL' 
                className='login_input'
                onChange={handleChange}
                required
            />  
            <input 
                type='password'
                name = 'password' 
                placeholder='PASSWORD' 
                className='login_input'
                onChange={handleChange}
                required
            />
        <button onClick={handleSubmit}>
            SUBMIT
        </button>
      </div>
      <div className='login-footer'>

      </div>
    </div>
  )
}

export default Login