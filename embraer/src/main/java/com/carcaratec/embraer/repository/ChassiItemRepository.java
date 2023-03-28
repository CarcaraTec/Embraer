package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.ChassiItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChassiItemRepository extends JpaRepository<ChassiItem, Integer> {
}
