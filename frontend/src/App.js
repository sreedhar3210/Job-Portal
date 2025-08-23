import './css/App.css';
import Home from './components/Home';
import Signin from './components/Signin'
import Signup from './components/Signup'
import CompanySignup from './components/CompanySignup';
import Job from './components/Job';
import {
  BrowserRouter,
  Routes,
  Route
} from 'react-router-dom';

function App() {
    return (
        <BrowserRouter>
          <div>
            <Routes>
              <Route path="/signin" element={<Signin/>} />
              <Route path="/signup" element={<Signup/>} />
              <Route path="/" element={<Home/>} />
              <Route path="/company-signup" element={<CompanySignup/>} />
              <Route path="/post-job" element={<Job/>} />
            </Routes>
          </div>
        </BrowserRouter>
    );
}

export default App;
