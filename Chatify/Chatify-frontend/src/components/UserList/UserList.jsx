import React from 'react';
import { Search, MoreVertical, MessageCircle } from 'lucide-react';
import { format } from 'date-fns';
import './UserList.css';

const UserList = ({ users, selectedUser, onSelectUser, currentUser }) => {
  return (
    <div className="user-list-container">
      <div className="user-list-header">
        <div className="current-user">
          <img src={currentUser.profilePictureUrl} alt={currentUser.username} className="user-avatar" />
          <div className="header-icons">
            <MessageCircle size={24} />
            <MoreVertical size={24} />
          </div>
        </div>
        <div className="search-container">
          <div className="search-input-wrapper">
            <Search size={20} />
            <input type="text" placeholder="Search or start new chat" />
          </div>
        </div>
      </div>

      <div className="users-container">
        {users.map((user) => (
          <div
            key={user.id}
            className={`user-item ${selectedUser?.id === user.id ? 'active' : ''}`}
            onClick={() => onSelectUser(user)}
          >
            <img src={user.profilePictureUrl} alt={user.username} className="user-avatar" />
            <div className="user-info">
              <div className="user-name-time">
                <h6>{user.username}</h6>
                <small>
                  {user.lastSeen && !isNaN(new Date(user.lastSeen)) 
                    ? format(new Date(user.lastSeen), 'HH:mm') 
                    : 'Unknown'}
                </small>
              </div>
              <p className="user-last-message">{user.about}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default UserList;
