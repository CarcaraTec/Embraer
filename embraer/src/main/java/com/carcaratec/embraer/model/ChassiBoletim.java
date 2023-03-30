package com.carcaratec.embraer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CHASSI_BOLETIM")
public class ChassiBoletim {

    @Id
    @Column(name = "ID_CHASSI", nullable = false)
    private Integer idChassi;

    @Column(name = "ID_BOLETIM")
    private String idBoletim;

    @Column(name = "STATUS")
    private String status;

    public Integer getIdChassi() {
        return idChassi;
    }

    public void setIdChassi(Integer idChassi) {
        this.idChassi = idChassi;
    }

    public String getIdBoletim() {
        return idBoletim;
    }

    public void setIdBoletim(String idBoletim) {
        this.idBoletim = idBoletim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
