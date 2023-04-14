package com.capstone.ecommerce.e_commerce.utils;

import java.util.Set;

import com.capstone.ecommerce.e_commerce.models.Role;
import com.capstone.ecommerce.e_commerce.models.User;

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
public class UserResponse {
	 private Long idUser;
	    private String nomeCompleto;
	    private String email;
	    private String username;
	    private Set<Role> roles;
	    public static UserResponse parseUser( User user ) {

	        return UserResponse.builder()
	                .idUser( user.getIdUser() )
	                .nomeCompleto( user.getNomeCompleto() )
	                .email( user.getEmail() )
	                .username( user.getUsername() )
	                .roles( user.getRoles() )
	                .build();
	    }
}
