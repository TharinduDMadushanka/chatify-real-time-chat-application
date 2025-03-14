package lk.tdm.Chatify.service;

import lk.tdm.Chatify.dto.UserDTO;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);
    UserDTO loginUser(String email, String password);
}
