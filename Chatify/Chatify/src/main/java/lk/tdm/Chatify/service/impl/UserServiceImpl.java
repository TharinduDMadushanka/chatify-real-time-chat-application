package lk.tdm.Chatify.service.impl;

import lk.tdm.Chatify.dto.UserDTO;
import lk.tdm.Chatify.entity.User;
import lk.tdm.Chatify.repo.UserRepo;
import lk.tdm.Chatify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Hash the password
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

    @Override
    public UserDTO loginUser(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return new UserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        null, // Never expose passwords in responses
                        user.getProfilePictureUrl(),
                        user.isOnlineStatus(),
                        user.getAbout(),
                        user.getLastSeen()
                );
            }
        }
        return null; // Login failed
    }
}
