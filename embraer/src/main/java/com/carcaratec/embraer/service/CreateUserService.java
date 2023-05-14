package com.carcaratec.embraer.service;


import com.carcaratec.embraer.model.dto.User;
import com.carcaratec.embraer.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class CreateUserService {

    @Autowired
    UsuarioRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User execute(User user) {

        User existsUser = userRepository.findByUsername(user.getUsername());

        if (existsUser != null) {
            throw new Error("User already exists!");
        }

        user.setPassword(passwordEncoder().encode(user.getPassword()));

        User createdUser = userRepository.save(user);

        return createdUser;
    }

}