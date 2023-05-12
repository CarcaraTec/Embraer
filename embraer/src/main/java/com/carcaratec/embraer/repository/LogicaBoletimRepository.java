package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.dto.LogicaBoletim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogicaBoletimRepository extends JpaRepository<LogicaBoletim, Integer> {

    @Query(value = "SELECT * FROM LOGICA_BOLETIM WHERE ID_ITEM = ?1", nativeQuery = true)
    List<LogicaBoletim> findByItem (Integer item);
}
