package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.ChassiBoletim;
import com.carcaratec.embraer.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByCategoria(String category);

    @Query(value = "SELECT DISTINCT CATEGORIA FROM ITEM",nativeQuery = true)
    List<String> findCategory();


}
