package com.carcaratec.embraer.statistics.repository;

import com.carcaratec.embraer.statistics.model.UtilizacoesBoletins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilizacoesBoletinsRepository extends JpaRepository<UtilizacoesBoletins, String> {
    List<UtilizacoesBoletins> findByIdBoletim(String idBoletim);
}
