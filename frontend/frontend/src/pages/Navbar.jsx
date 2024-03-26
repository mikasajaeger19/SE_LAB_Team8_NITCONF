import React from 'react' 
import {Link} from 'react-router-dom'
import './Navbar.css'

function Navbar() {
    return (
      <div className='navbar'>
        <a className = 'nav'  href ='/personal'><p>PERSONAL</p></a>
        <a className = 'nav' href ='/Dashboard'><p>DASHBOARD</p></a>
        <a className = 'nav' href ='/submit '><p>ABSTRACT</p></a>
        <a className = 'nav' href ='/reupload'><p>REUPLOAD</p></a>
        <a className = 'nav' href ='/versions'><p>VERSIONS</p></a>
      </div>
      
    );
  }

export default Navbar