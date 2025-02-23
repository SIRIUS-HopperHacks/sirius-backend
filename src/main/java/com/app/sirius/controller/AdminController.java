package com.app.sirius.controller;

import com.app.sirius.config.AdminValidator;
import com.app.sirius.domain.Admin;
import com.app.sirius.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
@Tag(name = "Admin API", description = "Admin Functions API")
public class AdminController {
    private AdminService adminService;

    public AdminController() {System.out.println("###LOG : "+getClass().getName() + "() 생성");}


    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @PostMapping("/login")
    @Operation(summary = "Admin Login", description = "Endpoint for admin login. Session information will be stored upon successful login.")
    public String login(Admin admin, HttpSession session){
        if (adminService.findOne(admin) == 1) {
            session.setAttribute("adminId",admin.getAdminId());
            return "/home";
        }
        else return "/loginfail";
    }


    @GetMapping("/register")
    @Operation(summary = "Display Registration Page", description = "Navigates to the admin registration page.")
    public String register(){
        return "/register";
    }

    @PostMapping("/register")
    @Operation(summary = "Register Admin", description = "Registers a new admin and stores session information.")
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

        String page = "/admin/registerSuccess";
        int cnt = adminService.register(admin);
        model.addAttribute("result",cnt);
        return page;

    }

    @InitBinder
    public void initBinder(WebDataBinder binder){binder.setValidator(new AdminValidator());}
}
