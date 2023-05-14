package com.carcaratec.embraer.model.record;

import com.carcaratec.embraer.model.dto.Chassi;

public record DadosListagemChassi(
        Integer idChassi
//        ,
//        String proprietario
) {
    public DadosListagemChassi (Chassi chassi){
        this(chassi.getIdChassi()
//                , chassi.getProprietario()
        );
    }
}
