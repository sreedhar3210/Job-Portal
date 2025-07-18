import React, { useState } from 'react';
import UserSignup from './UserSignup';
import CompanySignup from './CompanySignup';
import '../css/Signup.css';

function Signup() {

	const [isUserSignup, setIsUserSignup] = useState(false);
	const [isCompanySignup, setIsCompanySignup] = useState(false);

	const handleRegisterAsUser = (event) => setIsUserSignup(true);
	const handleRegisterAsCompany = (event) => setIsCompanySignup(true);
	
	return (
		<div className="register-container">
		  <div className="register-card" hidden={isUserSignup || isCompanySignup}>
		    <h2 className="register-heading">Choose Registration Type</h2>
		    <button
		      className="register-button"
		      type="button"
		      onClick={handleRegisterAsUser}>
		      Register as Applicant
		    </button>
		    <button
		      className="register-button"
		      type="button"
		      onClick={handleRegisterAsCompany}>
		      Register as Company
		    </button>
		  </div>

		  <div hidden={!isUserSignup}>
		    <UserSignup />
		  </div>

		  <div hidden={!isCompanySignup}>
		    <CompanySignup />
		  </div>
		</div>
	);
}

export default Signup;