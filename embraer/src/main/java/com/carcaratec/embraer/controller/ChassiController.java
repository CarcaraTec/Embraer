package com.carcaratec.embraer.controller;

import com.carcaratec.embraer.model.dto.Chassi;
import com.carcaratec.embraer.model.record.DadosCadastroChassi;
import com.carcaratec.embraer.model.record.DadosListagemChassi;
import com.carcaratec.embraer.repository.ChassiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

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

    @GetMapping("/listPropChassi/{userId}")
    public List<Chassi> listPropChassi (@PathVariable("userId") UUID userId){
        var chassis = chassiRepository.findByProprietario(userId);
        return chassis;
    }

    @Transactional
    @PostMapping("/signChassi")
    public void asignChassi(@RequestBody Chassi chassi) {
        System.out.println(chassi.getIdChassi());
        System.out.println(chassi.getProprietario());
        chassiRepository.save(chassi);
    }
}
