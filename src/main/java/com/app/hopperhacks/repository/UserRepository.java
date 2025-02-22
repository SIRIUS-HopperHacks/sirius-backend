package com.app.hopperhacks.repository;

import com.app.hopperhacks.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    //사용자 userid으로 조회
    User findByUserId(String user_id);

    User findByUserCode(Long user_code);
}
