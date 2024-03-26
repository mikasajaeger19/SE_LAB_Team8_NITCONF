import { useNavigate } from "react-router-dom";
import React from 'react';
import Footer from './Footer.jsx';
import './Home.css';
import Login from './Login.jsx';
import Register from './Register.jsx';



const Home = () => {
    const navigate = useNavigate();
    const signUpHandler = () => {
        navigate('/register');
    }
    const loginHandler = () => {
        navigate('/login');
    }

    return (
        <div className="home--container">
            <div className = 'container-top'>
                <img src = "./NITC.svg" alt="logo" />
                <h1 className="nitconf">NITC<span className="home-o">O</span>NF</h1>

            </div>
            <div className="container-bottom">
                <span className="login"><Login /></span>
                <span className="register"><Register /></span>
            </div>

        <span className="footer"><Footer/></span>
        </div>
        
    )
}

export default Home;