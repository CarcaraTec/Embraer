package com.carcaratec.embraer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MUDANCAS")
public class Mudancas {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_MUDANCA", nullable = false)
    private Integer idMudanca;

    @Column(name = "TABELA_MODIFICADA")
    private String tabelaModificada;

    @Column(name = "ID_REGISTRO_MODIFICADO")
    private Integer idRegistroModificado;

    @Column(name = "CAMPO_MODIFICADO")
    private String campoModificado;

    @Column(name = "VALOR_ANTERIOR")
    private String valorAnterior;

    @Column(name = "VALOR_NOVO")
    private String valorNovo;

    @Column(name = "ID_USUARIO")
    private Integer idUsuario;

    public Integer getIdMudanca() {
        return idMudanca;
    }

    public void setIdMudanca(Integer idMudanca) {
        this.idMudanca = idMudanca;
    }

    public String getTabelaModificada() {
        return tabelaModificada;
    }

    public void setTabelaModificada(String tabelaModificada) {
        this.tabelaModificada = tabelaModificada;
    }

    public Integer getIdRegistroModificado() {
        return idRegistroModificado;
    }

    public void setIdRegistroModificado(Integer idRegistroModificado) {
        this.idRegistroModificado = idRegistroModificado;
    }

    public String getCampoModificado() {
        return campoModificado;
    }

    public void setCampoModificado(String campoModificado) {
        this.campoModificado = campoModificado;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(String valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public String getValorNovo() {
        return valorNovo;
    }

    public void setValorNovo(String valorNovo) {
        this.valorNovo = valorNovo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
