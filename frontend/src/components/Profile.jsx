import React from "react";
import Navbar from "./NavigationBar";
import "../css/Profile.css";

function Profile() {
    const username = localStorage.getItem("username");
    const email = localStorage.getItem("email");
    const company = localStorage.getItem("company");

    const handleLogout = () => {
        localStorage.clear();
        
    }

    return (
        <>
            <Navbar />

            <div className="profile-page">
                <div className="profile-card">

                    <div className="profile-header">
                        <div className="profile-avatar">
                            {username ? username.charAt(0).toUpperCase() : "U"}
                        </div>

                        <div>
                            <h1>{username || "User"}</h1>
                            <p>{company || "Job Portal User"}</p>
                        </div>
                    </div>

                    <div className="profile-details">
                        <div className="profile-section">
                            <h2>Personal Information</h2>

                            <div className="detail">
                                <span>Name</span>
                                <strong>{username || "Not available"}</strong>
                            </div>

                            <div className="detail">
                                <span>Email</span>
                                <strong>{email || "Not available"}</strong>
                            </div>
                        </div>

                        <div className="profile-section">
                            <h2>Account</h2>

                            <div className="detail">
                                <span>Account Type</span>
                                <strong>{company ? "Company" : "Job Seeker"}</strong>
                            </div>

                            {company && (
                                <div className="detail">
                                    <span>Company</span>
                                    <strong>{company}</strong>
                                </div>
                            )}
                        </div>
                    </div>

                    <div className="profile-actions">
                        <button className="edit-btn">Edit Profile</button>
                        <button 
                            className="logout-btn"
                            onClick={handleLogout}>

                            Logout
                        </button>
                    </div>

                </div>
            </div>
        </>
    );
}

export default Profile;