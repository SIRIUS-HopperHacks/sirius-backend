package com.app.hopperhacks.repository;

import com.app.hopperhacks.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    //사용자 placeid으로 조회
    Admin findByAdminId(Long adminId);

    Admin findByEmail(String email);
}
