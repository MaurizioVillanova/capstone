package com.capstone.ecommerce.e_commerce.exceptions;

public class ProdottoNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
 public ProdottoNotFoundException(Long id) {
	 
	 super("Prodotto con "+ id +" non trovato");
	 		
 }
}
