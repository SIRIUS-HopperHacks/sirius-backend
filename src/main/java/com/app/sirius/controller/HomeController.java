package com.app.sirius.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Tag(name = "Home API", description = "Endpoints for the home page and general navigation")
public class HomeController {
    public HomeController(){System.out.println("###LOG : "+getClass().getName() + "() 생성");}

    @GetMapping("/home")
    @Operation(summary = "Display Home Page", description = "Renders the home page for general navigation.")
    public void home(){}

}
