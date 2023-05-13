package com.carcaratec.embraer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ChassiBoletimPK implements Serializable {

    private Integer idChassi;
    private String idBoletim;
}
