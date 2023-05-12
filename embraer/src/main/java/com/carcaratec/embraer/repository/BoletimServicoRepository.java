package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.dto.BoletimServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletimServicoRepository extends JpaRepository<BoletimServico, String> {
}
