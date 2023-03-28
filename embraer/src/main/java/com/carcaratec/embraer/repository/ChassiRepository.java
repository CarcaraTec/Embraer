package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.Chassi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface ChassiRepository extends JpaRepository<Chassi, Integer> {
}
