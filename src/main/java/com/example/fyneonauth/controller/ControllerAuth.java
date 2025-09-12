package com.example.fyneonauth.controller;

import com.example.fyneonauth.Service.EmailService;
import com.example.fyneonauth.Service.JwtService;
import com.example.fyneonauth.Service.OtpService;
import com.example.fyneonauth.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        emailService.sendEmail(email, "Код подтверждения", "Ваш код: " + code);
        return ResponseEntity.ok("Код отправлен на " + email);
    }

    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestParam String email,@RequestParam String code) {
        try {
            boolean isValid = otpService.verifyOtp(email, code);
            if (isValid) {
                System.out.println("Код подтверждён");
                System.out.println("JWT TOKEN: ");
                String token = jwtService.generateToken(email);
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(401).body("Неверный или просроченный код");
            }
        } catch (Exception e) {
            e.printStackTrace(); // временно
            return ResponseEntity.status(500).body("Ошибка сервера: " + e.getMessage());
        }
    }

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public ResponseEntity<String> me(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwtService.extractEmail(token);
        System.out.println("JWT TOKEN: " + token);
        return ResponseEntity.ok("Вы вошли как: " + email);
    }

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestHeader("Authorization") String authHeader,@RequestParam String name) {
        System.out.println("name = " + name);
        try {
            String token = authHeader.replace("Bearer ", "");
            String email = jwtService.extractEmail(token);
            userService.register(email, name);
            System.out.println("authHeader = " + authHeader);
            return ResponseEntity.ok("Пользователь зарегистрирован");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка сервера: " + e.getMessage());
        }
    }
}
