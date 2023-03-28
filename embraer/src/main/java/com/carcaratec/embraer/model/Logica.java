package com.carcaratec.embraer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "LOGICA")
public class Logica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_LOGICA", nullable = false)
    private Integer idLogica;

    @Column(name = "ID_ITEM")
    private Integer idItem;

    @Column(name = "CONDICAO")
    private String condicao;

    public Integer getIdLogica() {
        return idLogica;
    }

    public void setIdLogica(Integer idLogica) {
        this.idLogica = idLogica;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }
}
