import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../css/CompanySignup.css';

function CompanySignup() {

	const navigate = useNavigate();
	const [isFirstPage, setIsFirstPage] = useState(true);
	const [isSecondPage, setIsSecondPage] = useState(false);
	const [fetchedSelectOptions, setFetchedSelectOptions] = useState(false);
	const [companyName, setCompanyName] = useState("");
	const [industryTypeOptions, setIndustryTypeOptions] = useState([]);
	const [selectedIndustryType, setSelectedIndustryType] = useState({});
	const [companyURL, setCompanyURL] = useState("");
	const [companySizeOptions, setCompanySizeOptions] = useState([]);
	const [selectedCompanySize, setSelectedCompanySize] = useState({});
	const [companyDescription, setCompanyDescription] = useState("");
	const [companyEmail, setCompanyEmail] = useState("");
	const [companyPassword, setCompanyPassword] = useState("");
	const [confirmPassword, setConfirmPassword] = useState("");
	const [isSubmitted, setIsSubmitted] = useState(false);


	const handleCompanyNameChange = (event) => setCompanyName(event.target.value);
	const handleIndustryTypeChange = (event) =>	{
		const selectedIndex = event.target.selectedIndex - 1;
		setSelectedIndustryType(industryTypeOptions[selectedIndex]);
	}
	const handleCompanyURLChange = (event) => setCompanyURL(event.target.value);
	const handleCompanySizeChange = (event) => {
		const selectedIndex = event.target.selectedIndex - 1;
		setSelectedCompanySize(companySizeOptions[selectedIndex]);
	}
	const handleCompanyDescriptionChange = (event) => setCompanyDescription(event.target.value);
	const handleCompanyEmailChange = (event) => setCompanyEmail(event.target.value);
	const handleCompanyPasswordChange = (event) => setCompanyPassword(event.target.value);
	const handleConfirmPasswordChange = (event) => setConfirmPassword(event.target.value);
	const handleFirstPageSubmit = (event) => {
		setIsFirstPage(false);
		setIsSecondPage(true);
	}
	const handleSeondPagePrevButton = (event) => {
		setIsFirstPage(true);
		setIsSecondPage(false);
	}

	const verifyPassword = () => {
		return companyPassword === confirmPassword;
	}

	const handleFormSubmitButton = (event) => {
		if(verifyPassword()){
			setIsSubmitted(true);
		}
		else{
			setCompanyPassword("");
			setConfirmPassword("");
			alert("Passwords do not match, please re-enter");
		}
		
	}

	useEffect(() => {
		const getSelectOptions = async () => {
			setFetchedSelectOptions(true);
			let arrayParams = ['Industry Type', 'Company Size'];
			let options = {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(arrayParams)
			};

			const response = await fetch('http://localhost:8080/api/get-select-options', options);
			const responseText = await response.json();
			const responseTextData = await responseText.data;
			if(response.status === 200){
				if(responseText.status === "OK"){
					setIndustryTypeOptions(responseTextData["Industry Type"]);
					setCompanySizeOptions(responseTextData["Company Size"]);
				}
				else{
					alert('Some error in the page, please refresh the page');
				}
			}
			else{
				alert('An error occured could you please refresh the page');
			}
		};
		if(!fetchedSelectOptions){
			getSelectOptions();
		}
		// eslint-disable-next-line
	}, []);

	useEffect(() => {
		if(isSubmitted){
			console.log('>>>> submit form useEffect is called');
			const addCompany = async() => {
				let options = {
					method: 'POST',
					headers: {
						'Content-Type': 'application/json'
					},
					body: JSON.stringify({
						companyName: companyName,
						industryType: selectedIndustryType.id,
						companyURL: companyURL,
						companySize: selectedCompanySize.id,
						companyDescription: companyDescription,
						companyEmail: companyEmail,
						companyPassword: companyPassword
					})
				};

				const response = await fetch('http://localhost:8080/api/add-company', options);
				const responseText = await response.json();
				console.log('>>>> Company Signup response is ', response);
				console.log('>>>> company signup responseText is ', responseText);
				if(response.status === 200){
					alert(responseText.message);
					navigate('/signin');
				}
				else{
					alert(responseText.message);
					window.location.reload();
				}
				console.log('>>>> response text is ', responseText);
			}
			addCompany();
		}
		// eslint-disable-next-line
	}, [isSubmitted]);

	return (
		<div className="company-registration-container">
		  <h2 className="company-registration-heading">Register Your Company</h2>

		  <div hidden={!isFirstPage} className="form-section">
		    <label className="company-label">Company Name</label>
		    <input
		      type="text"
		      className="company-input"
		      value={companyName}
		      onChange={handleCompanyNameChange}
		      placeholder="Enter Company Name"
		    />

		    <label className="company-label">Industry Type</label>
		    <select
		      className="company-select"
		      value={selectedIndustryType.value}
		      onChange={handleIndustryTypeChange}
		    >
		      <option value="">Select Industry Type</option>
		      {industryTypeOptions.map((option, idx) => (
		        <option key={idx} value={option.value}>{option.label}</option>
		      ))}
		    </select>

		    <label className="company-label">Company URL</label>
		    <input
		      type="text"
		      className="company-input"
		      value={companyURL}
		      onChange={handleCompanyURLChange}
		      placeholder="Enter Company Website"
		    />

		    <label className="company-label">Company Size</label>
		    <select
		      className="company-select"
		      value={selectedCompanySize.value}
		      onChange={handleCompanySizeChange}
		    >
		      <option value="">Select Company Size</option>
		      {companySizeOptions.map((option, idx) => (
		        <option key={idx} value={option.value}>{option.label}</option>
		      ))}
		    </select>

		    <label className="company-label">Company Description</label>
		    <textarea
		      className="company-textarea"
		      value={companyDescription}
		      onChange={handleCompanyDescriptionChange}
		      placeholder="Describe your company"
		    />

		    <button
		      className="company-next-button"
		      type="button"
		      onClick={handleFirstPageSubmit}
		    >
		      Next
		    </button>
		  </div>

		  <div hidden={!isSecondPage} className="form-section">
		    <label className="company-label">Company Email</label>
		    <input
		      type="email"
		      className="company-input"
		      value={companyEmail}
		      onChange={handleCompanyEmailChange}
		      placeholder="Enter Company Email"
		    />

		    <label className="company-label">Password</label>
		    <input
		      type="password"
		      className="company-input"
		      value={companyPassword}
		      onChange={handleCompanyPasswordChange}
		      placeholder="Enter Password"
		    />

		    <label className="company-label">Confirm Password</label>
		    <input
		      type="password"
		      className="company-input"
		      value={confirmPassword}
		      onChange={handleConfirmPasswordChange}
		      placeholder="Confirm Password"
		    />

		    <div className="button-group">
		      <button
		        className="company-prev-button"
		        type="button"
		        onClick={handleSeondPagePrevButton}
		      >
		        Previous
		      </button>

		      <button
		        className="company-submit-button"
		        type="button"
		        onClick={handleFormSubmitButton}
		      >
		        Submit
		      </button>
		    </div>
		  </div>
		</div>

	);

}

export default CompanySignup;