package com.capstone.ecommerce.e_commerce.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "idProdotto")*/
@Entity
@Table(name = "prodotti")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProdotto;

    private String nome;
    private double prezzo;
    private String immagineUrl;
    private String descrizione;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    private Disponibilità disponibilita;

   // @OneToMany(mappedBy = "prodotto" , cascade = CascadeType.ALL)
   // @OneToMany( cascade = CascadeType.ALL)
    //@Column(name="carrello_id")
  //  @JoinColumn(name="carrello_id")
   // @JsonBackReference
    //@ToString.Exclude
   // private List<Carrello> ordine;

   // @OneToMany(mappedBy = "prodotto" , cascade = CascadeType.ALL)
    //@OneToMany(  cascade = CascadeType.ALL)
   // @Column(name="preferito_id")
   // @JoinColumn(name="preferito_id")
  //  @JsonBackReference
   // @ToString.Exclude
  //  private List<Preferito> preferito;

}
