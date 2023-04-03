package com.carcaratec.embraer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class LogicaFabrica {
    @Id
    @Column(name = "ID_LOGICA")
    private Integer idLogica;

    @Column(name = "ID_ITEM")
    private Integer idItem;

    @Column(name = "CHASSI_MINIMO")
    private Integer chassiMinimo;
}
