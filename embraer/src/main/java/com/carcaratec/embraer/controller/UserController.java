package com.carcaratec.embraer.controller;

import com.carcaratec.embraer.model.dto.CreateUserRoleDTO;
import com.carcaratec.embraer.model.dto.User;
import com.carcaratec.embraer.service.CreateRoleUserService;
import com.carcaratec.embraer.service.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    CreateUserService createUserService;

    @Autowired
    CreateRoleUserService createRoleUserService;

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return createUserService.execute(user);
    }

    @PostMapping("/role")
    public User role(@RequestBody CreateUserRoleDTO createUserRoleDTO) {
        return createRoleUserService.execute(createUserRoleDTO);
    }

    @GetMapping("/user")
    @ResponseBody
    public String getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            return userDetails.getUsername();
        }
        return "Nenhum usuário logado";
    }



}
