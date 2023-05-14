package com.carcaratec.embraer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.carcaratec.embraer.model.dto.CreateUserRoleDTO;
import com.carcaratec.embraer.model.dto.Role;
import com.carcaratec.embraer.model.dto.User;
import com.carcaratec.embraer.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreateRoleUserService {

    @Autowired
    UsuarioRepository userRepository;

    public User execute(CreateUserRoleDTO createUserRoleDTO) {

        Optional<User> userExists = userRepository.findById(createUserRoleDTO.getIdUser());
        List<Role> roles = new ArrayList<>();

        if (userExists.isEmpty()) {
            throw new Error("User does not exists!");
        }

        roles = createUserRoleDTO.getIdsRoles().stream().map(role -> {
            return new Role(role);
        }).collect(Collectors.toList());

        User user = userExists.get();

        user.setRoles(roles);

        userRepository.save(user);

        return user;

    }
}