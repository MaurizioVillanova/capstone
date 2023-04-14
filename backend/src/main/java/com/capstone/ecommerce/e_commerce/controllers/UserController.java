package com.capstone.ecommerce.e_commerce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.ecommerce.e_commerce.models.RoleType;
import com.capstone.ecommerce.e_commerce.models.User;
import com.capstone.ecommerce.e_commerce.models.UserResponse;
import com.capstone.ecommerce.e_commerce.services.RoleService;
import com.capstone.ecommerce.e_commerce.services.UserService;
import com.capstone.ecommerce.e_commerce.utils.UserRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	// GET ALL
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	@CrossOrigin
	public List<User> getAllUsers() {

		return userService.getAll();

	}

	// GET ALL PAGEABLE
	@GetMapping("/pageable")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<Page<User>> getAllUsersPageable(Pageable p) {

		Page<User> findAll = userService.getAllPaginate(p);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	// GET BY ID
	@GetMapping("/{id}")
	//@PreAuthorize("hasAnyRole('ADMIN','USER')")
	// @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> getById(@PathVariable Long id) throws Exception {

		return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
	}

	@GetMapping("/useremail/{email}")
	public User getUser(@PathVariable(required = true) String email) {
		Optional<User> u = userService.findByEmail(email);
		if (u.isPresent()) {
			User u1 = u.get();
			return  u1;
		} else {
			return null;
		}
	}

	// GET BY USERNAME (UNIQUE)
	@GetMapping("/username/{username}")
	public User getByUsername(@PathVariable(required = true) String username) {
		Optional<User> u = userService.findByUsername(username);
		if (u.isPresent()) {
			User u1 = u.get();
			return u1;
		} else {
			return null;
		}
	}

	// AGGIUNGI UN NUOVO UTENTE CON IL BODY COME RICHIESTA
	@PostMapping("/adduser")
	public UserResponse create(@RequestBody UserRequest user) {

		try {

			return userService.createAndSave(user);

		} catch (Exception e) {

			log.error(e.getMessage());

		}

		return null;

	}

	// UPDATE
	@PutMapping("/updateuser/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<UserResponse> update(@RequestBody UserRequest user, @PathVariable("id") Long id) {

		try {
			return new ResponseEntity<>(userService.updateUserById(user, id), HttpStatus.OK);

		} catch (Exception e) {

			log.error(e.getMessage());

		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// ADD ROLE ADMIN
	@PutMapping("/{id}/add-role/{roleType}")
	@PreAuthorize("hasRole('ADMIN')")
	public void addRole(@PathVariable("id") Long id, @PathVariable("roleType") String roleType) throws Exception {

		User u = userService.getById(id);

		if (roleType.equals("ADMIN")) {

			u.addRole(roleService.getByRole(RoleType.ROLE_ADMIN));

			userService.update(u);

		}

	}

	// DELETE
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteById(@PathVariable Long id) {

		try {

			userService.delete(id) ;

		} catch (Exception e) {

			log.error(e.getMessage());

		}

	}
}
