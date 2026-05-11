package com.bridgelabz.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class OAuth2LoginSuccessHandler
        implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    public OAuth2LoginSuccessHandler(UserRepository userRepository,
                                     JwtService jwtService,
                                     ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        String googleId = oauthUser.getAttribute("sub");
        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");
        String picture = oauthUser.getAttribute("picture");

        UserEntity user = userRepository.findByEmail(email)
                .orElse(UserEntity.builder()
                        .email(email)
                        .googleId(googleId)
                        .name(name)
                        .pictureUrl(picture)
                        .provider(AuthProvider.GOOGLE)
                        .role(UserRole.USER)
                        .build());

        user.setGoogleId(googleId);
        user.setName(name);
        user.setPictureUrl(picture);

        user = userRepository.save(user);

        String token = jwtService.generateToken(user);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("tokenType", "Bearer");
        body.put("token", token);
        body.put("email", user.getEmail());
        body.put("name", user.getName());
        body.put("role", user.getRole().name());

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);

        objectMapper.writeValue(response.getWriter(), body);
    }
}