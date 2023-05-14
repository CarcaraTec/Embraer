package com.carcaratec.embraer.controller;

import com.carcaratec.embraer.model.dto.Usuario;
import com.carcaratec.embraer.repository.UsuarioRepository;
import com.carcaratec.embraer.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("usuarios")
@PreAuthorize("hasRole('1')")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping

    public List<Usuario> listarUsuarios (){
        return usuarioRepository.findAll();
    }

    @PostMapping("/criar")
    @Transactional
    public void criarUsuario(@RequestBody Usuario usuario){
        usuarioService.criarUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable String id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
