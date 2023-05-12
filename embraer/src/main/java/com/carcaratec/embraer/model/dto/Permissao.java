package com.carcaratec.embraer.model.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "PERMISSAO")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_PERMISSAO", nullable = false)
    private Integer idPermissao;

    @Column(name = "PERMISSAO")
    private String permissao;
}
