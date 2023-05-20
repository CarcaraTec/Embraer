package com.carcaratec.embraer.controller;

import com.carcaratec.embraer.model.dto.Chassi;
import com.carcaratec.embraer.model.record.DadosCadastroChassi;
import com.carcaratec.embraer.model.record.DadosListagemChassi;
import com.carcaratec.embraer.repository.ChassiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping("chassi")
public class ChassiController {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ChassiRepository chassiRepository;

    @PostMapping("/insertChassi")
    public void insertChassi(@RequestBody DadosCadastroChassi dados) {
        chassiRepository.save(new Chassi(dados));
    }

    @GetMapping
    public List<DadosListagemChassi> listAllChassis (){
        List<DadosListagemChassi> chassiList = chassiRepository.findAll().stream().map(DadosListagemChassi::new).toList();
        return chassiList;
    }



}
