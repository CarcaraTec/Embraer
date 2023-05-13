package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.dto.Hierarquia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HierarquiaRepository extends JpaRepository<Hierarquia, Integer> {

    List<Hierarquia> findByIdItemOrderByNivelDesc(Integer idItem);
    List<Hierarquia> findByIdItem(Integer idItem);
    List<Hierarquia> findByIdLogica(Integer idLogica);
}
