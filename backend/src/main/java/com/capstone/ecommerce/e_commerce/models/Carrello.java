package com.capstone.ecommerce.e_commerce.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "idCarrello")
@Entity
@Table(name = "ordini")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class Carrello {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	    private Long idCarrello;

	    private Date createdDate;

	    //@JsonBackReference
	    @ManyToOne
	    @JoinColumn(name = "prodotto_id", nullable=false)
	    private Prodotto prodotto ;

	   // @JsonBackReference
	    @ManyToOne
	    @JoinColumn(name = "user_id", nullable=false)
	 	    private User user;

	    private int quantity;
	    
	    
}
