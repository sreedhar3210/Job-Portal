import React, { useState, useEffect } from 'react';
import DatePicker from 'react-datepicker';
import { useNavigate } from 'react-router-dom';
import 'react-datepicker/dist/react-datepicker.css';
import '../css/Job.css';


function Job() {        
    const navigate = useNavigate();
    const company = localStorage.getItem('company');
    const [fetchSelectedOptions, setFetchSelectedOptions] = useState(false);
    const [roleOptions, setRoleOptions] = useState([]);
    const [selectedRoleOption, setSelectedRoleOption] = useState({});
    const [description, setDescription] = useState("");
    const [responsibilities, setResponsibilities] = useState("");
    const [requirements, setRequirements] = useState("");
    const [locationOptions, setLocationOptions] = useState([]);
    const [selectedLocationOption, setSelectedLocationOption] = useState({});
    const [employmentTypeOptions, setEmploymentTypeOptions] = useState([]);
    const [selectedEmploymentTypeOption, setSelectedEmploymentTypeOption] = useState({});
    const [experienceLevelOptions, setExperienceLevelOptions] = useState([]);
    const [selectedExperienceLevelOption, setSelectedExperienceLevelOption] = useState({});
    const [salary, setSalary] = useState(0);
    const [lastDate, setLastDate] = useState(null);
    const [isSubmitted, setIsSubmitted] = useState(false);
  
    const getTodayDate = () => {
        let today = new Date();
        let dd = today.getDate();
        let mm = today.getMonth();
        let yyyy = today.getFullYear();
        if(dd.length === 1)         dd = '0' + dd;
        if(mm.length === 1)         mm = '0' + mm;
        let formattedDate = dd + '/' + mm + '/' + yyyy;
        return formattedDate;
    }
    
    const handleRoleOptionChange = (event) => {
        let selectedIndex = event.target.selectedIndex - 1;
        setSelectedRoleOption(roleOptions[selectedIndex]);
    }
    
    const handleDescriptionChange = (event) => {
        setDescription(event.target.value);
    }
    
    const handleResponsibilitiesChange = (event) => {
        setResponsibilities(event.target.value);
    }
    
    const handleRequirementsChange = (event) => {
        setRequirements(event.target.value);
    }
    
    const handleLocationOptionChange = (event) => {
        let selectedIndex = event.target.selectedIndex - 1;
        setSelectedLocationOption(locationOptions[selectedIndex]);
    }
    
    const handleEmploymentTypeOptionChange = (event) => {
        let selectedIndex = event.target.selectedIndex - 1;
        setSelectedEmploymentTypeOption(employmentTypeOptions[selectedIndex]);
    }
    
    const handleExperienceLevelOptionChange = (event) => {
        let selectedIndex = event.target.selectedIndex - 1;
        setSelectedExperienceLevelOption(experienceLevelOptions[selectedIndex]);
    }
    
    const handleSalaryChange = (event) => {
        setSalary(event.target.value);
    }
    
    const handleLastDateChange = (date) => {
        setLastDate(date);
    }
    
    const handleFormSubmitButton = (event) => {
        setIsSubmitted(true);
    }
    
    useEffect(() => {
        const getSelectOptions = async() => {
            setFetchSelectedOptions(true);
            let arrayParams = ['Job Roles', 'Job Locations', 'Job Type', 'Job Experience'];
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
                    setRoleOptions(responseTextData["Job Roles"]);
                    setLocationOptions(responseTextData["Job Locations"]);
                    setEmploymentTypeOptions(responseTextData["Job Type"]);
                    setExperienceLevelOptions(responseTextData["Job Experience"]);
                }
                else{
                    alert("Some error occured, please refresh the page");
                }
            }
          else{
                alert("Some error occured, please refresh the page");
          }
        }
        if(!fetchSelectedOptions){
            getSelectOptions();
        }
        // eslint-disable-next-line
    }, []);
  
    useEffect(() => {
        const postJob = async() => {
            let options = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    roleId: selectedRoleOption.id,
                    description: description,
                    responsibilities: responsibilities,
                    requirements: requirements,
                    locationId: selectedLocationOption.id,
                    employmentTypeId: selectedEmploymentTypeOption.id,
                    experienceLevelId: selectedExperienceLevelOption.id,
                    salary: salary,
                    companyId: company.id,
                    postedDate: getTodayDate(),
                    lastDate: getTodayDate()
                })
            };
            console.log('>>>> options= ', options);
            const response = fetch('http://localhost:8080/api/post-job', options);
            const responseText = await response.json();
            if(response.status === 200){
                alert(responseText.message);
                navigate('/signin');
            } else{
                alert(responseText.message);
                window.location.reload();
            }
        }
        if(isSubmitted){
            console.log('>>> selectedRoleOptions is = ', selectedRoleOption);
            console.log('>>>> selectedRoleOptionsId = ', selectedRoleOption.id);
            console.log('>>> selectedLocation is = ', selectedLocationOption);
            console.log('>>> selectedLocationId = ', selectedLocationOption.id);
            console.log('>>>> ')
            postJob();
        }
        // eslint-disable-next-line
    }, [isSubmitted]);
  
    return(
        <div className="job-form-container">
        <p className="form-title">Create New Job</p>

        <label className="form-label">Role</label>
        <select value={selectedRoleOption.value} onChange={handleRoleOptionChange} className="form-select">
            <option value="">Select Role</option>
            {roleOptions.map((option, index) => (
                <option key={index} value={option.value}>{option.label}</option>
            ))}
        </select>

        <label className="form-label">Job Description</label>
        <textarea
            value={description}
            onChange={handleDescriptionChange}
            placeholder="Enter Job Description"
            className="form-input"
        />

        <label className="form-label">Responsibilities</label>
        <textarea
            value={responsibilities}
            onChange={handleResponsibilitiesChange}
            placeholder="Enter Job Responsibilities"
            className="form-input"
        />

        <label className="form-label">Requirements</label>
        <textarea
            value={requirements}
            onChange={handleRequirementsChange}
            placeholder="Enter Job Requirements"
            className="form-input"
        />

        <label className="form-label">Location</label>
        <select value={selectedLocationOption.value} onChange={handleLocationOptionChange} className="form-select">
            <option value="">Select Location</option>
            {locationOptions.map((option, index) => (
                <option key={index} value={option.value}>{option.label}</option>
            ))}
        </select>

        <label className="form-label">Employment Type</label>
        <select value={selectedEmploymentTypeOption.value} onChange={handleEmploymentTypeOptionChange} className="form-select">
            <option value="">Select Employment Type</option>
            {employmentTypeOptions.map((option, index) => (
                <option key={index} value={option.value}>{option.label}</option>
            ))}
        </select>

        <label className="form-label">Experience Level</label>
        <select value={selectedExperienceLevelOption.value} onChange={handleExperienceLevelOptionChange} className="form-select">
            <option value="">Select Required Experience Level</option>
            {experienceLevelOptions.map((option, index) => (
                <option key={index} value={option.value}>{option.label}</option>
            ))}
        </select>

        <label className="form-label">Salary (in LPA)</label>
        <input
            type="number"
            value={salary}
            onChange={handleSalaryChange}
            placeholder="Enter Salary in LPA"
            className="form-input"
        />

        <label className="form-label">Last Date to Apply</label>
        <div className="datepicker-wrapper">
            <DatePicker
                className="custom-datepicker-input"
                selected={lastDate}
                onChange={handleLastDateChange}
                dateFormat="dd/MM/yyyy"
                showYearDropdown
                scrollableMonthYearDropdown
                placeholderText="Select Last Date to Apply"
            />
        </div>
        
        <button
            type="button"
            onClick={handleFormSubmitButton}
            className="submit-button"
        >
            Submit
        </button>
    </div>




    )
}

export default Job;