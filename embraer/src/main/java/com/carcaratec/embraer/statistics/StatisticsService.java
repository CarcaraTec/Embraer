package com.carcaratec.embraer.statistics;

import com.carcaratec.embraer.model.dto.Chassi;
import com.carcaratec.embraer.model.dto.Hierarquia;
import com.carcaratec.embraer.model.dto.Item;
import com.carcaratec.embraer.model.record.DadosCadastroItemReturn;
import com.carcaratec.embraer.repository.*;
import com.carcaratec.embraer.service.VerificacaoHierarquia;
import com.carcaratec.embraer.statistics.model.ApplicableItems;
import com.carcaratec.embraer.statistics.model.QtdItensIntalados;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService extends VerificacaoHierarquia {
    @Autowired
    private ChassiRepository chassiRepository;

    @Autowired
    private BoletimServicoRepository boletimServicoRepository;

    @Autowired
    private ChassiBoletimRepository chassiBoletimRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LogicaBoletimRepository logicaBoletimRepository;

    @Autowired
    private LogicaFabricaRepository logicaFabricaRepository;

    @Autowired
    private HierarquiaRepository hierarquiaRepository;

    List<Item> listItem = new ArrayList<>();

    List<DadosCadastroItemReturn> listItemReturn = new ArrayList<>();
    List<ApplicableItems> listApplicableItems = new ArrayList<>();

    JSONObject itemFabrica = new JSONObject();
    List<Hierarquia> listHierarquia = new ArrayList<>();
    Integer idLogica;
    Integer dependencia;
    Integer idItem;
    Integer idChassi;
    String nomeItem;
    Boolean produto = false;
    String status;
    String boletim1 = "";

    public QtdItensIntalados verificaItensIntalados(Integer idItem) {
        Integer installed = 0;
        Integer notInstalled = 0;
        QtdItensIntalados qtdItensIntalados;
        List<Chassi> chassis = chassiRepository.findAll();
        for (Chassi chassi : chassis) {
            DadosCadastroItemReturn cadastroItemReturn;
            String fabrica = String.valueOf(logicaFabricaRepository.findItemFactory(chassi.getIdChassi(), idItem));
            String status = "";
            if (!fabrica.equals("null")) {
                status = fabrica.equals("1") ? "✔" : "❌";
                if (status.equals("✔")) {
                    installed++;
                } else if (status.equals("❌")) {
                    notInstalled++;
                }
            } else {
                List<Hierarquia> logica = new ArrayList<>();
                boolean produto = false;
                Integer idLogica = hierarquiaRepository.findByIdItemOrderByNivelDesc(idItem).get(0).getIdLogica();

                while (true) {
                    logica = hierarquiaRepository.findByIdLogica(idLogica);
                    Integer dependencia = logica.get(0).getDependencia();
                    String boletim1 = produto ? logica.get(0).getInput2() : logica.get(0).getInput1();
                    String boletim2 = logica.get(0).getInput2();
                    String operador = logica.get(0).getOperacao().replaceAll(" ", "");

                    if (dependencia != null && !produto) {
                        logica = hierarquiaRepository.findByIdLogica(dependencia);
                        produto = true;
                    } else {
                        produto = false;
                    }

                    switch (operador) {
                        case "AND":
                            status = estaInstaladoAnd(chassi.getIdChassi(), boletim1, boletim2);
                            break;
                        case "OR":
                            status = estaInstaladoOr(chassi.getIdChassi(), boletim1, boletim2);
                            break;
                    }

                    nomeItem = logica.get(0).getNome();

                    if (!produto) {
                        break;
                    }
                }
                if (status.equals("✔")) {
                    installed++;
                } else if (status.equals("❌")) {
                    notInstalled++;
                }
            }
            cadastroItemReturn = new DadosCadastroItemReturn(idItem, nomeItem, status);
            listItemReturn.add(cadastroItemReturn);
            itemFabrica.put(String.valueOf(idItem), fabrica);
        }
        return new QtdItensIntalados(idItem, nomeItem, installed, notInstalled);
    }

    public List<DadosCadastroItemReturn> VerificaItemsApplicable(Integer idChassi) {
        List<DadosCadastroItemReturn> listItemReturn = new ArrayList<>();
        List<Item> listItem = itemRepository.findAll();
        for (Item item : listItem) {
            DadosCadastroItemReturn cadastroItemReturn;
            String fabrica = String.valueOf(logicaFabricaRepository.findItemFactory(idChassi, item.getIdItem()));
            String status = "";
            if (!fabrica.equals("null")) {
                status = ("Installed");
            } else {
                List<Hierarquia> logica = new ArrayList<>();
                boolean produto = false;
                Integer idDoItem = Integer.valueOf(item.getIdItem());
                logica = hierarquiaRepository.findByIdItemOrderByNivelDesc(idDoItem);
                Integer idLogica = logica.get(0).getIdLogica();

                while (true) {
                    logica = hierarquiaRepository.findByIdLogica(idLogica);
                    Integer dependencia = logica.get(0).getDependencia();
                    String boletim1 = produto ? logica.get(0).getInput2() : logica.get(0).getInput1();
                    String boletim2 = logica.get(0).getInput2();
                    String operador = logica.get(0).getOperacao().replaceAll(" ", "");

                    if (dependencia != null && !produto) {
                        logica = hierarquiaRepository.findByIdLogica(dependencia);
                        produto = true;
                    } else {
                        produto = false;
                    }

                    switch (operador) {
                        case "AND":
                            status = isApplicableAnd(idChassi, boletim1, boletim2);
                            break;
                        case "OR":
                            status = isApplicableOr(idChassi, boletim1, boletim2);
                            break;
                    }
                    if (!produto) {
                        break;
                    }
                }
                cadastroItemReturn = new DadosCadastroItemReturn(item.getIdItem(), item.getNome(), status);
                if(status.equals("APPLICABLE")) {
                    listItemReturn.add(cadastroItemReturn);
                }
            }
        }
        return listItemReturn;
    }


    public List<DadosCadastroItemReturn> VerificaItemsInstalados(Integer idChassi) {
        List<DadosCadastroItemReturn> listItemReturn = new ArrayList<>();
        List<Item> listItem = itemRepository.findAll();
        for (Item item : listItem) {
            DadosCadastroItemReturn cadastroItemReturn;
            String fabrica = String.valueOf(logicaFabricaRepository.findItemFactory(idChassi, item.getIdItem()));
            String status = "";
            if (!fabrica.equals("null")) {
                status = fabrica.equals("1") ? "✔" : "❌";
            } else {
                List<Hierarquia> logica = new ArrayList<>();
                boolean produto = false;
                Integer idLogica = hierarquiaRepository.findByIdItemOrderByNivelDesc(item.getIdItem()).get(0).getIdLogica();

                while (true) {
                    logica = hierarquiaRepository.findByIdLogica(idLogica);
                    Integer dependencia = logica.get(0).getDependencia();
                    String boletim1 = produto ? logica.get(0).getInput2() : logica.get(0).getInput1();
                    String boletim2 = logica.get(0).getInput2();
                    String operador = logica.get(0).getOperacao().replaceAll(" ", "");

                    if (dependencia != null && !produto) {
                        logica = hierarquiaRepository.findByIdLogica(dependencia);
                        produto = true;
                    } else {
                        produto = false;
                    }

                    switch (operador) {
                        case "AND":
                            status = estaInstaladoAnd(idChassi, boletim1, boletim2);
                            break;
                        case "OR":
                            status = estaInstaladoOr(idChassi, boletim1, boletim2);
                            break;
                    }
                    if (!produto) {
                        break;
                    }
                }
            }
            cadastroItemReturn = new DadosCadastroItemReturn(item.getIdItem(), item.getNome(), status);
            if(status.equals("✔")) {
                listItemReturn.add(cadastroItemReturn);
            }
            itemFabrica.put(String.valueOf(item.getIdItem()), fabrica);
        }

        return listItemReturn;
    }

    public List<DadosCadastroItemReturn> InstalledAndApplicable(Integer idChassi) {
        List<DadosCadastroItemReturn> listaFinal = new ArrayList<>();

        List<DadosCadastroItemReturn> listaApplicable = VerificaItemsApplicable(idChassi);
        List<DadosCadastroItemReturn> listaInstalados = VerificaItemsInstalados(idChassi);

        listaFinal.addAll(listaApplicable);
        listaFinal.addAll(listaInstalados);

        return listaFinal;
    }

}

