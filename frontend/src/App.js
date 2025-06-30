import './css/App.css';
// import Home from './Home';
import Signin from './components/Signin'
import Signup from './components/Signup'
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
            </Routes>
          </div>
        </BrowserRouter>
    );
}

export default App;
