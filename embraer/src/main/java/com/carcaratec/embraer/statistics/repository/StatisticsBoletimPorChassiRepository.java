package com.carcaratec.embraer.statistics.repository;

import com.carcaratec.embraer.statistics.model.StatisticsBoletimPorChassi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsBoletimPorChassiRepository extends JpaRepository<StatisticsBoletimPorChassi, Integer> {
    List<StatisticsBoletimPorChassi> findByIdChassi(Integer idChassi);
}
