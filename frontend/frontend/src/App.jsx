
import React from 'react';
import { BrowserRouter,Routes,Route } from 'react-router-dom'
import Dashboard from './pages/Dashboard.jsx'
import Personal from './pages/Personal.jsx'
import Comments from './pages/Comments.jsx'
import EditDetails from './pages/EditDetails.jsx'
import Reupload from './pages/Reupload.jsx'
import Navbar from './pages/Navbar.jsx'


function App() {
  return (
    <BrowserRouter>
    <Navbar />
    <Routes>
        <Route path="/personal" element={<Personal/>}/>
        <Route path="/dashboard" element={<Dashboard/>}/>
        <Route path = "/comments" element={<Comments/>}/>
        <Route path = "/editdetails" element={<EditDetails/>}/>
        <Route path = "/reupload" element={<Reupload/>}/>
      </Routes>
    </BrowserRouter>
    
  );
}

export default App;
