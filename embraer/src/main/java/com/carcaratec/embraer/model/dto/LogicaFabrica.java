package com.carcaratec.embraer.model.dto;

import com.carcaratec.embraer.model.record.DadosCadastroLogicaFabrica;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "LOGICA_FABRICA")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LogicaFabrica {
    @Id
    @Column(name = "ID_LOGICA")
    private Integer idLogica;

    @Column(name = "ID_ITEM")
    private Integer idItem;

    @Column(name = "CHASSI_MINIMO")
    private Integer chassiMinimo;

    public LogicaFabrica(DadosCadastroLogicaFabrica dados){
        this.idLogica = dados.idLogica();
        this.idItem = dados.idItem();
        this.chassiMinimo = dados.chassiMinimo();
    }
}
