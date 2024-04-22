package org.example.taller.services.implementations;

import lombok.Data;
import org.example.taller.domain.dtos.LoginDTO;
import org.example.taller.domain.dtos.RegisterDTO;
import org.example.taller.domain.entity.User;
import org.example.taller.exceptions.LoginExceptions;
import org.example.taller.exceptions.RegisterExceptions;
import org.example.taller.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Data
@Service
public class UserServiceImp implements UserService {
    public static final List<User> users = new ArrayList<>(Arrays.asList(
            new User("Fermin Lopez", "ricardo@gmail.com", "passwoord1"),
            new User("Raul Gonzalez", "raul@gmail.com", "passwoord2"),
            new User("Felipe Alonos", "felipe@gmail.com", "passwoord3"),
            new User("Laura Majano", "laura@gmail.com", "passwoord4"),
            new User("Peter Gray", "peter@gmail.com", "passwoord5")
    ));

    @Override
    public void register(RegisterDTO info) {
        User user = this.findByEmail(info.getEmail());

        if (user == null) {
            user = new User();
            users.add(user);

            user.setUsername(info.getUsername());
            user.setUseremail(info.getEmail());
            user.setPassword(info.getPassword());
        }

        else {
            throw new RegisterExceptions("El email que utilizaste ya existe!");
        }
    }

    @Override
    public void access(LoginDTO info) {
        Boolean correctPass;
        User username = this.findByUsername(info.getUsernameoremail());
        User userEmail = this.findByEmail(info.getUsernameoremail());

        if(username == null && userEmail == null) {
            throw new LoginExceptions("Usuario o correo no encontrado!");
        }

        else {
            if(username == null) {
                correctPass = matchPass(userEmail.getPassword(), info.getPassword());
            }
            else {
                correctPass = matchPass(username.getPassword(), info.getPassword());
            }

            if(!correctPass) {
                throw new LoginExceptions("Contraseña incorrecta!");
            }
        }
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findByEmail(String email) {
        return users.stream()
                .filter(b -> b.getUseremail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return users.stream()
                .filter(b -> b.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public Boolean matchPass(String realPass, String properPass) {
        boolean correct = false;

        if (Objects.equals(realPass, properPass)) {
            correct = true;
        }
        return correct;
    }
}