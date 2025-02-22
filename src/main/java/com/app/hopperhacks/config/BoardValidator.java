package com.app.hopperhacks.config;

import com.app.hopperhacks.domain.Board;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz){
        System.out.println("###LOG : supports("+clazz.getName()+")");
        boolean result = Board.class.isAssignableFrom(clazz);
        System.out.println("###LOG : result : "+result);
        return result;
    }

    @Override
    public void validate(Object target, Errors errors){
        Board board = (Board) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"board_title", "title cannot be empty.");
    }
}
