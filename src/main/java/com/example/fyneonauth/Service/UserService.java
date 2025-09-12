package com.example.fyneonauth.Service;

import com.example.fyneonauth.model.User;
import com.example.fyneonauth.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public void register(String email, String name) {
        if (userRepo.existsByName(name)) {
            throw new IllegalArgumentException("Имя уже занято");
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        userRepo.save(user);
    }

    public boolean isRegistered(String email) {
        return userRepo.findByEmail(email).isPresent();
    }
}
