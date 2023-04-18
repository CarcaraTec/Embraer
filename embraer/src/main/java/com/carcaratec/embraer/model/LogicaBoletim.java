package com.carcaratec.embraer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "LOGICA_BOLETIM")
public class LogicaBoletim {

    @Column(name = "ID_LOGICA", nullable = false)
    private Integer idLogica;

    @Column(name = "INPUT1")
    private String input1;

    @Column(name = "INPUT2")
    private String input2;

    @Column(name = "OPERACAO")
    private String operacao;

    @Column(name = "DEPENDENCIA")
    private String dependencia;

    @Id
    @Column(name = "ID_ITEM")
    private Integer idItem;

    public Integer getIdLogica() {
        return idLogica;
    }

    public void setIdLogica(Integer idLogica) {
        this.idLogica = idLogica;
    }

    public String getInput1() {
        return input1;
    }

    public void setInput1(String input1) {
        this.input1 = input1;
    }

    public String getInput2() {
        return input2;
    }

    public void setInput2(String input2) {
        this.input2 = input2;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }
}
