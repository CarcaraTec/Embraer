package com.carcaratec.embraer.statistics.model;

public record QtdItensIntalados(
        Integer idItem,
        String nome,
        Integer qtdInstalled,
        Integer qtdNotInstalled
) {
}
