package org.example.taller.controllers;

import jakarta.validation.Valid;
import org.example.taller.domain.dtos.GeneralResponse;
import org.example.taller.domain.dtos.LoginDTO;
import org.example.taller.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<GeneralResponse> login(@RequestBody @Valid LoginDTO info) {
        userService.access(info);

        return GeneralResponse.builder()
                .status(HttpStatus.OK)
                .message("Acceso exitoso!")
                .build();
    }
}
