package com.carcaratec.embraer.service;

import com.carcaratec.embraer.model.dto.ChassiBoletim;
import com.carcaratec.embraer.model.record.DadosCadastroItemReturn;
import com.carcaratec.embraer.repository.ChassiBoletimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public class VerificacaoHierarquia {

    @Autowired
    private ChassiBoletimRepository chassiBoletimRepository;

    public List<Integer> caminhoHierarquia(String caminho){
        List<Integer> listaHierarquia = new ArrayList<>();
        String[] hierarquiaString = caminho.split("/");

        for(int i = 0;i< hierarquiaString.length;i++){
            listaHierarquia.add(Integer.valueOf(hierarquiaString[i]));
        }
        return listaHierarquia;
    }

    public Boolean verificaBoletim(Integer idChassi, String boletim){
        ChassiBoletim listBoletim = chassiBoletimRepository.findBoletimByIdAndChassi(boletim, idChassi);
        boolean isValid = false;
        if(listBoletim != null){
            if(listBoletim.getStatus().equals("INCORPORATED")){
                isValid = true;
            }
        }
        return isValid;
    }

    public String estaInstalado(Integer idChassi, String boletim1, String boletim2) {
        if (verificaBoletim(idChassi, boletim1)) {
            if (verificaBoletim(idChassi, boletim2)) {
                
            }
        }
    }
}
