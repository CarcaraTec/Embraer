package com.carcaratec.embraer.statistics.model;

import lombok.*;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "v_qtd_utilizacao_boletim")
@Getter
public class UtilizacoesBoletins {
    @Id
    @Column(name = "ID_BOLETIM")
    private String idBoletim;

    @Column(name = "QUANTIDADE_TOTAL_USOS")
    private Integer qtdTotal;

    @Column(name = "QUANTIDADE_INCORPORATED")
    private Integer qtdIncorporated;

    @Column(name = "QUANTIDADE_APPLICABLE")
    private Integer qtdApplicable;
}
