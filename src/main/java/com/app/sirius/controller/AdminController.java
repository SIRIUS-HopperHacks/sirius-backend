package com.app.sirius.controller;

import com.app.sirius.config.AdminValidator;
import com.app.sirius.domain.Admin;
import com.app.sirius.domain.LoginRequest;
import com.app.sirius.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@Tag(name = "Admin API", description = "Admin Functions API")
public class AdminController {
    @Autowired
    private AdminService adminService;

    public AdminController() {System.out.println("###LOG : "+getClass().getName() + "() 생성");}


    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @PostMapping("/login")
    @ResponseBody
    @Operation(summary = "Admin Login", description = "Endpoint for admin login. Session information will be stored upon successful login.")
    public ResponseEntity<Map<String,Object>> login(@RequestParam LoginRequest loginRequest){
        Admin admin = adminService.findByEmail(loginRequest.getEmail());
        Map<String,Object> response = new HashMap<>();

        if (admin!=null) {
            response.put("status","confirmed");
            response.put("adminId", admin.getAdminId());
            response.put("email", admin.getEmail());
            response.put("password", admin.getPassword());
            response.put("organizationType", admin.getOrganizationType());
            response.put("createdTime", admin.getCreatedTime());

        }
        else response.put("status","denied");
        return ResponseEntity.ok(response);
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
