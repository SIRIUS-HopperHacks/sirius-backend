package com.app.hopperhacks.config;

import com.app.hopperhacks.domain.Authority;
import com.app.hopperhacks.repository.AuthorityRepository;
import org.springframework.boot.CommandLineRunner;

import javax.transaction.Transactional;

public class AuthorityInitializer implements CommandLineRunner {
    private final AuthorityRepository authorityRepository;

    public AuthorityInitializer(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (authorityRepository.count() == 0) { // 권한이 없을 때만 추가
            authorityRepository.save(new Authority((long)1,"ADMIN"));
            authorityRepository.save(new Authority((long)2,"MEMBER"));
            System.out.println("AUTH 데이터 초기화 완료!");
        } else {
            System.out.println("AUTH 데이터가 이미 존재합니다.");
        }
    }
}
