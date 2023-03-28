package com.carcaratec.embraer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "BOLETIM_ITEM")
public class BoletimItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_boletim", nullable = false, length = 50)
    private String idBoletim;

    @Column(name = "ID_ITEM")
    private Integer idItem;

    public String getIdBoletim() {
        return idBoletim;
    }

    public void setIdBoletim(String idBoletim) {
        this.idBoletim = idBoletim;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }
}
