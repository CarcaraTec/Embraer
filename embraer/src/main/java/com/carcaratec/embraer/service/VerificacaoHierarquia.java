package com.carcaratec.embraer.service;

import com.carcaratec.embraer.model.dto.ChassiBoletim;
import com.carcaratec.embraer.model.record.DadosCadastroItemReturn;
import com.carcaratec.embraer.repository.ChassiBoletimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VerificacaoHierarquia {

    @Autowired
    private ChassiBoletimRepository chassiBoletimRepository;

    public List<Integer> caminhoHierarquia(String caminho){
        List<Integer> listaHierarquia = new ArrayList<>();
        String[] hierarquiaString = caminho.split("/");

        for(int i = 0;i< hierarquiaString.length;i++){
            if(!hierarquiaString[i].equals("")) {
                listaHierarquia.add(Integer.valueOf(hierarquiaString[i]));
            }
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

    public Boolean verificaBoletimApplicable(Integer idChassi, String boletim){
        ChassiBoletim listBoletim = chassiBoletimRepository.findBoletimByIdAndChassi(boletim, idChassi);
        boolean isValid = false;
        if(listBoletim != null){
            if(listBoletim.getStatus().equals("APPLICABLE")){
                isValid = true;
            }
        }
        return isValid;
    }

    public String estaInstaladoAnd(Integer idChassi, String boletim1, String boletim2) {
        String status = "❌";
        if (verificaBoletim(idChassi, boletim1)) {
            if(boletim2!=null) {
                if (verificaBoletim(idChassi, boletim2)) {
                    status = "✔";
                }
            }else{
                status = "✔";
            }
        }
        return status;
    }

    public String isApplicableAnd(Integer idChassi, String boletim1, String boletim2){
        String status = "NOT APPLICABLE";
        if(verificaBoletimApplicable(idChassi,boletim1)) {
            if(boletim2!=null) {
                if(verificaBoletimApplicable(idChassi,boletim2)){
                    status = "APPLICABLE";
                }
            }else{
                status = "APPLICABLE";
            }
        }
        return status;
    }

    public String estaInstaladoOr(Integer idChassi, String boletim1, String boletim2){
        String status = "❌";
        Boolean input1 = verificaBoletim(idChassi,boletim1);
        Boolean input2 = verificaBoletim(idChassi,boletim2);

        if(input1 || input2){
            status = "✔";
        }
        return status;
    }

    public String isApplicableOr(Integer idChassi, String boletim1, String boletim2){
        String status = "NOT APPLICABLE";
        Boolean input1 = verificaBoletimApplicable(idChassi,boletim1);
        Boolean input2 = verificaBoletimApplicable(idChassi,boletim2);
        if(input1 || input2){
            status = "APPLICABLE";
        }
        return status;
    }
}
