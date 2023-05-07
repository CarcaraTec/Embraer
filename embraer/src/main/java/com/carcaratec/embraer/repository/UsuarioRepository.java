package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.dto.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
