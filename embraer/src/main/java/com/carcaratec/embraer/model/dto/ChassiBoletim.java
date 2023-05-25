package com.carcaratec.embraer.model.dto;

import com.carcaratec.embraer.model.ChassiBoletimPK;
import com.carcaratec.embraer.model.record.DadosCadastroChassiBoletim;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "CHASSI_BOLETIM")
@IdClass(ChassiBoletimPK.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ChassiBoletim {
    @Id
    @Column(name = "ID_CHASSI", nullable = false)
    private Integer idChassi;

    @Id
    @Column(name = "ID_BOLETIM")
    private String idBoletim;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "MODIFICADO_POR")
    private UUID modificadoPor;

    public ChassiBoletim(DadosCadastroChassiBoletim dados){
        this.idChassi = dados.idChassi();
        this.idBoletim = dados.idBoletim();
        this.status = dados.status();
    }

    public void atualizar (ChassiBoletim chassiBoletim){
        this.idChassi = chassiBoletim.getIdChassi();
        this.idBoletim = chassiBoletim.idBoletim;
        this.status = chassiBoletim.getStatus();
        this.modificadoPor = chassiBoletim.getModificadoPor();
    }
}
