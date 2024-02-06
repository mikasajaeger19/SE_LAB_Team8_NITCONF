import React from 'react' 
import {Link} from 'react-router-dom'
import './Navbar.css'

function Navbar() {
    return (
      <nav className="navbar">
        <h3 className="nav--personal">
          <Link to="/personal">Personal Info</Link>
        </h3>
        <h3 className="nav--dashboard">
          <Link to="/dashboard">Dashboard</Link>
        </h3>
        <h3 className="nav--submit">
          <Link to="/submit">Submit Abstract</Link>
        </h3>
        <h3 className="nav--reupload">
          <Link to="/reupload">Re-upload Paper</Link>
        </h3>

        <h3 className="nav--versions">
          <Link to="/versions">Versions</Link>
        </h3>
      </nav>
    );
  }

export default Navbar