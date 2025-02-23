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
import java.util.regex.Pattern;

@Controller
@RequestMapping("/admin")
@Tag(name = "Admin API", description = "Admin Functions API")
public class AdminController {
    @Autowired
    private AdminService adminService;

    public AdminController() {System.out.println("###LOG : "+getClass().getName() + "() 생성");}

    private static final Pattern LOCAL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+$");

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @PostMapping("/login")
    @ResponseBody
    @Operation(summary = "Admin Login", description = "Endpoint for admin login. Session information will be stored upon successful login.")
    public ResponseEntity<Map<String,Object>> login(@RequestBody LoginRequest loginRequest){
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
    @ResponseBody
    @Operation(summary = "Register Admin", description = "Registers a new admin and stores session information.")
    public ResponseEntity<Map<String, Object>> registerSuccess(@RequestBody Admin admin){
        Map<String, Object> response = new HashMap<>();

        if(adminService.isExist(admin.getEmail())){
            response.put("status","failed. email already exists");
        }

        String email = admin.getEmail();
        if (email == null || email.trim().isEmpty()) {
            response.put("status", "failed. email cannot be empty");
        } else {
            int atIndex = email.indexOf('@');
            if (atIndex == -1) {
                response.put("status", "failed. Invalid email format: missing '@' symbol");
            } else {
                String localPart = email.substring(0, atIndex);
                String domainPart = email.substring(atIndex + 1);

                // Check the length of local part
                if (localPart.length() > 64) {
                    response.put("status", "failed. Less than 64 characters are allowed for the email address");
                }
                // Validate the local part
                else if (!LOCAL_PATTERN.matcher(localPart).matches()) {
                    response.put("status", "failed. Invalid characters in the email address");
                }
            }
        }

        int cnt = adminService.register(admin);
        if (cnt > 0) {
            response.put("status","success");
            response.put("status","confirmed");
            response.put("admin_id", admin.getAdminId());
            response.put("email", admin.getEmail());
            response.put("password", admin.getPassword());
            response.put("organization_type", admin.getOrganizationType());
            if ( admin.getCreatedTime()!=null) {response.put("created_time", admin.getCreatedTime());}
        }
        return ResponseEntity.ok(response);
    }
}
