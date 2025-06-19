import React, { useState, useEffect } from 'react';
import '../css/Signup.css';

function Signup() {

	const [firstname, setFirstname] = useState('');
	const [lastname, setLastname] = useState('');
	const [isSubmitted, setIsSubmitted] = useState(false);

	const handleFirstnameOnChange = (event) => setFirstname(event.target.value);
	const handleLastnameOnChange  = (event) => setLastname(event.target.value);
	const handleDetailsSubmit = () => {
		console.log('>>> firstname is ', firstname, ' lastname is ', lastname);
		setIsSubmitted(true);
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
						lastname: lastname
					})
				};

				await fetch('http://localhost:8080/api/insert-user', options);
			};

			insertUser();
		}
		// eslint-disable-next-line
	}, [isSubmitted]);

	return (

		<div className="signup-container">
			<input
				className="signup-input"
				type="text"
				onChange={handleFirstnameOnChange}
				value={firstname}
				placeholder="Enter your Firstname"
			/>
			<input
				className="signup-input"
				type="text"
				onChange={handleLastnameOnChange}
				value={lastname}
				placeholder="Enter your Lastname"
			/>
			<button
				className="signup-button"
				type="submit"
				onClick={handleDetailsSubmit}
			>
				Submit
			</button>
		</div>
	);

}

export default Signup;