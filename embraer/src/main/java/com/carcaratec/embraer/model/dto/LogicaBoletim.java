package com.carcaratec.embraer.model.dto;

import com.carcaratec.embraer.model.record.DadosCadastroLogicaBoletim;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "LOGICA_BOLETIM")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    public LogicaBoletim(DadosCadastroLogicaBoletim dados){
        this.idLogica = dados.idLogica();
        this.input1 = dados.input1();
        this.input2 = dados.input2();
        this.operacao = dados.operacao();
        this.dependencia = dados.dependencia();
    }
}
