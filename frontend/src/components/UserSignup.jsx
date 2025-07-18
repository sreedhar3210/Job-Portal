import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../css/UserSignup.css';

function UserSignup() {

	const navigate = useNavigate();
	const [firstname, setFirstname] = useState('');
	const [lastname, setLastname] = useState('');
	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');
	const [confirmPassword, setConfirmPassword] = useState('');
	const [isSubmitted, setIsSubmitted] = useState(false);

	const handleFirstnameOnChange = (event) => setFirstname(event.target.value);
	const handleLastnameOnChange  = (event) => setLastname(event.target.value);
	const handleEmailOnChange = (event) => setEmail(event.target.value);
	const handlePasswordOnChange = (event) => setPassword(event.target.value);
	const handleConfirmPasswordOnChange = (event) => setConfirmPassword(event.target.value);

	const verifyPassword = () => {
		return password === confirmPassword;
	}

	const handleDetailsSubmit = () => {
		console.log('>>> firstname is ', firstname, ' lastname is ', lastname);
		console.log('>>> email is ', email, ' password is ', password);
		if(!verifyPassword){
			alert('Password and Re-entered passwords do not match');
		}
		else{
			setIsSubmitted(true);
		}
		
	}

	useEffect(() => {
		if (isSubmitted) {
			const insertUser = async () => {
				let options = {
					method: 'POST',
					headers: {
						'Content-Type': 'application/json'
					},
					body: JSON.stringify({
						firstname: firstname,
						lastname: lastname,
						email: email,
						password: password
					})
				};

				const response = await fetch('http://localhost:8080/api/add-user', options);
				const responseText = await response.json();
				if(response.status === 200){
					alert(responseText.message);
					navigate("/signin");
				}
				else{
					alert(responseText.message);
					window.location.reload();
				}
			};

			insertUser();
		}
		// eslint-disable-next-line
	}, [isSubmitted]);

	return (

		<div className="signup-wrapper">
		  <div className="signup-card">
		    <h2 className="signup-title">Create Account</h2>

		    <label className="signup-label">Firstname</label>
		    <input
		      className="signup-input"
		      type="text"
		      onChange={handleFirstnameOnChange}
		      value={firstname}
		      placeholder="Enter your Firstname"
		    />

		    <label className="signup-label">Lastname</label>
		    <input
		      className="signup-input"
		      type="text"
		      onChange={handleLastnameOnChange}
		      value={lastname}
		      placeholder="Enter your Lastname"
		    />

		    <label className="signup-label">Email</label>
		    <input
		      className="signup-input"
		      type="text"
		      onChange={handleEmailOnChange}
		      value={email}
		      placeholder="Enter your Email"
		    />

		    <label className="signup-label">Password</label>
		    <input
		      className="signup-input"
		      type="password"
		      onChange={handlePasswordOnChange}
		      value={password}
		      placeholder="Enter your Password"
		    />

		    <label className="signup-label">Re-enter Password</label>
		    <input
		      className="signup-input"
		      type="password"
		      onChange={handleConfirmPasswordOnChange}
		      value={confirmPassword}
		      placeholder="Re-enter your Password"
		    />

		    <button
		      className="signup-button"
		      type="submit"
		      onClick={handleDetailsSubmit}
		    >
		      Submit
		    </button>

		    <p className="signup-footer">
		      Already have an account? <a href="/signin">Sign In</a>
		    </p>
		  </div>
		</div>

	);

}

export default UserSignup;