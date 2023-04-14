package com.capstone.ecommerce.e_commerce.utils;

import com.capstone.ecommerce.e_commerce.models.Carrello;
import com.capstone.ecommerce.e_commerce.models.Prodotto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ElementoDelCarrello {
	 private Long id;
	    private Integer quantity;
	    private Prodotto prodotto;

	    public ElementoDelCarrello(Carrello carrello) {
	        this.id = carrello.getIdCarrello();
	        this.quantity = carrello.getQuantity();
	        this.setProdotto(carrello.getProdotto());
	    }
}
