package com.capstone.ecommerce.e_commerce.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "idUser")
@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String nomeCompleto;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;

    @ManyToMany // PIU UTENTI POSSONO AVERE PIU RUOLI E VICEVERSA
    @JoinTable(name = "user_roles", //Nome della tabella che verrà creata
            joinColumns = @JoinColumn(name = "user_id"),// Crea colonna
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles = new HashSet<Role>();

    private Boolean active = true;

    

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    @Column(name="carrello_id")
    //@JsonBackReference
    @ToString.Exclude
    private List<Carrello> ordine;
    
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    @Column(name="preferito_id")
    //@JsonBackReference
    @ToString.Exclude
    private List<Preferito> preferito;
    public void addRole(Role r) {

        this.roles.add(r);

    }
}

