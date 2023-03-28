package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.BoletimItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletimItemRepository extends JpaRepository<BoletimItem, String> {
}
