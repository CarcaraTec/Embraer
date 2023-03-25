package com.carcaratec.embraer.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CHASSI")
public class Chassi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CHASSI")
    private Integer id;

    @Column(name = "DATA_REGISTRO")
    private LocalDateTime dataRegistro;
}
