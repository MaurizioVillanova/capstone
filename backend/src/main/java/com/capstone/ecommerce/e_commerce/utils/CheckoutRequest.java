package com.capstone.ecommerce.e_commerce.utils;

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
public class CheckoutRequest {
	 private String productName;
	    private int quantity;
	    private double price;
	    private int productId;
	    private int userId;

}
