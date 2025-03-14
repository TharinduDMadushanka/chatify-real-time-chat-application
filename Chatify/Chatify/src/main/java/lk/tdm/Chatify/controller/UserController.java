package lk.tdm.Chatify.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lk.tdm.Chatify.dto.LoginRequest;
import lk.tdm.Chatify.dto.UserDTO;
import lk.tdm.Chatify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
@Tag(name = "User Management", description = "User registration and management APIs")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user in the system")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.saveUser(userDTO);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    @Operation(summary = "Login a user", description = "Authenticates a user and returns user details")
    public ResponseEntity<UserDTO> loginUser(@RequestBody LoginRequest loginRequest) {
        UserDTO loggedInUser = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Get all users", description = "Retrieves a list of all users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
