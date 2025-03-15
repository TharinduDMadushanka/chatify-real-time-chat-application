import React, { useState } from 'react';
import Message from '../Message/Message';
import './ChatBox.css';

const ChatBox = ({ messages, stompClient }) => {
  const [content, setContent] = useState('');

  const sendMessage = () => {
    const message = {
      senderId: 1, // Replace with actual sender ID
      receiverId: 2, // Replace with actual receiver ID
      content: content,
    };
    stompClient.send('/app/chat.sendMessage', {}, JSON.stringify(message));
    setContent('');
  };

  return (
    <div className="chat-box">
      <div className="messages">
        {messages.map((message, index) => (
          <Message key={index} message={message} />
        ))}
      </div>
      <div className="input-box">
        <input
          type="text"
          value={content}
          onChange={(e) => setContent(e.target.value)}
          placeholder="Type a message..."
        />
        <button onClick={sendMessage}>Send</button>
      </div>
    </div>
  );
};

export default ChatBox;
