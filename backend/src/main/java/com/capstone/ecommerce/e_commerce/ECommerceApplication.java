package com.capstone.ecommerce.e_commerce;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.capstone.ecommerce.e_commerce.models.Role;
import com.capstone.ecommerce.e_commerce.models.RoleType;
import com.capstone.ecommerce.e_commerce.models.User;
import com.capstone.ecommerce.e_commerce.services.RoleService;
import com.capstone.ecommerce.e_commerce.services.UserService;

@SpringBootApplication
public class ECommerceApplication implements CommandLineRunner {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(ECommerceApplication.class);
	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("CONTROLLO LA PRESENZA DEI RUOLI");
		List<Role> roles = roleService.getAll();

		if (roles.size() == 0) {
			logger.info("RUOLI NON TROVATI, LI CREO");

			Role roleUser = new Role();
			Role roleAdmin = new Role();
			roleUser.setRoleType(RoleType.ROLE_USER);
			roleAdmin.setRoleType(RoleType.ROLE_ADMIN);

			roleService.save(roleUser);
			roleService.save(roleAdmin);
			List<Role> rolesCheckAgain = roleService.getAll();

			checkAdmin(rolesCheckAgain);

		} else {
			logger.info("RUOLI TROVATI");
			checkAdmin(roles);
		}

	}

	public void checkAdmin(List<Role> roles) throws Exception {
		if (roles.size() > 0) {

			logger.info("Eseguo controllo presenza account amministratore iniziale");

			Optional<User> admin = userService.findByUsername("admin");

			if (admin.isPresent()) {
				logger.info("ACCOUNT ADMIN TROVATO: ");
				logger.info("USERNAME: " + admin.get().getUsername());
				logger.info("Password: admin");

				logger.warn("Ricordarsi di contattare la rotta /auth/login per loggarsi con le credenziali");

			} else {
				logger.error("ACCOUNT ADMIN NON TROVATO");
				logger.info("INIZIALIZZO ACCOUNT ADMIN");

				User newAdmin = new User();
				newAdmin.setNomeCompleto("Admin");
				newAdmin.setEmail("admin@gmail.com");
				newAdmin.setUsername("admin");
				newAdmin.setPassword("admin");

				Set<Role> rolesSet = new HashSet<>();
				rolesSet.add(roleService.getById(1L));
				rolesSet.add(roleService.getById(2L));
				newAdmin.setRoles(rolesSet);

				try {
					userService.save(newAdmin);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}

				logger.info("USERNAME: admin");
				logger.info("PASSWORD: admin");
				logger.info("Si consiglia in futuro di disattivare questo account e crearne uno personale");
				logger.warn("Ricordarsi di contattare la rotta /auth/login per loggarsi con le credenziali");
			}
		}
	}
	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}