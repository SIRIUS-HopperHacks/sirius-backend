package com.app.sirius.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OtpValidationService {
    private final Map<String, String> otpStore = new HashMap<>();

    public void storeOtp(String email, String otp){
        otpStore.put(email,otp);
    }

    public boolean validateOtp(String email, String otp){
        return otp.equals(otpStore.get(email));
    }
}
