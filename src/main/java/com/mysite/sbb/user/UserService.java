package com.mysite.sbb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String mail, String password) {
        SiteUser siteUser = SiteUser.builder()
                .username(username)
                .email(mail)
                .password(passwordEncoder.encode(password))
                .build();

        userRepository.save(siteUser);
        return siteUser;
    }

}
