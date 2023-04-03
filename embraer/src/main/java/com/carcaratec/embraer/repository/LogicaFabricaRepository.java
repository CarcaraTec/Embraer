package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.LogicaFabrica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface LogicaFabricaRepository extends JpaRepository<LogicaFabrica, Integer> {

    @Query(value = "SELECT * FROM LOGICA_FABRICA WHERE ID_ITEM = ?1", nativeQuery = true)
    List<LogicaFabrica> findByIdItem (Integer idItem);
}
