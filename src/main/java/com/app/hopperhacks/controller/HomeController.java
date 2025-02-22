package com.app.hopperhacks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {
    public HomeController(){System.out.println("###LOG : "+getClass().getName() + "() 생성");}

    @RequestMapping("/home")
    public void home(){}


}
