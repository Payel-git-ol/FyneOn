package com.example.fyneonauth.controller;

import com.example.fyneonauth.Service.EmailService;
import com.example.fyneonauth.Service.OtpService;
import com.example.fyneonauth.model.OtpCode;
import com.example.fyneonauth.model.User;
import com.example.fyneonauth.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/auth")
public class ControllerAuth {
    @Autowired
    private OtpService otpService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/request-code")
    public ResponseEntity<String> requestCode(@RequestParam String email) {
        String code = otpService.generateOtp(email);
        emailService.sendEmail(email, "Код подтверэдения", "Ваш код: " + code);
        return ResponseEntity.ok("Код отправлен на " + email);
    }
}
