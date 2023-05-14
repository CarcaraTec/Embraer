package com.carcaratec.embraer.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "v_hierarquia")
public class Hierarquia {
    @Id
    Integer idLogica;

    Integer idItem;

    String nome;

    String input1;

    String operacao;

    String input2;

    Integer dependencia;

    Integer nivel;

    Integer noRaiz;

    String caminhoHierarquia;
}
