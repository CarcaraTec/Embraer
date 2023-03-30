package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.ChassiBoletim;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository

public interface ChassiBoletimRepository extends JpaRepository<ChassiBoletim, Integer> {
  @Transactional
  @Modifying
  @Query(value = "INSERT INTO CHASSI_BOLETIM (ID_CHASSI, ID_BOLETIM, STATUS) VALUES (?1, ?2, ?3)", nativeQuery = true)
  void insertChassiBoletim(Integer idChassi, String idBoletim, String status);

  }