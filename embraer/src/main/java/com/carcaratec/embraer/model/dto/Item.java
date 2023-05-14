package com.carcaratec.embraer.model.dto;

import com.carcaratec.embraer.model.record.DadosCadastroItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ITEM")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {
    @Id
    @Column(name = "ID_ITEM", nullable = false)
    private Integer idItem;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CATEGORIA")
    private String categoria;

    public Item(DadosCadastroItem dados){
        this.idItem = dados.idItem();
        this.nome = dados.nome();
        this.categoria = dados.categoria();
    }
}
