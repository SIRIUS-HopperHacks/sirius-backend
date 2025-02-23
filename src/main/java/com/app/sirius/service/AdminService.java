package com.app.sirius.service;

import com.app.sirius.domain.Admin;
import com.app.sirius.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    public void setAdminRepository(AdminRepository adminRepository){this.adminRepository = adminRepository;}

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

    public int findOne(Admin admin){
        Admin admin_result = adminRepository.findByAdminId(admin.getAdminId());
        if (admin_result != null) return 1;
        return 0;
    }

    public Admin findByEmail(String email){
        Admin admin = adminRepository.findByEmail(email);
        return admin;
    }
}
