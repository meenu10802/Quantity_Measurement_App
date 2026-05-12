package com.example.user_service.auth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {

        OAuth2User oauthUser = super.loadUser(userRequest);

        String googleId = oauthUser.getAttribute("sub");
        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");
        String picture = oauthUser.getAttribute("picture");

        UserEntity user = userRepository.findByEmail(email)
                .orElse(null);

        if (user == null) {

            user = UserEntity.builder()
                    .email(email)
                    .googleId(googleId)
                    .name(name)
                    .pictureUrl(picture)
                    .provider(AuthProvider.GOOGLE)
                    .role(UserRole.USER)
                    .build();

        } else {

            user.setGoogleId(googleId);
            user.setName(name);
            user.setPictureUrl(picture);
        }

        userRepository.saveAndFlush(user);

        return oauthUser;
    }
}