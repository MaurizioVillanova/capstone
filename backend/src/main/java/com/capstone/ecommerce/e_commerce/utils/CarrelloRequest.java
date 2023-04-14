package com.capstone.ecommerce.e_commerce.utils;

import java.util.List;

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

public class CarrelloRequest {
	 private List<ElementoDelCarrello> cartItems;
	    private double totalCost;
}
