package com.mysite.sbb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public SiteUser create(String username, String mail, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        SiteUser siteUser = SiteUser.builder()
                .username(username)
                .email(mail)
                .password(passwordEncoder.encode(password))
                .build();

        userRepository.save(siteUser);
        return siteUser;
    }

}
