package com.carcaratec.embraer.controller;

import com.carcaratec.embraer.model.dto.Item;
import com.carcaratec.embraer.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public List<Item> listarItems() {
        List<Item> lista = itemRepository.findAll();
        return lista;
    }

    @GetMapping("/category")
    public List<String> category(){
        List<String>category = itemRepository.findCategory();
        List<String>categoryFormated = new ArrayList<>();
        for(int i = 0;i<category.size();i++){
            categoryFormated.add(category.get(i).toString().toUpperCase());
        }
        return categoryFormated;
    }

    @GetMapping("/findByCategory")
    public List<Item> findByCategory(@RequestParam("category") String category){
        List<Item> findByCategoria = itemRepository.findByCategoria(category);
        return findByCategoria;
    }
}
