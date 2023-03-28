package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.Logica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogicaRepository extends JpaRepository<Logica, Integer> {
}
