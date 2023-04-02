package com.carcaratec.embraer.controller;

import com.carcaratec.embraer.dataImporter.LoadData;
import com.carcaratec.embraer.model.*;
import com.carcaratec.embraer.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class ChassiController {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ChassiRepository chassiRepository;

    @Autowired
    private BoletimServicoRepository boletimServicoRepository;

    @Autowired
    private ChassiBoletimRepository chassiBoletimRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LogicaRepository logicaRepository;

    @GetMapping("/chassis")
    public List<Chassi> listarChassi() {
        List<Chassi> lista = chassiRepository.findAll();
        return lista;
    }

    @GetMapping("/items")
    public List<Item> listarItems() {
        List<Item> lista = itemRepository.findAll();
        return lista;
    }


    @PostMapping("/insertChassi")
    public ResponseEntity<?> insertChassi(@RequestBody Chassi chassi) {
        chassiRepository.save(chassi);
        return ResponseEntity.ok(chassi);
    }

    @GetMapping("/findBoletim")
    public List<ChassiBoletim> listarItems(@RequestParam("idChassi") Integer idChassi) {
        List<ChassiBoletim> lista = chassiBoletimRepository.findBoletimByChassi(idChassi);
        return lista;
    }

    // Definição da rota para a requisição HTTP GET
    @GetMapping("/logica")
    public List<ItemReturn> logica(@RequestParam("idChassi") Integer idChassi) {
        List<ItemReturn> listItemReturn = new ArrayList<>();

        List<Item> listItem = itemRepository.findAll();

        for (Item item : listItem) {
            List<Logica> listLogica = logicaRepository.findByItem(item.getIdItem());

            for (Logica logica : listLogica) {
                ItemReturn itemReturn = new ItemReturn();

                Integer idItem = item.getIdItem();
                String nomeItem = item.getNome();
                String statusItem1 = "null";
                String statusItem2 = "null";

                List<ChassiBoletim> listChassiBoletim1 = chassiBoletimRepository.findBoletimByIdAndChassi(logica.getInput1(), idChassi);
                boolean notEmptyBoletim1 = !listChassiBoletim1.isEmpty();

                String operacao = logica.getOperacao();

                if (operacao.contains("OR") || notEmptyBoletim1) {
                    List<ChassiBoletim> listChassiBoletim2 = chassiBoletimRepository.findBoletimByIdAndChassi(logica.getInput2(), idChassi);
                    boolean notEmptyBoletim2 = !listChassiBoletim2.isEmpty();

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

                    if (statusItem1.contains("INCORPORATED") || statusItem2.contains("INCORPORATED")) {
                        itemReturn.setStatus("INCORPORATED");
                    } else {
                        itemReturn.setStatus("APPLICABLE");
                    }
                    listItemReturn.add(itemReturn);
                } else {
                    itemReturn.setIdItem(idItem);
                    itemReturn.setNome(nomeItem);
                    itemReturn.setStatus("NOT APPLICABLE");
                    listItemReturn.add(itemReturn);
                }
            }
        }

        return listItemReturn;
    }



//    @GetMapping("/teste")
//    public String teste(@RequestParam ("idChassi") Integer idChassi){
//        List<ChassiBoletim> listChassiBoletim1 = chassiBoletimRepository
//                .findBoletimByIdAndChassi(listLogica.get(x).getInput1(), idChassi);
//        System.out.println(listChassiBoletim1.get(x).getStatus());
//
//        return "";
//    }

    @PostMapping("/insertBoletim")
    public ResponseEntity<?> insertBoletim(@RequestBody BoletimServico boletimServico) {
        boletimServicoRepository.save(boletimServico);
        return ResponseEntity.ok(boletimServico);
    }

    @PostMapping("/insertChassiBoletim")
    public ResponseEntity<?> insertChassiBoletim(@RequestBody ChassiBoletim chassiBoletim) {
        chassiBoletimRepository.save(chassiBoletim);
        return ResponseEntity.ok(chassiBoletim);
    }

    @PostMapping("/insertA")
    public ResponseEntity<?> insertA(@RequestBody Path path) throws IOException, InterruptedException {
        Chassi chassi = new Chassi();
        BoletimServico boletimServico = new BoletimServico();
        ChassiBoletim chassiBoletim = new ChassiBoletim();
        LoadData loadData = new LoadData();
        String p = path.getCaminho();
        System.out.println(path);
        loadData.convert("csv", p);
        String[] path2 = p.split("\\.");

        p = path2[0] + ".csv";

        int lines = loadData.countLine(p);

        for (int i = 1; i <= lines; i++) {
            chassiBoletim = loadData.getBloco(i, p).getBody();
            chassi.setIdChassi(chassiBoletim.getIdChassi());
            boletimServico.setIdBoletim(chassiBoletim.getIdBoletim());

            chassiRepository.save(chassi);
            boletimServicoRepository.save(boletimServico);
            chassiBoletimRepository.insertChassiBoletim(chassiBoletim.getIdChassi(), chassiBoletim.getIdBoletim(), chassiBoletim.getStatus());
        }

        return ResponseEntity.ok(chassi);
    }


    public static void main(String[] args) {
        JSONObject a = new JSONObject();

        for (int i = 0; i < a.length(); i++) {
            System.out.println(a);
        }
    }

}
