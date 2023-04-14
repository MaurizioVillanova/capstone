package com.capstone.ecommerce.e_commerce.exceptions;

public class PreferitoNotFoundException extends RuntimeException {

private static final long serialVersionUID = 1L;

public  PreferitoNotFoundException(Long id) {
	super("Preferito con "+ id +" non trovato!");
}
	
}
