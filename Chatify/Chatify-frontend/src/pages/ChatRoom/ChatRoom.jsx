import React, { useEffect, useState } from 'react';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import axios from 'axios';
import ChatBox from '../../components/ChatBox/ChatBox';
import UserList from '../../components/UserList/UserList';
import './ChatRoom.css';

const ChatRoom = () => {
  const [stompClient, setStompClient] = useState(null);
  const [messages, setMessages] = useState([]);
  const [users, setUsers] = useState([]);
  const [selectedUser, setSelectedUser] = useState(null);
  const currentUser = JSON.parse(localStorage.getItem('user'));

  useEffect(() => {
    // Fetch users
    const fetchUsers = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/v1/user/all');
        setUsers(response.data.filter(user => user.id !== currentUser.id));
      } catch (error) {
        console.error('Failed to fetch users', error);
      }
    };
    fetchUsers();

    // Setup WebSocket connection
    const socket = new SockJS('http://localhost:8080/chat');
    const client = Stomp.over(socket);
    
    client.connect({}, () => {
      setStompClient(client);
      
      client.subscribe(`/user/${currentUser.id}/topic/messages`, (message) => {
        const newMessage = JSON.parse(message.body);
        setMessages(prev => [...prev, newMessage]);
      });
    });

    return () => {
      if (client.connected) {
        client.disconnect();
      }
    };
  }, [currentUser.id]);

  useEffect(() => {
    if (selectedUser) {
      // Fetch chat history
      const fetchChatHistory = async () => {
        try {
          const response = await axios.get(`http://localhost:8080/chat/history`, {
            params: {
              senderId: currentUser.id,
              receiverId: selectedUser.id,
              page: 0,
              size: 50
            }
          });
          setMessages(response.data.content);
        } catch (error) {
          console.error('Failed to fetch chat history', error);
        }
      };
      fetchChatHistory();
    }
  }, [selectedUser, currentUser.id]);

  return (
    <div className="chat-room">
      <UserList
        users={users}
        selectedUser={selectedUser}
        onSelectUser={setSelectedUser}
        currentUser={currentUser}
      />
      <ChatBox
        messages={messages}
        stompClient={stompClient}
        selectedUser={selectedUser}
        currentUser={currentUser}
      />
    </div>
  );
};

export default ChatRoom;