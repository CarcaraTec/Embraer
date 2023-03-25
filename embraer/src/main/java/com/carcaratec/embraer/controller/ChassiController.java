package com.carcaratec.embraer.controller;

import com.carcaratec.embraer.model.Chassi;
import com.carcaratec.embraer.repository.ChassiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChassiController {

    @Autowired
    private ChassiRepository chassiRepository;

    @GetMapping("/chassis")
    public List<Chassi> listarChassi(){
        List<Chassi> lista = chassiRepository.findAll();
        return lista;
    }
}
