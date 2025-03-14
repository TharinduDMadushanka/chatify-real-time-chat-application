package lk.tdm.Chatify.service;

import lk.tdm.Chatify.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);
    UserDTO loginUser(String email, String password);

    List<UserDTO> getAllUsers();
}
