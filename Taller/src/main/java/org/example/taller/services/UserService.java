package org.example.taller.services;

import org.example.taller.domain.dtos.LoginDTO;
import org.example.taller.domain.dtos.RegisterDTO;
import org.example.taller.domain.entity.User;

import java.util.List;

public interface UserService {
    void register(RegisterDTO info);
    void access(LoginDTO info);
    List<User> findAll();
    User findByEmail(String email);
    User findByUsername(String username);
}
