package com.carcaratec.embraer.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="NOTIFICACOES")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notificacoes {

    @Id
    private Integer idNotificacao;

    private UUID usuarioLogado;

    private String mensagem;

    private Boolean lido;
}
