import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../css/Signin.css';

function Signin(){

	const navigate = useNavigate();
	const [isUserSignIn, setIsUserSignIn] = useState(true);
	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');
	const [isSubmitted, setIsSubmitted] = useState(false);

	const handleEmailOnChange = (event) => setEmail(event.target.value);
	const handlePasswordOnChange = (event) => setPassword(event.target.value);

	const handleDetailsSubmit = () => {
		console.log('>>> email is ' + email + ' password is ' + password);
		setIsSubmitted(true);
	}

	const handleUserSignInButton = () => {
		setIsUserSignIn(true);
	}

	const handleCompanySignInButton = () => {
		setIsUserSignIn(false);
	}

	useEffect(() => {
		if (isSubmitted) {
			const verifyUserCredentials = async () => {
				let userOptions = {
					method: 'POST',
					headers: {
						'Content-Type': 'application/json'
					},
					body: JSON.stringify({
						email: email,
						password: password 
					})
				};
				let companyOptions = {
					method: 'POST',
					headers: {
						'Content-Type': 'application/json'
					},
					body: JSON.stringify({
						companyEmail: email,
						companyPassword: password
					})
				};
				let response = '';
				if(isUserSignIn)		response = await fetch('http://localhost:8080/api/verify-user-credentials', userOptions);
				else					response = await fetch('http://localhost:8080/api/verify-company-credentials', companyOptions);	
				
				const responseText = await response.json();

				console.log('>>>>> response: ', response);
				console.log('>>>>> responseText: ', responseText);
				if(response.status === 200){
					if(responseText.status === "Found"){
						navigate("/");
					}
					else{
						alert('Invalid Credentials, please try again');
						window.location.reload();
					}
				}
				else{
					alert('Some error occured, please try again');
					window.location.reload();
				}

			};

			verifyUserCredentials();
		}
		// eslint-disable-next-line
	}, [isSubmitted]);

	return(
		<div className="signin-container">
		    <h2 className="signin-title">Welcome Back</h2>
		    <p className="signin-subtitle">Signing in as {isUserSignIn ? "User" : "Company"}</p>

		    <input
		        className="signin-input"
		        type="text"
		        onChange={handleEmailOnChange}
		        value={email}
		        placeholder="Enter your Email"
		    />
		    <input
		        className="signin-input"
		        type="password"
		        onChange={handlePasswordOnChange}
		        value={password}
		        placeholder="Enter your Password"
		    />
		    
		    <button
		        className="signin-button"
		        type="submit"
		        onClick={handleDetailsSubmit}
		    >
		        Sign In
		    </button>

		    <div className="signin-footer">
		        <p>Don't have an account? 
		            <a href="http://localhost:3000/signup"> Sign Up</a>
		        </p>
		    </div>

		    <div className="signin-switch">
		        <p>Sign in as:</p>
		        <div className="signin-switch-buttons">
		            <button
		                type="button"
		                onClick={handleUserSignInButton}
		                className="signin-switch-button"
		                hidden={isUserSignIn}
		            >
		                User
		            </button>

		            <button
		                type="button"
		                onClick={handleCompanySignInButton}
		                className="signin-switch-button"
		                hidden={!isUserSignIn}
		            >
		                Company
		            </button>
		        </div>
		    </div>
		</div>

	);
}

export default Signin;