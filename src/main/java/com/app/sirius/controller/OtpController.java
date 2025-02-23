package com.app.sirius.controller;

import com.app.sirius.config.GmailSMTP;
import com.app.sirius.service.OtpOperatorService;
import com.app.sirius.service.OtpValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;

@Controller
@RequestMapping("/otp")
public class OtpController {
    @Autowired
    public OtpOperatorService otpOperatorService;
    @Autowired
    public OtpValidationService otpValidationService;

    @GetMapping("/otp")
    public void otp(){}


    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestParam String email){
        try{
            String otp = otpOperatorService.generateOtp();
            otpValidationService.storeOtp(email,otp);
            GmailSMTP.send_gmail(email,otp);
            return ResponseEntity.ok("OTP has been sent to your email");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error sending OTP");
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String email,@RequestParam String otp){
        if (otpValidationService.validateOtp(email, otp)){
            return ResponseEntity.ok("OTP is valid");
        } else {
            return ResponseEntity.status(400).body("Invalid OTP");
        }
    }

}
