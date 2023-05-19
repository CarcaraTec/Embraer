package com.carcaratec.embraer.controller;

import com.carcaratec.embraer.model.ChassiBoletimPK;
import com.carcaratec.embraer.model.dto.BoletimServico;
import com.carcaratec.embraer.model.dto.Chassi;
import com.carcaratec.embraer.model.dto.ChassiBoletim;
import com.carcaratec.embraer.model.record.DadosCadastroBoletim;
import com.carcaratec.embraer.model.record.DadosCadastroChassi;
import com.carcaratec.embraer.model.record.DadosCadastroChassiBoletim;
import com.carcaratec.embraer.repository.BoletimServicoRepository;
import com.carcaratec.embraer.repository.ChassiBoletimRepository;
import com.carcaratec.embraer.repository.ChassiRepository;
import com.carcaratec.embraer.repository.UsuarioRepository;
import oracle.ucp.proxy.annotation.Post;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "chassiBoletim")
public class ChassiBoletimController {

    @Autowired
    private ChassiBoletimRepository chassiBoletimRepository;

    @Autowired
    private ChassiRepository chassiRepository;

    @Autowired
    private BoletimServicoRepository boletimServicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping("/insertChassiBoletim")
    public void insertChassiBoletim(@RequestBody DadosCadastroChassiBoletim dados) {
        chassiBoletimRepository.save(new ChassiBoletim(dados));
    }

    @GetMapping("/findBoletim")
    public List<ChassiBoletim> findChassiBoletim(@RequestParam("idChassi") Integer idChassi) {
        List<ChassiBoletim> lista = chassiBoletimRepository.findBoletimByChassi(idChassi);
        return lista;
    }

    @PostMapping("/saveChassi")
    public ResponseEntity<?> saveChassi(@RequestBody String jsonBody) {
        JSONArray jsonArray = new JSONArray(jsonBody);

        for (Object obj : jsonArray) {
            JSONObject jsonObj = new JSONObject(obj.toString());
            Integer idChassi = jsonObj.getInt("idChassi");
            String idBoletim = jsonObj.getString("idBoletim");
            boolean status1 = jsonObj.optBoolean("status1", false);
            boolean status2 = jsonObj.optBoolean("status2", false);

            String status = "";
            if (status1) {
                status = "INCORPORATED";
            } else if (status2) {
                status = "APPLICABLE";
            }

            chassiBoletimRepository.updateChassi(status, idChassi, idBoletim);
        }

        return ResponseEntity.ok().build();
    }

    @Transactional
    @PostMapping("atualizar")
    public void atualizar (@RequestBody ChassiBoletim chassiBoletim, @AuthenticationPrincipal UserDetails userDetails){
        ChassiBoletimPK chassiBoletimPK = new ChassiBoletimPK(chassiBoletim.getIdChassi(),chassiBoletim.getIdBoletim());
        var chassi = chassiBoletimRepository.getReferenceById(chassiBoletimPK);
        chassiBoletim.setModificadoPor(usuarioRepository.findByUsername(userDetails.getUsername()).getId());


        chassi.atualizar(chassiBoletim);

    }

    @Transactional
    @PostMapping("/loadData")
    public void loadData(@RequestBody String stringJson) {
        JSONObject json = new JSONObject(stringJson);
        JSONArray data = new JSONArray(json.get("data").toString());
        System.out.println(data);

        String chassiString = "";
        Integer chassi = 0;

        List<BoletimServico> boletimServicoList = new ArrayList<>();
        List<ChassiBoletim> chassiBoletimList = new ArrayList<>();

        for (int i = 0; i < data.length(); i++) {
            JSONObject chassiBoletim0 = new JSONObject(data.get(i).toString());
            if (i == 0) {
                chassiString = chassiBoletim0.get("Chassis").toString().replaceAll("[^0-9]", "");
                chassi = Integer.valueOf(chassiString);
                Chassi chass = new Chassi(new DadosCadastroChassi(chassi));
                chassiRepository.save(chass);
            } else if (i > 0) {
                String boletim = chassiBoletim0.get("Boletim de serviço").toString();
                DadosCadastroBoletim dadosCadastroBoletim = new DadosCadastroBoletim(boletim, "");
                DadosCadastroChassiBoletim dados = new DadosCadastroChassiBoletim(chassi, boletim,
                        chassiBoletim0.get("Status").toString());
                boletimServicoList.add(new BoletimServico(dadosCadastroBoletim));
                chassiBoletimList.add(new ChassiBoletim(dados));
            }
        }
        boletimServicoRepository.saveAll(boletimServicoList);
        chassiBoletimRepository.saveAll(chassiBoletimList);
    }


    @PostMapping("/chassi")
    public void testeChassi (@RequestBody DadosCadastroChassi dados){
        chassiRepository.save(new Chassi(dados));
    }

}
