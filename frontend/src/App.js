import './css/App.css';
import Home from './components/Home';
import Signin from './components/Signin'
import Signup from './components/Signup'
import CompanySignup from './components/CompanySignup';
import Job from './components/Job';
import Profile from './components/Profile';
import {
  BrowserRouter,
  Routes,
  Route,
  Outlet,
  Navigate
} from 'react-router-dom';

function App() {

    // 1. Create the ProtectedRoute wrapper component
    const ProtectedRoute = () => {
        // Replace this with your actual authentication logic.
        // For example, checking a token in localStorage or a Redux store.
        const isUserSignin = localStorage.getItem("IsCompanySignIn");
        const isCompanySignin = localStorage.getItem("IsCompanySignIn");

        // If authenticated, render the child routes (Outlet). 
        // If not, redirect to the signin page.
        return isUserSignin || isCompanySignin ? <Outlet /> : <Navigate to="/signin" replace />;
    };

    return (
        <BrowserRouter>
          <div>
            <Routes>
              {/* PUBLIC ROUTES - Accessible without logging in */}
              <Route path="/signin" element={<Signin/>} />
              <Route path="/signup" element={<Signup/>} />
              <Route path="/company-signup" element={<CompanySignup/>} />

              {/* PROTECTED ROUTES - Requires authentication */}
              <Route element={<ProtectedRoute />}>
                <Route path="/" element={<Home/>} />
                <Route path="/post-job" element={<Job/>} />
                <Route path="/profile" element={<Profile/>} />
              </Route>
            </Routes>
          </div>
        </BrowserRouter>
    );
}

export default App;
