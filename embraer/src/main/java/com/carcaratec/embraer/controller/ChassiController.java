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
    public List<Chassi> listarChassi(){
        List<Chassi> lista = chassiRepository.findAll();
        return lista;
    }
    @GetMapping("/items")
    public List<Item> listarItems(){
        List<Item> lista = itemRepository.findAll();
        return lista;
    }



    @PostMapping("/insertChassi")
    public ResponseEntity<?> insertChassi(@RequestBody Chassi chassi){
        chassiRepository.save(chassi);
        return ResponseEntity.ok(chassi);
    }

    @GetMapping("/findBoletim")
    public List<ChassiBoletim> listarItems(@RequestParam("idChassi") Integer idChassi){
        List<ChassiBoletim> lista = chassiBoletimRepository.findBoletimByChassi(idChassi);
        return lista;
    }

    @GetMapping("/logica")
    public List<ItemReturn> logica (@RequestParam ("idChassi") Integer idChassi){
        ItemReturn itemReturn = new ItemReturn();
        List<ItemReturn> listItemReturn = new ArrayList<>();
        List<Item> listItem = itemRepository.findAll();
        for(int i = 0;i<listItem.size();i++){
            List<Logica> listLogica = logicaRepository.findByItem(listItem.get(i).getIdItem());
            for(int x = 0;x<listLogica.size();x++) {
                    List<ChassiBoletim> listChassiBoletim1 = chassiBoletimRepository
                            .findBoletimByIdAndChassi(listLogica.get(x).getInput1(), idChassi);
                if(listLogica.get(x).getOperacao().equals("OR") || listChassiBoletim1 != null) {
                    List<ChassiBoletim> listChassiBoletim2 = chassiBoletimRepository
                            .findBoletimByIdAndChassi(listLogica.get(x).getInput2(), idChassi);

                    if(listChassiBoletim1 != null){
                        if(listChassiBoletim1.get(x).getStatus().equals("INCORPORATED")){

                            itemReturn.setIdItem(listItem.get(i).getIdItem());
                            itemReturn.setNome(listItem.get(i).getNome());
                            itemReturn.setInstalado(true);

                            System.out.println(itemReturn.getIdItem());
                            System.out.println(itemReturn.getNome());
                            listItemReturn.add(itemReturn);

                        }else {
                            itemReturn.setIdItem(listItem.get(i).getIdItem());
                            itemReturn.setNome(listItem.get(i).getNome());
                            itemReturn.setInstalado(false);

                            System.out.println(itemReturn.getIdItem());
                            System.out.println(itemReturn.getNome());
                            listItemReturn.add(itemReturn);
                        }
                        i = 1000;
                        x = 1000;
                    }
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
    public ResponseEntity<?> insertBoletim(@RequestBody BoletimServico boletimServico){
        boletimServicoRepository.save(boletimServico);
        return ResponseEntity.ok(boletimServico);
    }

    @PostMapping("/insertChassiBoletim")
    public ResponseEntity<?> insertChassiBoletim(@RequestBody ChassiBoletim chassiBoletim){
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
        loadData.convert("csv",p);
        String[] path2 = p.split("\\.");

        p = path2[0] + ".csv";

        int lines = loadData.countLine(p);

        for(int i = 1;i<=lines;i++){
            chassiBoletim = loadData.getBloco(i, p).getBody();
            chassi.setIdChassi(chassiBoletim.getIdChassi());
            boletimServico.setIdBoletim(chassiBoletim.getIdBoletim());

            chassiRepository.save(chassi);
            boletimServicoRepository.save(boletimServico);
            chassiBoletimRepository.insertChassiBoletim(chassiBoletim.getIdChassi(),chassiBoletim.getIdBoletim(), chassiBoletim.getStatus());
        }

        return ResponseEntity.ok(chassi);
    }






    public static void main(String[] args) {
        JSONObject a = new JSONObject();

        for(int i = 0;i<a.length();i++){
            System.out.println(a);
        }
    }

}
