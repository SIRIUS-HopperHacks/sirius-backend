package com.app.sirius.config;

import com.app.sirius.domain.Admin;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class AdminValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz){
        System.out.println("###LOG : supports("+clazz.getName()+")");
        boolean result = Admin.class.isAssignableFrom(clazz);
        System.out.println("###LOG : result : "+result);
        System.out.println("###LOG : result : "+result);
        return result;
    }

    @Override
    public void validate(Object target, Errors errors){
        Admin admin = (Admin) target;

        //이름 공백 판별
        String email = admin.getEmail();
        if (email == null || email.trim().isEmpty()) {
            errors.rejectValue("email","space is not available.");
        }

        //TODO : 이메일형태 확인

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","email cannot be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "password is mandatory.");

        //입력 paassword, re_password 가 동일한지 비교
        if(!admin.getPassword().equals(admin.getRepassword())){
            errors.rejectValue("repassword", "password doesn't match");
        }
    }
}
