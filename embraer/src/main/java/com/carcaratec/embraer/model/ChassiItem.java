package com.carcaratec.embraer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CHASSI_ITEM")
public class ChassiItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_CHASSI", nullable = false)
    private Integer idChassi;

    @Column(name = "ID_ITEM")
    private Integer idItem;

    public Integer getIdChassi() {
        return idChassi;
    }

    public void setIdChassi(Integer idChassi) {
        this.idChassi = idChassi;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }
}
