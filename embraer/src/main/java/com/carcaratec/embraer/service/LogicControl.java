package com.carcaratec.embraer.service;

import com.carcaratec.embraer.model.dto.*;
import com.carcaratec.embraer.model.record.DadosCadastroItemReturn;
import com.carcaratec.embraer.repository.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogicControl extends VerificacaoHierarquia {
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

    JSONObject itemFabrica = new JSONObject();
    List<Hierarquia> listHierarquia = new ArrayList<>();
    Integer idLogica;
    Integer dependencia;
    Integer idItem;

    String nomeItem;
    Boolean produto = false;
    String status;
    String boletim1 = "";

    public List<DadosCadastroItemReturn> itemsDeal(Integer idChassi, String category) {
        List<DadosCadastroItemReturn> listItemReturn = new ArrayList<>();
        List<Item> listItem = category.equals("all") ? itemRepository.findAll() : itemRepository.findAllByCategoria(category);
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
                    String operador = logica.get(0).getOperacao().replaceAll(" ","");

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
            listItemReturn.add(cadastroItemReturn);
            itemFabrica.put(String.valueOf(item.getIdItem()), fabrica);
        }
        System.out.println("Quantidade de itens total: " + listItem.size());
        System.out.println("Quantidade de itens colocados: " + listItemReturn.size());

        return listItemReturn;
    }
}
