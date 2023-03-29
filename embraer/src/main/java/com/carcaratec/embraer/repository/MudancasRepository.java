package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.ChassiBoletim;
import com.carcaratec.embraer.model.Mudancas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MudancasRepository extends JpaRepository<Mudancas, Integer> {
}
