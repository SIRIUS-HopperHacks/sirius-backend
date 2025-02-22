package com.app.hopperhacks.config;

import com.app.hopperhacks.domain.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz){
        System.out.println("###LOG : supports("+clazz.getName()+")");
        boolean result = User.class.isAssignableFrom(clazz);
        System.out.println("###LOG : result : "+result);
        return result;
    }

    @Override
    public void validate(Object target, Errors errors){
        User user = (User) target;

        //이름 공백 판별
        String user_name = user.getUserName();
        if (user_name == null || user_name.trim().isEmpty()) {
            errors.rejectValue("user_name","space is not available.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"user_name","name cannot be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"user_password", "password is mendatory.");

        //입력 paassword, re_password 가 동일한지 비교
        if(!user.getUserPassword().equals(user.getUserRepassword())){
            errors.rejectValue("user_repassword", "password doesn't match");
        }
    }
}
