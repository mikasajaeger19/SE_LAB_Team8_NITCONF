import React from 'react' 
import {Link} from 'react-router-dom'

function Navbar() {
    return (
      <nav className="navbar">
        <h3 className="nav--personal">
          <Link to="/personal">Personal Info</Link>
        </h3>
        <h3 className="nav--dashboard">
          <Link to="/home">Dashboard</Link>
        </h3>
        <h3 className="nav--submit">
          <Link to="/cart">Submit Abstract</Link>
        </h3>
        <h3 className="nav--reupload">
          <Link to="/account">Account</Link>
        </h3>
      </nav>
    );
  }

export default Navbar