package com.mysite.sbb.user.repository;

import com.mysite.sbb.user.model.SiteUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    Optional<SiteUser> findByUsername(String username);
}
