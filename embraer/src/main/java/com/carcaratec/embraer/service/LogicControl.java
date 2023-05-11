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


    Integer idItem;

    String nomeItem;

    String status;

    public List<DadosCadastroItemReturn> itemsDeal(Integer idChassi, String category){
        List<Integer> caminhoHierarquia = new ArrayList<>();

        listItem = itemRepository.findAll();
        //Item é de fabrica
        for(int i = 0;i< listItem.size();i++){
            List<Hierarquia> logica = new ArrayList<>();
            DadosCadastroItemReturn cadastroItemReturn;
            String fabrica = String.valueOf(logicaFabricaRepository.findItemFactory(idChassi,listItem.get(i).getIdItem()));

            idItem = listItem.get(i).getIdItem();

            nomeItem = listItem.get(i).getNome();

            //Caso seja
            if(!fabrica.equals("null")){
                switch (fabrica){
                    case "1":status = "✔";
                        break;
                    case "0":status = "❌";
                        break;
                    default:status = "";
                }
                cadastroItemReturn = new DadosCadastroItemReturn(idItem,nomeItem,status);
                listItemReturn.add(cadastroItemReturn);
                //Caso não seja
            }else{
                //Procura a logica
                logica = hierarquiaRepository.findByIdItem(idItem);
                String boletim1 = logica.get(0).getInput1();
                String boletim2 = logica.get(0).getInput2();
                String operador = logica.get(0).getOperacao();

                //Popula a lista do caminho hieraquico
                caminhoHierarquia = caminhoHierarquia(logica.get(0).getCaminhoHierarquia());
                //Caso possua só um parametro
                if(caminhoHierarquia.size()==1){
                    switch (operador){
                        case "AND":{
                            if(verificaBoletim(idChassi, boletim1)){
                                if(verificaBoletim(idChassi, boletim2));
                            }
                        }
                            break;
                        case "OR":{

                        }
                            break;
                    }
                }


            }
            itemFabrica.put(String.valueOf(listItem.get(i).getIdItem()),fabrica);
        }

        System.out.println(itemFabrica);
        return listItemReturn;
    }


}
