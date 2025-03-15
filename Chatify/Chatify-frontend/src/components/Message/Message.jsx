import React from 'react';
import './Message.css';

const Message = ({ message }) => {
  return (
    <div className="message">
      <strong>{message.senderId}</strong>: {message.content}
    </div>
  );
};

export default Message;
