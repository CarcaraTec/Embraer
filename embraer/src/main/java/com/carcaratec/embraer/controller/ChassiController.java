package com.carcaratec.embraer.controller;

import com.carcaratec.embraer.model.Chassi;
import com.carcaratec.embraer.repository.ChassiRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import oracle.ucp.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChassiController {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ChassiRepository chassiRepository;

    @GetMapping("/chassis")
    public List<Chassi> listarChassi(){
        List<Chassi> lista = chassiRepository.findAll();
        return lista;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Chassi chassi){
        chassiRepository.save(chassi);
        return ResponseEntity.ok(chassi);
    }


}
