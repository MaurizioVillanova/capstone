package com.capstone.ecommerce.e_commerce.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
	private String username;
	private String password;
}
