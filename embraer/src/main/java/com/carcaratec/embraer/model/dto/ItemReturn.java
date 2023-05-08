package com.carcaratec.embraer.model.dto;

import com.carcaratec.embraer.model.record.DadosCadastroItemReturn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemReturn {

    private Integer idItem;

    private String nome;

    private String status;

    public ItemReturn(DadosCadastroItemReturn dados){
        this.idItem = dados.idItem();
        this.nome = dados.nome();
        this.status = dados.status();
    }
}
