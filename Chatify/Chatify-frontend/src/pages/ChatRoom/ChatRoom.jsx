import React, { useEffect, useState } from 'react';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import ChatBox from '../../components/ChatBox/ChatBox';
import UserList from '../../components/UserList/UserList';
import './ChatRoom.css';

const ChatRoom = () => {
  const [stompClient, setStompClient] = useState(null);
  const [messages, setMessages] = useState([]);
  const [users, setUsers] = useState([]);

  useEffect(() => {
    const socket = new SockJS('/chat');
    const client = Stomp.over(socket);
    setStompClient(client);

    client.connect({}, () => {
      client.subscribe('/topic/messages', (message) => {
        setMessages((prevMessages) => [...prevMessages, JSON.parse(message.body)]);
      });

      client.subscribe('/topic/users', (users) => {
        setUsers(JSON.parse(users.body));
      });
    });

    return () => {
      if (client.connected) {
        client.disconnect(() => {
          console.log("Disconnected");
        });
      }
    };
  }, []);

  return (
    <div className="chat-room-container">
      <UserList users={users} />
      <ChatBox messages={messages} stompClient={stompClient} />
    </div>
  );
};

export default ChatRoom;
