import React, {useState} from 'react'
import axios from 'axios'
import './Login.css'

const Login = () => {

  const [userDetails, setUserDetails] = useState({
    email : '',
    password : '',
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        const response = await axios.post('http://localhost:8080/login', userDetails);
        console.log(response);
        if(response.data.token)
            localStorage.setItem('token', response.data.token);
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
                placeholder='EMAIL' 
                className='login_input'
                onChange={handleChange}
                required
            />  
            <input 
                type='text' 
                placeholder='PASSWORD' 
                className='login_input'
                onChange={handleChange}
                required
            />
        <button>
            SUBMIT
        </button>
      </div>
      <div className='login-footer'>

      </div>
    </div>
  )
}

export default Login