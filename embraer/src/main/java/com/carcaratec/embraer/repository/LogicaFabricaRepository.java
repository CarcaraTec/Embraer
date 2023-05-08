package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.dto.LogicaFabrica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogicaFabricaRepository extends JpaRepository<LogicaFabrica, Integer> {

    @Query(value = "SELECT * FROM LOGICA_FABRICA WHERE ID_ITEM = ?1", nativeQuery = true)
    List<LogicaFabrica> findByIdItem (Integer idItem);

    @Query(value = "SET SERVEROUTPUT ON;\nDECLARE result NUMBER; BEGIN result := fabrica.verificar_instalacao_fabrica(?1, ?2); END;",nativeQuery = true)
    Integer findItemFactory(Integer chassi, Integer idItem);
}
