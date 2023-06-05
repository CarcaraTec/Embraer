package com.carcaratec.embraer.statistics;

import com.carcaratec.embraer.model.record.DadosCadastroItemReturn;
import com.carcaratec.embraer.repository.ChassiRepository;
import com.carcaratec.embraer.service.LogicControl;
import com.carcaratec.embraer.statistics.model.ApplicableItems;
import com.carcaratec.embraer.statistics.model.QtdItensIntalados;
import com.carcaratec.embraer.statistics.model.StatisticsBoletimPorChassi;
import com.carcaratec.embraer.statistics.model.UtilizacoesBoletins;
import com.carcaratec.embraer.statistics.repository.StatisticsBoletimPorChassiRepository;
import com.carcaratec.embraer.statistics.repository.UtilizacoesBoletinsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "statistics")
public class StatisticsController {

    @Autowired
    private StatisticsBoletimPorChassiRepository statisticsBoletimPorChassiRepository;

    @Autowired
    private UtilizacoesBoletinsRepository utilizacoesBoletinsRepository;

    @Autowired
    private LogicControl logicControl;

    @Autowired
    private ChassiRepository chassiRepository;

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/statusBoletim")
    public List<StatisticsBoletimPorChassi> listStatusBoletins (@RequestParam("idChassi") Integer idChassi){
        List<StatisticsBoletimPorChassi> statusBoletins = statisticsBoletimPorChassiRepository.findByIdChassi(idChassi);
        return statusBoletins;
    }

    @GetMapping("/utilizacoesBoletim")
    public List<UtilizacoesBoletins> listUtilizacoes (@RequestParam("idBoletim") String idBoletim){
        List<UtilizacoesBoletins> utilizacoesBoletins = utilizacoesBoletinsRepository.findByIdBoletim(idBoletim);
        return utilizacoesBoletins;
    }

    @GetMapping("/qtdInstalados/{id}")
    public QtdItensIntalados listQtdInstalados (@PathVariable("id") Integer idItem) {
        QtdItensIntalados qtditems = statisticsService.verificaItensIntalados(idItem);
        return qtditems;
    }

    @GetMapping("/applicable")
    public List<DadosCadastroItemReturn> listApplicable (@RequestParam("idChassi") Integer idChassi){
        List<DadosCadastroItemReturn> list = statisticsService.VerificaItemsApplicable(idChassi);
        return list;
    }

    @GetMapping("/installed")
    public List<DadosCadastroItemReturn> listInstalled (@RequestParam("idChassi") Integer idChassi){
        List<DadosCadastroItemReturn> list = statisticsService.VerificaItemsInstalados(idChassi);
        return list;
    }

    @GetMapping("/installedAndApplicable")
    public List<DadosCadastroItemReturn> listInstalledApplicable (@RequestParam("idChassi") Integer idChassi){
        List<DadosCadastroItemReturn> list = statisticsService.InstalledAndApplicable(idChassi);
        return list;
    }
}
