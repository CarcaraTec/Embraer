package com.carcaratec.embraer.controller;

import com.carcaratec.embraer.dataImporter.LoadData;
import com.carcaratec.embraer.model.BoletimServico;
import com.carcaratec.embraer.model.Chassi;
import com.carcaratec.embraer.model.ChassiBoletim;
import com.carcaratec.embraer.model.Path;
import com.carcaratec.embraer.repository.BoletimServicoRepository;
import com.carcaratec.embraer.repository.ChassiBoletimRepository;
import com.carcaratec.embraer.repository.ChassiRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ChassiController {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ChassiRepository chassiRepository;

    @Autowired
    private BoletimServicoRepository boletimServicoRepository;

    @Autowired
    private ChassiBoletimRepository chassiBoletimRepository;

    @GetMapping("/chassis")
    public List<Chassi> listarChassi(){
        List<Chassi> lista = chassiRepository.findAll();
        return lista;
    }

    @PostMapping("/insertChassi")
    public ResponseEntity<?> insertChassi(@RequestBody Chassi chassi){
        chassiRepository.save(chassi);
        return ResponseEntity.ok(chassi);
    }

    @PostMapping("/insertBoletim")
    public ResponseEntity<?> insertBoletim(@RequestBody BoletimServico boletimServico){
        boletimServicoRepository.save(boletimServico);
        return ResponseEntity.ok(boletimServico);
    }

    @PostMapping("/insertChassiBoletim")
    public ResponseEntity<?> insertChassiBoletim(@RequestBody ChassiBoletim chassiBoletim){
        chassiBoletimRepository.save(chassiBoletim);
        return ResponseEntity.ok(chassiBoletim);
    }

    @PostMapping("/insertA")
    public ResponseEntity<?> insertA(@RequestBody Path path) throws IOException, InterruptedException {
        Chassi chassi = new Chassi();
        BoletimServico boletimServico = new BoletimServico();
        ChassiBoletim chassiBoletim = new ChassiBoletim();
        LoadData loadData = new LoadData();
        String p = path.getCaminho();
        System.out.println(path);
        loadData.convert(p);
        String[] path2 = p.split("\\.");

        p = path2[0] + ".csv";

        int lines = loadData.countLine(p);

        for(int i = 1;i<=lines;i++){
            chassiBoletim = loadData.getBloco(i, p).getBody();
            chassi.setIdChassi(chassiBoletim.getIdChassi());
            boletimServico.setIdBoletim(chassiBoletim.getIdBoletim());

            chassiRepository.save(chassi);
            boletimServicoRepository.save(boletimServico);
            chassiBoletimRepository.insertChassiBoletim(chassiBoletim.getIdChassi(),chassiBoletim.getIdBoletim(), chassiBoletim.getStatus());
        }

        return ResponseEntity.ok(chassi);
    }


    public static void main(String[] args) {

    }

}
