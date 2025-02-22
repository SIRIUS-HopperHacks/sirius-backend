package com.app.hopperhacks.controller;

import com.app.hopperhacks.config.UserValidator;
import com.app.hopperhacks.domain.User;
import com.app.hopperhacks.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController() {System.out.println("###LOG : "+getClass().getName() + "() 생성");}

    @GetMapping("/login")
    public void login(){
        //TODO
    }

    @GetMapping
    public void register(){
        //TODO
    }

    @PostMapping("/register")
    public String registerSuccess(@Valid User user, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        //아이디 중복검사
        if(!result.hasFieldErrors("user_id") && userService.isExist(user.getUser_id())){
            result.rejectValue("user_id","id already exists");
        }

        //검증 에러시 redirect
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("user_id", user.getUser_id());
            redirectAttributes.addFlashAttribute("user_name", user.getUser_name());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err: errList){
                redirectAttributes.addFlashAttribute("error", err.getCode());
                break;
            }
            return "redirect:/user/register";
        }

        //TODO
        //비밀번호 특수문자 포함여부나 글자수 등의 추가 검사

        String page = "/user/registerSuccess";
        int cnt = userService.register(user);
        model.addAttribute("result",cnt);
        return page;

    }



    @InitBinder
    public void initBinder(WebDataBinder binder){binder.setValidator(new UserValidator());}


}
