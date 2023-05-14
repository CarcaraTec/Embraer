package com.carcaratec.embraer.service;

import com.carcaratec.embraer.model.dto.Usuario;
import com.carcaratec.embraer.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void criarUsuario(@RequestBody Usuario usuario){
        usuario.setSenha(passwordEncoder().encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
    }
}
