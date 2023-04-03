package com.carcaratec.embraer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LOGICA_FABRICA")
public class LogicaFabrica {
    @Id
    @Column(name = "ID_LOGICA")
    private Integer idLogica;

    @Column(name = "ID_ITEM")
    private Integer idItem;

    @Column(name = "CHASSI_MINIMO")
    private Integer chassiMinimo;

    public Integer getIdLogica() {
        return idLogica;
    }

    public void setIdLogica(Integer idLogica) {
        this.idLogica = idLogica;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getChassiMinimo() {
        return chassiMinimo;
    }

    public void setChassiMinimo(Integer chassiMinimo) {
        this.chassiMinimo = chassiMinimo;
    }
}
