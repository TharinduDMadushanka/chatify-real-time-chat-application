import React from 'react';
import './UserList.css';

const UserList = ({ users }) => {
  return (
    <div className="user-list">
      <h3>Online Users</h3>
      <ul>
        {users.map((user, index) => (
          <li key={index}>{user.username}</li>
        ))}
      </ul>
    </div>
  );
};

export default UserList;
