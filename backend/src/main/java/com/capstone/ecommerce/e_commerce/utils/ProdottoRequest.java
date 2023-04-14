package com.capstone.ecommerce.e_commerce.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ProdottoRequest {
	 private String nome;
	    private double prezzo;
	    private String immagineUrl;
	    private String descrizione;
	    private String categoria;
	    private String disponibilita;
}
