package lk.tdm.Chatify.service.impl;

import lk.tdm.Chatify.dto.UserDTO;
import lk.tdm.Chatify.entity.User;
import lk.tdm.Chatify.repo.UserRepo;
import lk.tdm.Chatify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());

        // 🔹 Encrypt the password before saving it
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        user.setProfilePictureUrl(userDTO.getProfilePictureUrl());
        user.setOnlineStatus(userDTO.isOnlineStatus());
        user.setAbout(userDTO.getAbout());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setLastSeen(userDTO.getLastSeen());

        User savedUser = userRepo.save(user);

        return new UserDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                null, // Never expose passwords in responses
                savedUser.getProfilePictureUrl(),
                savedUser.isOnlineStatus(),
                savedUser.getAbout(),
                savedUser.getLastSeen()
        );
    }

}
