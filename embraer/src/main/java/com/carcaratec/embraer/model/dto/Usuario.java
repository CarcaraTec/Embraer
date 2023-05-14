package com.carcaratec.embraer.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "USUARIO")
public class Usuario {

    @Id
    private String login;

    private String nome;

    private String email;

    private String senha;

    @ManyToMany
    private List<Role> roles;
}
