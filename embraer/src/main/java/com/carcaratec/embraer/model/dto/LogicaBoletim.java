package com.carcaratec.embraer.model.dto;

import com.carcaratec.embraer.model.record.DadosCadastroLogicaBoletim;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "LOGICA_BOLETIM")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class LogicaBoletim {

    @Id
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

    @Column(name = "ID_ITEM")
    private Integer idItem;

    @Column(name = "MODIFICADO_POR")
    private UUID modificadoPor;

    public LogicaBoletim(DadosCadastroLogicaBoletim dados){
        this.idLogica = dados.idLogica();
        this.input1 = dados.input1();
        this.input2 = dados.input2();
        this.operacao = dados.operacao();
        this.dependencia = dados.dependencia();
    }

    public void atualizarLogica(LogicaBoletim logicaBoletim){
        if (!(logicaBoletim.getInput1() == null) && !(logicaBoletim.getInput1().equals(""))) {
            this.input1 = logicaBoletim.getInput1();
        }
        if (!(logicaBoletim.getInput2() == null) && !(logicaBoletim.getInput2().equals(""))) {
            this.input2 = logicaBoletim.getInput2();
        }
        if (!(logicaBoletim.getOperacao() == null) && !(logicaBoletim.getOperacao().equals(""))) {
            this.operacao = logicaBoletim.getOperacao();
        }
        if (!(logicaBoletim.getDependencia() == null) && !(logicaBoletim.getDependencia().equals(""))) {
            this.dependencia = logicaBoletim.getDependencia();
        }
        if (!(logicaBoletim.getModificadoPor() == null) && !(logicaBoletim.getModificadoPor().equals(""))) {
            this.modificadoPor = logicaBoletim.getModificadoPor();
        }
    }
}
