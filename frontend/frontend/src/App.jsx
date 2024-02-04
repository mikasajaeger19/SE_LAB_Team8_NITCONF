
import React from 'react';
import { BrowserRouter,Routes,Route } from 'react-router-dom'
import Dashboard from './pages/Dashboard.jsx'
import Personal from './pages/Personal.jsx'
import Comments from './pages/Comments.jsx'
import EditDetails from './pages/EditDetails.jsx'
import Reupload from './pages/Reupload.jsx'
import Navbar from './pages/Navbar.jsx'
import Submit  from './pages/Submit.jsx';
import Home from './pages/Home.jsx';
import LoginForm from './pages/Login.jsx';
import Register from './pages/Register.jsx';
import Version from './pages/Version.jsx';


function App() {
  return (
    <BrowserRouter>

    <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/login" element={<LoginForm/>}/> 
        <Route path="/register" element={<Register />}/>
        <Route path="/personal" element={<Personal/>}/>
        <Route path="/dashboard" element={<Dashboard/>}/>
        <Route path = "/comments" element={<Comments/>}/>
        <Route path = "/editdetails" element={<EditDetails/>}/>
        <Route path = "/reupload" element={<Reupload/>}/>
        <Route path = "/submit" element={<Submit/>}/>
        <Route path = "/versions" element={<Version/>}/>
      </Routes>
    </BrowserRouter>
    
  );
}

export default App;
