import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { MessageCircle } from 'lucide-react';
import axios from 'axios';
import './Login.css';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/v1/user/login', { email, password });
      localStorage.setItem('token', response.data.token);
      localStorage.setItem('user', JSON.stringify(response.data));
      navigate('/chat');
    } catch (error) {
      setError('Invalid email or password');
      console.error('Login failed', error);
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-card">
        <div className="auth-header">
          <MessageCircle size={48} className="auth-logo" />
          <h1>Welcome to Chatify</h1>
        </div>
        <form onSubmit={handleLogin} className="auth-form">
          {error && <div className="alert alert-danger">{error}</div>}
          <div className="form-group mb-3">
            <input
              type="email"
              className="form-control"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div className="form-group mb-4">
            <input
              type="password"
              className="form-control"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="btn btn-primary w-100 mb-3">
            Login
          </button>
        </form>
        <p className="text-center text-white">
          Don't have an account? <Link to="/register" className="auth-link">Register</Link>
        </p>
      </div>
    </div>
  );
};

export default Login;