package com.carcaratec.embraer.statistics.model;

import lombok.*;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "v_qtd_boletim_status_por_chassi")
@Getter
public class StatisticsBoletimPorChassi {

    @Id
    @Column(name = "ID_CHASSI")
    private Integer idChassi;

    @Column(name = "QUANTIDADE_TOTAL")
    private Integer qtdTotal;

    @Column(name = "QUANTIDADE_APPLICABLE")
    private Integer qtdApplicable;

    @Column(name = "QUANTIDADE_INCORPORATED")
    private Integer qtdIncorporated;

}
