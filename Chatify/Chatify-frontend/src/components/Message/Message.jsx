import React from 'react';
import { format } from 'date-fns';
import './Message.css';

const Message = ({ message, isCurrentUser }) => {
  return (
    <div className={`message ${isCurrentUser ? 'sent' : 'received'}`}>
      <div className="message-bubble">
        <div className="message-content">{message.content}</div>
        <div className="message-time">
          {format(new Date(message.timestamp), 'HH:mm')}
        </div>
      </div>
    </div>
  );
};

export default Message;