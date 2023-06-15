package com.carcaratec.embraer.controller;

import com.carcaratec.embraer.model.ChassiBoletimPK;
import com.carcaratec.embraer.model.dto.ChassiBoletim;
import com.carcaratec.embraer.model.dto.Hierarquia;
import com.carcaratec.embraer.model.dto.Item;
import com.carcaratec.embraer.model.dto.LogicaBoletim;
import com.carcaratec.embraer.model.record.DadosCadastroItemReturn;
import com.carcaratec.embraer.repository.*;
import com.carcaratec.embraer.service.LogicControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LogicControl logicControl;

    @Autowired
    private LogicaBoletimRepository logicaBoletimRepository;

    @Autowired
    private LogicaFabricaRepository logicaFabricaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HierarquiaRepository hierarquiaRepository;

    @GetMapping
    public List<Item> listarItems() {
        List<Item> lista = itemRepository.findAll();
        return lista;
    }

    @GetMapping("/category")
    public List<String> category() {
        List<String> category = itemRepository.findCategory();
        List<String> categoryFormated = new ArrayList<>();
        for (int i = 0; i < category.size(); i++) {
            categoryFormated.add(category.get(i).toString().toUpperCase());
        }
        return categoryFormated;
    }

    @GetMapping("/findByCategory")
    public List<Item> findByCategory(@RequestParam("category") String category) {
        List<Item> findByCategoria = itemRepository.findByCategoria(category);
        return findByCategoria;
    }

    @GetMapping("/logica")
    public List<DadosCadastroItemReturn> logica(@RequestParam("idChassi") Integer idChassi) {
        List<DadosCadastroItemReturn> listItemReturn = logicControl.itemsDeal(idChassi, "all");
        return listItemReturn;
    }

    @GetMapping("/logicaByCategory")
    public List<DadosCadastroItemReturn> logicaCategory(@RequestParam("idChassi") Integer idChassi, @RequestParam("category") String category) {
        List<DadosCadastroItemReturn> listItemReturn = logicControl.itemsDeal(idChassi, category.toLowerCase());
        return listItemReturn;
    }

    @GetMapping("/hierarquia")
    public List<Hierarquia> findHierarquia() {
        List<Hierarquia> listHierarquia = hierarquiaRepository.findAll();
        return listHierarquia;
    }

    @GetMapping("/findFactory")
    public Integer find(@RequestParam("chassi") Integer chassi,@RequestParam("idItem") Integer idItem){
        return logicaFabricaRepository.findItemFactory(chassi,idItem);
    }

    @Transactional
    @PutMapping("alterarLogica")
    public void atualizar (@RequestBody LogicaBoletim logicaBoletim, @AuthenticationPrincipal UserDetails userDetails){
        var logica = logicaBoletimRepository.getReferenceById(logicaBoletim.getIdLogica());
        logica.setModificadoPor(usuarioRepository.findByUsername(userDetails.getUsername()).getId());
        logica.atualizarLogica(logicaBoletim);
    }

    @GetMapping("/hierarquiaByIdItem/{id}")
    public List<Hierarquia> findHierarquiaByItem(@PathVariable("id") Integer id) {
        List<Hierarquia> listHierarquia = hierarquiaRepository.findByIdItem(id);
        return listHierarquia;
    }


}
