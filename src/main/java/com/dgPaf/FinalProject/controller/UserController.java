package com.dgPaf.FinalProject.controller;

import com.dgPaf.FinalProject.dto.LoginRequest;
import com.dgPaf.FinalProject.dto.UserActivityDto;
import com.dgPaf.FinalProject.dto.UserDTO;
import com.dgPaf.FinalProject.model.Furniture;
import com.dgPaf.FinalProject.model.User;
import com.dgPaf.FinalProject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        boolean authenticated = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (authenticated) {
            User user = userService.findByEmail(loginRequest.getEmail()).orElse(null);
            if (user != null) {
                return ResponseEntity.ok(user); // Return user details on successful login
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }
    }



    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userService.saveUser(user);
        return "User registered successfully!";
    }
    // UserController.java
    @PostMapping("/logout")
    public String logoutUser(HttpServletRequest request, HttpServletResponse response) {
        // Invalidate the session
        request.getSession().invalidate();
        return "Logout successful!";
    }

    @GetMapping("/{userId}/activity")
    public ResponseEntity<UserActivityDto> getUserActivity(@PathVariable Long userId) {
        try {
            UserActivityDto activity = userService.getUserActivity(userId);
            return ResponseEntity.ok(activity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/isLoggedIn")
    public ResponseEntity<Map<String, Boolean>> isLoggedIn(HttpSession session) {
        User user = (User) session.getAttribute("user");
        boolean isLoggedIn = user != null;
        Map<String, Boolean> response = new HashMap<>();
        response.put("loggedIn", isLoggedIn);
        return ResponseEntity.ok(response);
    }

}

