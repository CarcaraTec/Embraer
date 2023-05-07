package com.carcaratec.embraer.controller;

import com.carcaratec.embraer.model.dto.ChassiBoletim;
import com.carcaratec.embraer.model.record.DadosCadastroChassiBoletim;
import com.carcaratec.embraer.repository.ChassiBoletimRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "chassiBoletim")
public class ChassiBoletimController {

    @Autowired
    private ChassiBoletimRepository chassiBoletimRepository;

    @PostMapping("/insertChassiBoletim")
    public void insertChassiBoletim(@RequestBody DadosCadastroChassiBoletim dados){
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

}
