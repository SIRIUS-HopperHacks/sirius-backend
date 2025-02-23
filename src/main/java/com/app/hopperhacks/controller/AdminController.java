package com.app.hopperhacks.controller;

import com.app.hopperhacks.config.AdminValidator;
import com.app.hopperhacks.domain.Admin;
import com.app.hopperhacks.service.AdminService;
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
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    public AdminController() {System.out.println("###LOG : "+getClass().getName() + "() 생성");}

    @GetMapping("/login")
    public void login(){
        //TODO : 세션에 로그인 정보 저장
    }

    @GetMapping
    public String register(){
        return "/login";
    }

    @PostMapping("/register")
    public String registerSuccess(@Valid Admin admin, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        //아이디 중복검사
        if(!result.hasFieldErrors("email") && adminService.isExist(admin.getEmail())){
            result.rejectValue("email","email already exists");
        }

        //검증 에러시 redirect
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("adminId", admin.getAdminId());
            redirectAttributes.addFlashAttribute("email", admin.getEmail());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err: errList){
                redirectAttributes.addFlashAttribute("error", err.getCode());
                break;
            }
            return "redirect:/admin/register";
        }

        //TODO
        //비밀번호 특수문자 포함여부나 글자수 등의 추가 검사

        String page = "/admin/registerSuccess";
        int cnt = adminService.register(admin);
        model.addAttribute("result",cnt);
        return page;

    }

    @InitBinder
    public void initBinder(WebDataBinder binder){binder.setValidator(new AdminValidator());}
}
