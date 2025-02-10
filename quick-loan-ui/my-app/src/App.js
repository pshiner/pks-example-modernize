import './App.css';
import Login from'./pages/Login'
import Dashboard from'./pages/Dashboard'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Header from './components/layout/Header';
import Footer from './components/layout/Footer';
import ProgramCommitmentList from './components/ProgramCommitment/ProgramCommitmentList';

function App() {
  return (
    
    <Router>
      <Header/>
    <Routes>
      <Route path="/" element={<Login/>} />
      <Route path="/dashboard" element={<Dashboard />} />
      <Route path="/login" element={<Login/>} />
      <Route path="/programcommitmentlist" element={<ProgramCommitmentList/>} />
    </Routes>
    <Footer/>
  </Router>
   
  );
}

export default App;
