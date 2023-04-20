package com.carcaratec.embraer.controller;

import com.carcaratec.embraer.dataImporter.LoadData;
import com.carcaratec.embraer.model.*;
import com.carcaratec.embraer.repository.*;
import com.carcaratec.embraer.service.LogicControl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import oracle.ucp.proxy.annotation.Post;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
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
    private LogicaBoletimRepository logicaBoletimRepository;

    @Autowired
    private LogicaFabricaRepository logicaFabricaRepository;

    @Autowired
    private LogicControl logicControl;


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
        List<ItemReturn> listItemReturn = logicControl.itemsDeal(idChassi,"all");
        return listItemReturn;
    }

    @GetMapping("/logicaByCategory")
    public List<ItemReturn> logicaCategory(@RequestParam("idChassi") Integer idChassi,@RequestParam("category") String category) {
        List<ItemReturn> listItemReturn = logicControl.itemsDeal(idChassi,category.toLowerCase());
        return listItemReturn;
    }

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

    @PostMapping("/saveChassi")
    public String abc (@RequestBody String json0) {
        JSONArray jsonRefact = new JSONArray(json0);
        for (int i = 0; i < jsonRefact.length(); i++) {
            //System.out.println(jsonRefact.get(i));
            JSONObject json = new JSONObject(jsonRefact.get(i).toString());
            Integer idChassi = json.getInt("idChassi");
            String idBoletim = json.get("idBoletim").toString();
            boolean status1 = (boolean) json.get("status1");
            boolean status2 = (boolean) json.get("status2");
            String status = "";
            System.out.println(status2);
            if (status1 == true) {
                status = ("INCORPORATED");
            }
            if (status2 == true) {
                status = ("APPLICABLE");
            }
            System.out.println(idBoletim);
            System.out.println(idChassi);
            System.out.println(status);

            chassiBoletimRepository.updateChassi(status, idChassi, idBoletim);
        }
            return "";
        }

        @GetMapping("/category")
        public List<String> category(){
        List<String>category = itemRepository.findCategory();
        List<String>categoryFormated = new ArrayList<>();
        for(int i = 0;i<category.size();i++){
            categoryFormated.add(category.get(i).toString().toUpperCase());
        }
        return categoryFormated;
        }

        @GetMapping("/findByCategory")
        public List<Item> findByCategory(@RequestParam("category") String category){
            List<Item> findByCategoria = itemRepository.findByCategoria(category);
            return findByCategoria;
        }

        @PostMapping("/loadData")
        public void loadData(@RequestBody String stringJson){

        JSONObject json = new JSONObject(stringJson);


            JSONArray data = new JSONArray(json.get("data").toString());

            System.out.println(data);

            for(int i = 0;i<data.length();i++){
                JSONObject chassiBoletim = new JSONObject(data.get(i));
                System.out.println(data.get(i));
            //    System.out.println(chassiBoletim.get("Status"));
            }
//            System.out.println(data);
            //System.out.println(json.get("data"));
//        JSONArray jsonArray = new JSONArray(json.get("data"));
//            System.out.println(jsonArray);
        }

}
