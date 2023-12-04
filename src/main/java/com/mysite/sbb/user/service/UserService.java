package com.mysite.sbb.user.service;

import com.mysite.sbb.exception.DataNotFoundException;
import com.mysite.sbb.user.model.SiteUser;
import com.mysite.sbb.user.repository.UserRepository;
import java.util.Optional;
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

    public SiteUser getUser(String username) {
        Optional<SiteUser> findUser = userRepository.findByUsername(username);

        if (findUser.isEmpty()) {
            throw new DataNotFoundException("사용자를 찾을 수 없습니다.");
        }

        return findUser.get();
    }

    public SiteUser findById(Long userId) {
        return userRepository.findById(userId).get();
    }

    public long count() {
        return userRepository.count();
    }
}
