package com.carcaratec.embraer.model.record;

public record DadosCadastroLogicaBoletim(
        Integer idLogica,
        String input1,
        String input2,
        String operacao,
        String dependencia
) {
}
