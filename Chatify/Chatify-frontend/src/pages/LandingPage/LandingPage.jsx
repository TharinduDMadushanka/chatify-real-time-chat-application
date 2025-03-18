import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './LandingPage.css';

const LandingPage = () => {
  const [loading, setLoading] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    const timer = setInterval(() => {
      setLoading((prevProgress) => {
        if (prevProgress >= 100) {
          clearInterval(timer);
          setTimeout(() => {
            navigate('/login');
          }, 2000);
          return 100;
        }
        return prevProgress + 1;
      });
    }, 100);

    return () => clearInterval(timer);
  }, [navigate]);

  return (
    <div className="landing-container">
      <div className="content-wrapper">
        <div className="logo-container">
          <div className="logo-circle">
            <div className="logo-inner">
              <span className="logo-text">C</span>
            </div>
          </div>
          <h1 className="app-title">Chatify</h1>
          <p className="app-subtitle">Connect. Chat. Create.</p>
        </div>
        
        <div className="loading-container">
          <div className="progress-wrapper">
            <div className="progress custom-progress">
              <div 
                className="progress-bar" 
                role="progressbar" 
                style={{ width: `${loading}%` }} 
                aria-valuenow={loading} 
                aria-valuemin="0" 
                aria-valuemax="100"
              >
              </div>
            </div>
            <div className="progress-text">{loading}%</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LandingPage;