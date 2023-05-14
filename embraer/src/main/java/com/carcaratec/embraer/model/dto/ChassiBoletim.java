package com.carcaratec.embraer.model.dto;

import com.carcaratec.embraer.model.ChassiBoletimPK;
import com.carcaratec.embraer.model.record.DadosCadastroChassiBoletim;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "CHASSI_BOLETIM")
@IdClass(ChassiBoletimPK.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
}
