package com.carcaratec.embraer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ITEM")
public class Item {

    @Id
    @Column(name = "ID_ITEM", nullable = false)
    private Integer idItem;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
