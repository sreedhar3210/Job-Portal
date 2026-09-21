import React from "react";
import { Link } from "react-router-dom";
import "../css/Navbar.css";

const Navbar = () => {
  const isUserSignIn = localStorage.getItem('isUserSignIn');
  const isCompanySignin = localStorage.getItem('IsCompanySignIn');
  const username = localStorage.getItem('username');
  const companyName = localStorage.getItem('CompanyName');

  return (
    <nav className="navbar">
      <div className="navbar-logo">
        <Link to="/">JobPortal</Link>
      </div>

      <div className="navbar-links">
        <Link to="/">Home</Link>
        <Link to="/jobs" hidden={!isCompanySignin}>Openings</Link>
        <Link to="/companies">Companies</Link>
        <Link to="/post-job" hidden={!isCompanySignin}>Post a Job</Link>
      </div>

      <div className="navbar-actions">
        <Link to="/profile" className="profile-btn">
          <span className="profile-icon">👤</span>
          <span className="username">{isUserSignIn === true ? username : companyName}</span>
        </Link>
      </div>

    </nav>
  );
};

export default Navbar;
