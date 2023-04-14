package com.capstone.ecommerce.e_commerce.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "preferiti")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Preferito {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	    private Long idPreferito;


	    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	    @JoinColumn(name = "user_id",  nullable=false)
	    private User user;


	    @ManyToOne
	    @JoinColumn(name = "id_prodotto",   nullable=false )
	    private Prodotto prodotto;
	  
}
