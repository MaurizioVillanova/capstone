package com.capstone.ecommerce.e_commerce.utils;
import com.capstone.ecommerce.e_commerce.models.Prodotto;
import com.capstone.ecommerce.e_commerce.models.User;
import com.sun.istack.NotNull;
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

public class AggiungiAlCarrelloRequest {
	// private @NotNull Integer userId;
	 private User user;
	 private Prodotto prodotto;
	   // private @NotNull Integer prodottoId;
	    private @NotNull Integer quantity;
}
