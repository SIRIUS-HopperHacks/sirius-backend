package com.app.hopperhacks.service;

import com.app.hopperhacks.domain.Authority;
import com.app.hopperhacks.domain.User;
import com.app.hopperhacks.repository.AuthorityRepository;
import com.app.hopperhacks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository){this.userRepository = userRepository;}
    @Autowired
    public void setAuthorityRepository(AuthorityRepository authorityRepository){this.authorityRepository = authorityRepository;}


    public UserService(){System.out.println("###LOG : "+getClass().getName()+"() 생성");}


    public boolean isExist(String user_id){
        User user = userRepository.findByUserId(user_id);
        return user != null;
    }

    //회원가입
    public int register(User user){
        user = userRepository.save(user); //저장 후 user_code 값 반환

        Authority auth = authorityRepository.findByAuthName("MEMBER");
        user.addAuthority(auth);
        userRepository.save(user);
        return 1;
    }

    // 특정 사용자(code)의 권한
    public List<Authority> selectAuthorityByUser_code(Long user_code){
        User user = userRepository.findByUserCode(user_code);

        if(user != null) return user.getAuthorities();

        return new ArrayList<>();
    }
}
