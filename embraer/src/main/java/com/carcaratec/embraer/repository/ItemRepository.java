package com.carcaratec.embraer.repository;

import com.carcaratec.embraer.model.dto.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByCategoria(String category);

    @Query(value = "SELECT DISTINCT CATEGORIA FROM ITEM",nativeQuery = true)
    List<String> findCategory();

    List<Item> findAllByIdItem(int i);

    List<Item> findAllByCategoria(String category);
}
