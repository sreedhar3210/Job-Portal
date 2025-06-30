// import React, { useState } from 'react';
import React from 'react';

function Signin(){

	// const [email, setEmail] = useState('');
	// const [password, setPassword] = useState('');
	// const [isSubmitted, setIsSubmitted] = useState(false);

	// const handleEmailOnChange = (event) => setEmail(event.target.value);
	// const handlePasswordOnChange = (event) => setPassword(event.target.value);

	// const handleDetailsSubmit = () => {
	// 	console.log('>>> email is ' + email + ' password is ' + password);
	// 	setIsSubmitted(true);
	// }

	// useEffect(() => {
	// 	if (isSubmitted) {
	// 		const verifyUserCredentials = async () => {
	// 			let options = {
	// 				method: 'POST',
	// 				headers: {
	// 					'Content-Type': 'application/json'
	// 				},
	// 				body: JSON.stringify({
	// 					email: email,
	// 					password: password
	// 				})
	// 			};

	// 			await fetch('http://localhost:8080/api/verify-user-credentials', options);
	// 		};

	// 		insertUser();
	// 	}
	// 	// eslint-disable-next-line
	// }, [isSubmitted]);

	return(
		<div>
			<p>From Signin page</p>
			{/*<input
				type="text"
				nChange={handleEmailOnChange}
				value={email}
			/>
			<input
				type="text"
				onChange={handlePasswordOnChange}
				value={password}
			/>
			<button
				type="submit"
				onClick={handleOnSubmit}
			/>*/}
		</div>
	);
}

export default Signin;