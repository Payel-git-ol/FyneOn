package com.example.fyneonauth.Service;

import com.example.fyneonauth.dto.KafkaProducer;
import com.example.fyneonauth.model.User;
import com.example.fyneonauth.model.UserEvent;
import com.example.fyneonauth.model.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Transactional
    public void register(String email, String name) {
        if (userRepo.existsByName(name)) {
            throw new IllegalArgumentException("Имя уже занято");
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        User savedUser = userRepo.save(user);

        UserEvent event = new UserEvent(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );

        kafkaProducer.sendUserCreatedEvent(event);
    }

    public boolean isRegistered(String email) {
        return userRepo.findByEmail(email).isPresent();
    }

    public Optional<User> getUser(String email) {
        return userRepo.findByEmail(email);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
