package com.carcaratec.embraer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "BOLETIM_SERVICO")
public class BoletimServico {

    @Id
    @Column(name = "id_boletim", nullable = false, length = 50)
    private String idBoletim;

    @Column(name = "DESCRICAO")
    private String descricao;

    public String getIdBoletim() {
        return idBoletim;
    }

    public void setIdBoletim(String idBoletim) {
        this.idBoletim = idBoletim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
