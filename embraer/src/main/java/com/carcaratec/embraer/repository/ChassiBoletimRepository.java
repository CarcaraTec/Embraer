package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.ChassiBoletim;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ChassiBoletimRepository extends JpaRepository<ChassiBoletim, Integer> {
  @Transactional
  @Modifying
  @Query(value = "INSERT INTO CHASSI_BOLETIM (ID_CHASSI, ID_BOLETIM, STATUS) VALUES (?1, ?2, ?3)", nativeQuery = true)
  void insertChassiBoletim(Integer idChassi, String idBoletim, String status);

  @Query(value = "SELECT * FROM CHASSI_BOLETIM WHERE ID_CHASSI = ?1 ORDER BY ID_BOLETIM ASC",nativeQuery = true)
  List<ChassiBoletim> findBoletimByChassi(Integer idChassi);

  @Query(value = "SELECT * FROM CHASSI_BOLETIM WHERE ID_BOLETIM = ?1 AND ID_CHASSI = ?2",nativeQuery = true)
  List<ChassiBoletim> findBoletimByIdAndChassi(String idBoletim, Integer idChassi);

  @Query(value = "UPDATE CHASSI_BOLETIM SET STATUS = ?1 WHERE ID_CHASSI = ?2 AND ID_BOLETIM = ?3",nativeQuery = true)
  List<ChassiBoletim> updateChassi(String status,Integer idChassi,String idBoletim);
  }