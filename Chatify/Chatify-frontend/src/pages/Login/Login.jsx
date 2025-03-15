import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Login.css';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const response = await axios.post('http://localhost:8080/api/v1/user/login', { email, password });
      localStorage.setItem('token', response.data.token);
      navigate('/chat');
    } catch (error) {
      console.error('Login failed', error);
    }
  };

  return (
    <div className="login-wrapper d-flex justify-content-center align-items-center">
      <div className="login-form">
        <h2 className="text-center mb-4">Welcome Back</h2>
        <input
          type="email"
          className="form-control mb-3"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="password"
          className="form-control mb-4"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button className="btn btn-primary w-100" onClick={handleLogin}>Login</button>
      </div>
    </div>
  );
};

export default Login;
