package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.dto.Chassi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface ChassiRepository extends JpaRepository<Chassi,Integer> {
    List<Chassi> findByProprietario(UUID userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE CHASSI c SET c.proprietario = :userId WHERE c.idChassi = :idChassi",nativeQuery = true)
    void updateProprietario(@Param("userId") UUID userId, @Param("idChassi") Integer idChassi);

    @Query(value = "select * from chassi where proprietario = :userId",nativeQuery = true)
    List<Chassi> findProp (@Param("userId") String userId);

}
