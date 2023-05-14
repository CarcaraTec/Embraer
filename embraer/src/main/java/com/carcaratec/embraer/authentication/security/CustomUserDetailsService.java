package com.carcaratec.embraer.authentication.security;

import com.carcaratec.embraer.model.dto.Role;
import com.carcaratec.embraer.model.dto.Usuario;
import com.carcaratec.embraer.repository.PermissaoRepository;
import com.carcaratec.embraer.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PermissaoRepository permissaoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuarioExistente = usuarioRepository.findByLogin(username);

        List<Role> roleList = permissaoRepository.findAll();

        usuarioExistente.setRoles(roleList);


        if (usuarioExistente == null) {
            throw new Error ("O usuário existe!");
        }

        System.out.println(username);
        System.out.println(usuarioExistente);

        return UserPrincipal.create(usuarioExistente);
    }


}
