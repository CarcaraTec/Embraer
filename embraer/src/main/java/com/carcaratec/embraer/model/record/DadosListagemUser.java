package com.carcaratec.embraer.model.record;

import com.carcaratec.embraer.model.dto.Role;
import com.carcaratec.embraer.model.dto.User;

import java.util.List;
import java.util.UUID;

public record DadosListagemUser(UUID id, String name, List<Role> roles) {


    public DadosListagemUser(User user) {
        this(user.getId(), user.getName(),user.getRoles());
    }
}
