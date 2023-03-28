package com.carcaratec.embraer.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BOLTEST")
public class Chassi {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_ITEM", nullable = false)
    private Integer idItem;

    @Column(name = "ID_BOLETIM")
    private String idBoletim;


//
//    @Column(name = "DATA_REGISTRO")
//    private LocalDateTime dataRegistro;


    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getIdBoletim() {
        return idBoletim;
    }

    public void setIdBoletim(String idBoletim) {
        this.idBoletim = idBoletim;
    }
}
