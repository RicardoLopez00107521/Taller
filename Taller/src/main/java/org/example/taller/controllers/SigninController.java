package org.example.taller.controllers;

import jakarta.validation.Valid;
import org.example.taller.domain.dtos.GeneralResponse;
import org.example.taller.domain.dtos.RegisterDTO;
import org.example.taller.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class SigninController {

    private final UserService userService;

    public SigninController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<GeneralResponse> signin(@RequestBody @Valid RegisterDTO info) {
        userService.register(info);

        return GeneralResponse.builder()
                .status(HttpStatus.OK)
                .message("Registro exitoso!")
                .build();
    }

    @GetMapping("/all") //No es util es solo para corroborar la data
    public ResponseEntity<GeneralResponse> findAll() {

        return GeneralResponse.builder()
                .message("List of users!")
                .data(userService.findAll())
                .build();
    }
}
