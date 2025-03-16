import React, { useState, useRef, useEffect } from 'react';
import { Send, Paperclip, Smile } from 'lucide-react';
import { format } from 'date-fns';
import './ChatBox.css';

const ChatBox = ({ messages, stompClient, selectedUser, currentUser }) => {
  const [content, setContent] = useState('');
  const messagesEndRef = useRef(null);

  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  };

  useEffect(() => {
    scrollToBottom();
  }, [messages]);

  const sendMessage = (e) => {
    e.preventDefault();
    if (!content.trim() || !selectedUser) return;

    const message = {
      senderId: currentUser.id,
      receiverId: selectedUser.id,
      content: content.trim(),
      timestamp: new Date()
    };

    stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(message));
    setContent('');
  };

  if (!selectedUser) {
    return (
      <div className="chat-box-container d-flex align-items-center justify-content-center">
        <div className="text-center text-white">
          <h3>Select a chat to start messaging</h3>
        </div>
      </div>
    );
  }

  return (
    <div className="chat-box-container">
      <div className="chat-header">
        <img src={selectedUser.profilePictureUrl} alt={selectedUser.username} className="user-avatar" />
        <div className="user-info">
          <h5>{selectedUser.username}</h5>
          <small>{selectedUser.onlineStatus ? 'online' : 'offline'}</small>
        </div>
      </div>

      <div className="messages-container">
        {messages.map((message, index) => (
          <div
            key={index}
            className={`message ${message.senderId === currentUser.id ? 'sent' : 'received'}`}
          >
            <div className="message-content">
              {message.content}
              <span className="message-time">
                {format(new Date(message.timestamp), 'HH:mm')}
              </span>
            </div>
          </div>
        ))}
        <div ref={messagesEndRef} />
      </div>

      <form onSubmit={sendMessage} className="input-container">
        <button type="button" className="icon-button">
          <Smile size={24} />
        </button>
        <button type="button" className="icon-button">
          <Paperclip size={24} />
        </button>
        <input
          type="text"
          value={content}
          onChange={(e) => setContent(e.target.value)}
          placeholder="Type a message"
          className="message-input"
        />
        <button type="submit" className="send-button" disabled={!content.trim()}>
          <Send size={24} />
        </button>
      </form>
    </div>
  );
};

export default ChatBox;