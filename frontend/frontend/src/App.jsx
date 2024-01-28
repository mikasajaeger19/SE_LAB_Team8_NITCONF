
import React from 'react';
import { BrowserRouter,Routes,Route } from 'react-router-dom'
import Dashboard from './pages/Dashboard.jsx'
import Personal from './pages/Personal.jsx'

function App() {
  return (
    <BrowserRouter>
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
