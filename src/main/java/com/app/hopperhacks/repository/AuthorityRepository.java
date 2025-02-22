package com.app.hopperhacks.repository;


import com.app.hopperhacks.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByAuth_name(String auth_name);
}
