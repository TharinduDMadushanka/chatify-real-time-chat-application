import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { MessageCircle } from 'lucide-react';
import axios from 'axios';
import './Register.css';

const Register = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8080/api/v1/user/register', {
        username,
        email,
        password,
        profilePictureUrl: `https://api.dicebear.com/7.x/initials/svg?seed=${username}`,
        onlineStatus: true,
        about: "Hey! I'm using Chatify"
      });
      navigate('/login');
    } catch (error) {
      setError('Registration failed. Please try again.');
      console.error('Registration failed', error);
    }
  };

  return (
    <div className="register-container">
      <div className="register-card">
        <div className="register-header">
          <MessageCircle size={48} className="register-logo" />
          <h1>Create Account</h1>
        </div>
        <form onSubmit={handleRegister} className="register-form">
          {error && <div className="alert alert-danger">{error}</div>}
          <div className="register-form-group">
            <input
              type="text"
              className="register-input"
              placeholder="Username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div className="register-form-group">
            <input
              type="email"
              className="register-input"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div className="register-form-group">
            <input
              type="password"
              className="register-input"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="register-button">
            Register
          </button>
        </form>
        <p className="register-footer">
          Already have an account? <Link to="/login" className="register-link">Login</Link>
        </p>
      </div>
    </div>
  );
};

export default Register;
