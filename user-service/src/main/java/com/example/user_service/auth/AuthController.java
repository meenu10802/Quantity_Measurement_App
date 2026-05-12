package com.example.user_service.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/google-login-url")
    public Map<String, String> googleLoginUrl() {
        return Map.of(
                "url",
                "http://localhost:8080/oauth2/authorization/google"
        );
    }

    @GetMapping("/me")
    public ResponseEntity<?> currentUser(Authentication authentication) {

        if (authentication == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        String email = authentication.getName();

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("email", user.getEmail());
        body.put("name", user.getName());
        body.put("pictureUrl", user.getPictureUrl());
        body.put("role", user.getRole().name());

        return ResponseEntity.ok(body);
    }
}