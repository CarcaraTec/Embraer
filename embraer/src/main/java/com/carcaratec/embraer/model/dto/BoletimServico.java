package com.carcaratec.embraer.model.dto;

import com.carcaratec.embraer.model.record.DadosCadastroBoletim;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "BOLETIM_SERVICO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoletimServico {

    @Id
    @Column(name = "id_boletim", nullable = false, length = 50)
    private String idBoletim;

    @Column(name = "DESCRICAO")
    private String descricao;

    public BoletimServico(DadosCadastroBoletim dados){
        this.idBoletim = dados.idBoletim();
        this.descricao = dados.descricao();
    }
}
