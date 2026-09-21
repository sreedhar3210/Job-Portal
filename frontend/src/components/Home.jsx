
import React from "react";
import Navbar from "./NavigationBar";
import "../css/Home.css";

const Home = () => {
  return (
    <div className="home">
      <Navbar />

      <main className="home-content">
        <section className="hero">
          <h1>Find your next opportunity</h1>

          <p>
            Discover jobs, connect with companies, and take the next step
            in your career.
          </p>

          <div className="search-container">
            <input
              type="text"
              placeholder="Search for jobs..."
            />

            <input
              type="text"
              placeholder="Location"
            />

            <button>Search Jobs</button>
          </div>
        </section>

        <section className="home-section">
          <h2>Explore Opportunities</h2>

          <div className="home-cards">
            <div className="home-card">
              <h3>Find Jobs</h3>
              <p>
                Search and explore job opportunities that match your skills
                and interests.
              </p>
            </div>

            <div className="home-card">
              <h3>Discover Companies</h3>
              <p>
                Explore companies and learn more about the organizations
                hiring talented people.
              </p>
            </div>

            <div className="home-card">
              <h3>Build Your Career</h3>
              <p>
                Find opportunities that help you grow and move forward in
                your career.
              </p>
            </div>
          </div>
        </section>
      </main>
    </div>
  );
};

export default Home;

