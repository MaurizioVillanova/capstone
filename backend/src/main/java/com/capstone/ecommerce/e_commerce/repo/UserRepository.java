package com.capstone.ecommerce.e_commerce.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.capstone.ecommerce.e_commerce.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	 Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);
	@Transactional
    @Modifying
    @Query("UPDATE User u SET u.nomeCompleto = ?1, u.username = ?2, u.email = ?3 where u.idUser = ?4")
    int updateUserById(String nomeCompleto, String username, String email, Long id_user);
}
