package com.mysite.sbb.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SiteUser> findUser = userRepository.findByUsername(username);

        if (findUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        SiteUser siteUser = findUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if ("admin".equals(username)) { // admin 경우만 분기 처리
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
            return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);
        }

        authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);
    }
}
