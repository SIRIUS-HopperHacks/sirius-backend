package com.app.sirius.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpOperatorService {
    private static final int OTP_LENGTH = 6;

    public String generateOtp(){
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0 ; i < OTP_LENGTH; i++){
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
}
