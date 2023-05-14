package com.carcaratec.embraer.authentication.security;

import com.carcaratec.embraer.model.dto.User;
import com.carcaratec.embraer.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User existsUser = userRepository.findByUsernameFetchRoles(username);

        if (existsUser == null) {
            throw new Error("User does not exists!");
        }

        return UserPrincipal.create(existsUser);
    }

}