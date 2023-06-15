package com.carcaratec.embraer.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/notification")
@RestController
public class NotificacaoController {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @GetMapping
    public List<Notificacoes> listarNotif(){
        List<Notificacoes> list = notificacaoRepository.findAll();
        return list;
    }

    @PostMapping
    public void insertNotif(@RequestBody Notificacoes notificacoes){
        notificacaoRepository.save(notificacoes);
    }
}
