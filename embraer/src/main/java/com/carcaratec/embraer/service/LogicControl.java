package com.carcaratec.embraer.service;

import com.carcaratec.embraer.model.*;
import com.carcaratec.embraer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class LogicControl {
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

    public List<ItemReturn> itemsDeal(Integer idChassi){
        List<ItemReturn> listItemReturn = new ArrayList<>();

        List<Item> listItem = itemRepository.findAll();

        for (Item item : listItem) {
            ItemReturn itemReturn = new ItemReturn();

            List<LogicaBoletim> listLogicaBoletim = logicaBoletimRepository.findByItem(item.getIdItem());

            if(listLogicaBoletim.isEmpty()){

                List<LogicaFabrica> listFabrica = logicaFabricaRepository.findByIdItem(item.getIdItem());
                itemReturn.setIdItem(item.getIdItem());
                itemReturn.setNome(item.getNome());
                if(!listFabrica.isEmpty()){
                    Integer chassiMinimo = listFabrica.get(0).getChassiMinimo();
                    if(chassiMinimo<=idChassi){
                        itemReturn.setStatus("✔");
                    }else {
                        itemReturn.setStatus("❌");
                    }
                }else {
                    itemReturn.setStatus("❌");
                }
                listItemReturn.add(itemReturn);
            }

            for (LogicaBoletim logicaBoletim : listLogicaBoletim) {



                Integer idItem = item.getIdItem();
                String nomeItem = item.getNome();
                String statusItem1 = "null";
                String statusItem2 = "null";

                List<ChassiBoletim> listChassiBoletim1 = chassiBoletimRepository.findBoletimByIdAndChassi(logicaBoletim.getInput1(), idChassi);
                boolean notEmptyBoletim1 = !listChassiBoletim1.isEmpty();

                String operacao = logicaBoletim.getOperacao();

                List<ChassiBoletim> listChassiBoletim2 = chassiBoletimRepository.findBoletimByIdAndChassi(logicaBoletim.getInput2(), idChassi);
                boolean notEmptyBoletim2 = !listChassiBoletim2.isEmpty();

                if(logicaBoletim.getInput2()==null){
                    operacao = "OR";
                }

                if (notEmptyBoletim1) {
                    if (listChassiBoletim1.get(0).getStatus().equals("INCORPORATED")) {
                        statusItem1 = "INCORPORATED";
                    } else {
                        statusItem1 = "APPLICABLE";
                    }
                }
                if (notEmptyBoletim2) {
                    if (listChassiBoletim2.get(0).getStatus().equals("INCORPORATED")) {
                        statusItem2 = "INCORPORATED";
                    } else {
                        statusItem2 = "APPLICABLE";
                    }
                }
                itemReturn.setIdItem(idItem);
                itemReturn.setNome(nomeItem);

                if(operacao.contains("OR")) {
                    if(notEmptyBoletim1 || notEmptyBoletim2) {
                        if (statusItem1.contains("INCORPORATED") || statusItem2.contains("INCORPORATED")) {
                            itemReturn.setStatus("✔");
                        } else {
                            itemReturn.setStatus("❌");
                        }
                    }else {
                        itemReturn.setStatus("❌");
                    }
                }else if(operacao.contains("AND")){
                    if(notEmptyBoletim1 && notEmptyBoletim2) {
                        if (statusItem1.contains("INCORPORATED") && statusItem2.contains("INCORPORATED")) {
                            itemReturn.setStatus("✔");
                        } else {
                            itemReturn.setStatus("❌");
                        }
                    }else{
                        itemReturn.setStatus("❌");
                    }
                }
                if(listItemReturn.contains(itemReturn)){

                }else {
                listItemReturn.add(itemReturn);
            }}
        }
        return listItemReturn;
    }
}
