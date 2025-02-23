package com.app.hopperhacks.service;

import com.app.hopperhacks.domain.Admin;
import com.app.hopperhacks.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    public void setplaceRepository(AdminRepository adminRepository){this.adminRepository = adminRepository;}


    public AdminService(){System.out.println("###LOG : "+getClass().getName()+"() 생성");}


    public boolean isExist(String email){
        Admin admin = adminRepository.findByEmail(email);
        return email != null;
    }

    //회원가입
    public int register(Admin admin){
        admin = adminRepository.save(admin); //저장 후 id 값 반환
        return 1;
    }
}
