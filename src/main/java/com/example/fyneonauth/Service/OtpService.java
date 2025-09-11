package com.example.fyneonauth.Service;

import com.example.fyneonauth.model.OtpCode;
import com.example.fyneonauth.model.OtpCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class OtpService {
    @Autowired
    private OtpCodeRepository otpRepo;

    public String generateOtp(String email) {
        String code = String.valueOf((int)(Math.random() * 900000) + 100000);
        OtpCode otp = new OtpCode();
        otp.setEmail(email);
        otp.setCode(code);
        otp.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        otpRepo.save(otp);
        return code;
    }
}
