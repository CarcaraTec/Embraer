package com.carcaratec.embraer.controller;

import com.carcaratec.embraer.dataImporter.LoadData;
import com.carcaratec.embraer.model.Chassi;
import com.carcaratec.embraer.model.ChassiBoletim;
import com.carcaratec.embraer.repository.ChassiBoletimRepository;
import com.carcaratec.embraer.repository.ChassiRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ChassiController {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ChassiRepository chassiRepository;
    @Autowired
    private ChassiBoletimRepository chassiBoletimRepository;

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

    @PostMapping("/insertA")
    public ResponseEntity<?> insertA() throws IOException, InterruptedException {
        ChassiBoletim chassi = new ChassiBoletim();

        LoadData loadData = new LoadData();
        int lines = loadData.countLine();

        for(int i = 1;i<=lines;i++){
            chassi = loadData.getBloco(i).getBody();

            System.out.println(chassi.getIdChassi());
            System.out.println(chassi.getIdBoletim());;
            System.out.println(chassi.getStatus());;

            chassiBoletimRepository.save(chassi);
        }

        return ResponseEntity.ok(chassi);
    }


}
