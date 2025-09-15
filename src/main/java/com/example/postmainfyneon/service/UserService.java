package com.example.postmainfyneon.service;

import com.example.postmainfyneon.model.hash.UsersHash;
import com.example.postmainfyneon.model.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UsersRepository usersRepo;

    @Autowired
    public UserService(UsersRepository usersRepo) {
        this.usersRepo = usersRepo;
    }

    public UsersHash save(UsersHash usersHash) {
        return usersRepo.save(usersHash);
    }

    public UsersHash save(int id, String email, String name) {
        UsersHash user = new UsersHash();
        user.setId(id);
        user.setEmail(email);
        user.setName(name);
        return usersRepo.save(user);
    }

}
