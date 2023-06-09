package com.capstone.ecommerce.e_commerce.security;

import com.capstone.ecommerce.e_commerce.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Data
public class UserDetailsImpl implements UserDetails{

	private Long idUser;
	private String username;
	private String email;
	@JsonIgnore
	private String password;
	private boolean accountNonLocked;
	private boolean accountNonExpired;
	private boolean credentialsNonExpired;
	private boolean enabled;

	private Date expirationTime;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long idUser, String username, String email, String password, boolean enabled,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.idUser = idUser;
		this.username = username;
		this.email = email;
		this.password = password;
		this.accountNonLocked = enabled;
		this.accountNonExpired = enabled;
		this.credentialsNonExpired = enabled;
		this.enabled = enabled;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build( User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleType().name())).collect(Collectors.toList());
		return new UserDetailsImpl(user.getIdUser(), user.getUsername(), user.getEmail(), user.getPassword(),
				user.getActive(), authorities);
	}
}
