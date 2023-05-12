package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.dto.Chassi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChassiRepository extends JpaRepository<Chassi,Integer> {
}
