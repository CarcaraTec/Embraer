package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.dto.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Role, Integer>{
}
