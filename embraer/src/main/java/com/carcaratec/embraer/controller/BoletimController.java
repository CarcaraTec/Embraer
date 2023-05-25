package com.carcaratec.embraer.controller;

import com.carcaratec.embraer.model.dto.BoletimServico;
import com.carcaratec.embraer.model.record.DadosCadastroBoletim;
import com.carcaratec.embraer.repository.BoletimServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "boletim")
public class BoletimController {

    @Autowired
    private BoletimServicoRepository boletimServicoRepository;

    @PostMapping("/insertBoletim")
    public void insertBoletim (@RequestBody DadosCadastroBoletim dados){
        boletimServicoRepository.save(new BoletimServico(dados));
    }

    @GetMapping
    public List<BoletimServico> findAllBoletim(){
        List<BoletimServico> list = boletimServicoRepository.findAll();
        return list;
    }


    public void setBoletimServicoRepository(BoletimServicoRepository boletimServicoRepository) {
        this.boletimServicoRepository = boletimServicoRepository;
    }
}
