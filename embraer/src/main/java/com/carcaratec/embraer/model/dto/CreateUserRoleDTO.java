package com.carcaratec.embraer.model.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class CreateUserRoleDTO {

    private UUID idUser;

    private List<UUID> idsRoles;

}