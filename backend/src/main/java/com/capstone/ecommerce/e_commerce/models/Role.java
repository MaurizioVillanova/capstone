package com.capstone.ecommerce.e_commerce.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long idRole;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public Role( RoleType roleType ) {

        this.roleType = roleType;

    }

}
