package com.app.sirius.controller;

import com.app.sirius.service.AlertService;
import com.sun.net.httpserver.HttpContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/alert")
@Tag(name = "Alert API", description = "API for managing alerts")
public class AlertController {
    @Autowired
    private AlertService alertService;

    public AlertController(){System.out.println("###LOG : AlertController() 생성");}

    @GetMapping("/detail")
    @Operation(summary = "Get Alert Detail", description = "Retrieves detailed information for a specific alert by alertId and connection context.")
    public void detail(Long alertId, Model model, HttpContext context){
        model.addAttribute("list", alertService.detail(alertId));
        model.addAttribute("conPath", context.getPath());
    }

    @GetMapping("/list")
    @Operation(summary = "List Alerts", description = "Displays a paginated list of alerts.")
    public void list(Integer page, Model model){
        alertService.list(page,model);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete Alert", description = "Deletes an alert by its ID and returns a deletion result page.")
    public String deleteSuccess(long alertId, Model model){
        model.addAttribute("result", alertService.delete(alertId));
        return "alert/deleteSuccess";
    }

    @PostMapping("/pageRows")
    @Operation(summary = "Set Page Rows", description = "Sets the number of rows per page in the session and redirects to the alert list.")
    public String pageRows(Integer page, Integer pageRows){
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attrs.getRequest().getSession();
        session.setAttribute("pageRows", pageRows);
        return "redirect:/alert/list?page="+page;
    }


}
