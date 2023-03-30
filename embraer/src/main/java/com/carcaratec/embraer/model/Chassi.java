package com.carcaratec.embraer.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CHASSI")
public class Chassi {
    @Id
    @Column(name = "ID_CHASSI", nullable = false)
    private Integer idChassi;

    public Integer getIdChassi() {
        return idChassi;
    }

    public void setIdChassi(Integer idChassi) {
        this.idChassi = idChassi;
    }
}
