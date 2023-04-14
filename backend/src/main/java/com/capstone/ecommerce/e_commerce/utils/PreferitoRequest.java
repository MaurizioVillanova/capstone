package com.capstone.ecommerce.e_commerce.utils;

import com.capstone.ecommerce.e_commerce.models.Prodotto;
import com.capstone.ecommerce.e_commerce.models.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.sun.istack.NotNull;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PreferitoRequest {
	  // private  userId;
	    //private prodottoId;
		//public Integer getUserId() {
		//	return userId;
		//}
	//	public Integer getProdottoId() {
			//return prodottoId;
	//	}
	    private User user;
	    private Prodotto prodotto;
	// private @NotNull Integer userId;
	  //  private @NotNull Integer prodottoId;
	   
		
	    
}
