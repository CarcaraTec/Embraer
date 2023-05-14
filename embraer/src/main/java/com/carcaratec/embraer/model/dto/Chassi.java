package com.carcaratec.embraer.model.dto;

import com.carcaratec.embraer.model.record.DadosCadastroChassi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Entity(name = "chassi")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CHASSI")
@Getter
public class Chassi{

    @Id
    @Column(name = "ID_CHASSI", nullable = false)
    private Integer idChassi;

    @Column(name = "PROPRIETARIO")
    private UUID proprietario;


    public Chassi(DadosCadastroChassi dados) {
        this.idChassi = dados.idChassi();
//        this.proprietario = dados.proprietario();
    }
}
