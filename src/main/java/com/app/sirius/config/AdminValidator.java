package com.app.sirius.config;

import com.app.sirius.domain.Admin;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

public class AdminValidator implements Validator {

    // Pattern for validating the local part of the email:
    // Allowed characters: A-Z, a-z, 0-9, ., _, %, +, -
    private static final Pattern LOCAL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+$");


    @Override
    public boolean supports(Class<?> clazz) {
        System.out.println("###LOG : supports(" + clazz.getName() + ")");
        boolean result = Admin.class.isAssignableFrom(clazz);
        System.out.println("###LOG : result : " + result);
        return result;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Admin admin = (Admin) target;
        String email = admin.getEmail();

        // Validate that email is not null or empty
        if (email == null || email.trim().isEmpty()) {
            errors.rejectValue("email", "email cannot be empty.");
        } else {
            int atIndex = email.indexOf('@');
            if (atIndex == -1) {
                errors.rejectValue("email", "Invalid email format: missing '@' symbol.");
            } else {
                String localPart = email.substring(0, atIndex);
                String domainPart = email.substring(atIndex + 1);

                // Check the length of local part
                if (localPart.length() > 64) {
                    errors.rejectValue("email", "Less than 64 characters are allowed for the email address");
                }
                // Validate the local part
                if (!LOCAL_PATTERN.matcher(localPart).matches()) {
                    errors.rejectValue("email", "Invalid characters in the email address.");
                }
            }
        }
    }
}
