package com.carcaratec.embraer.controller;

import com.carcaratec.embraer.model.dto.CreateUserRoleDTO;
import com.carcaratec.embraer.model.dto.User;
import com.carcaratec.embraer.model.record.DadosListagemUser;
import com.carcaratec.embraer.repository.UsuarioRepository;
import com.carcaratec.embraer.service.CreateRoleUserService;
import com.carcaratec.embraer.service.CreateUserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    CreateUserService createUserService;

    @Autowired
    CreateRoleUserService createRoleUserService;

    @Autowired
    UsuarioRepository usuarioRepository;



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

            JSONObject user = new JSONObject();
            user.put("user",userDetails.getUsername());
            user.put("authorities",userDetails.getAuthorities());
            return user.toString();
        }
        return "Nenhum usuário logado";
    }

    @GetMapping("/listUsers/{roleId}")
    public List<DadosListagemUser> listagemUsers (@PathVariable("roleId") UUID roleId){
        var users = usuarioRepository.findByRolesId(roleId).stream().map(DadosListagemUser::new).toList();
        return users;
    }


    @Transactional
    @PostMapping("/login")
    public void login (@RequestBody String user){
        System.out.println("123");
        System.out.println(user);
    }



}
