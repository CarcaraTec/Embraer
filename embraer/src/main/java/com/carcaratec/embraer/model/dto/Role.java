package com.carcaratec.embraer.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public Role(Integer id) {
        this.id = id;
    }

}
