package com.app.sirius.controller;

import com.app.sirius.service.AlertService;
import com.sun.net.httpserver.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/alert")
public class AlertController {
    @Autowired
    private AlertService alertService;

    public AlertController(){System.out.println("###LOG : AlertController() 생성");}

    @GetMapping("/detail")
    public void detail(Long alertId, Model model, HttpContext context){
        model.addAttribute("list", alertService.detail(alertId));
        model.addAttribute("conPath", context.getPath());
    }

    @GetMapping("/list")
    public void list(Integer page, Model model){
        alertService.list(page,model);
    }

    @PostMapping("/delete")
    public String deleteSuccess(long alertId, Model model){
        model.addAttribute("result", alertService.delete(alertId));
        return "alert/deleteSuccess";
    }

    @PostMapping("/pageRows")
    public String pageRows(Integer page, Integer pageRows){
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attrs.getRequest().getSession();
        session.setAttribute("pageRows", pageRows);
        return "redirect:/alert/list?page="+page;
    }


}
