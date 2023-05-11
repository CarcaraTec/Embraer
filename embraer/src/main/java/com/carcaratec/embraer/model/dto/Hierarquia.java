package com.carcaratec.embraer.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

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
