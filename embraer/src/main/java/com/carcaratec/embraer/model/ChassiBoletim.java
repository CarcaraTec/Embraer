package com.carcaratec.embraer.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "CHASSI_BOLETIM")
public class ChassiBoletim {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_CHASSI", nullable = false)
    private Integer idChassi;

    @Column(name = "ID_BOLETIM")
    private String idBoletim;

    @Column(name = "STATUS")
    private String status;
}
