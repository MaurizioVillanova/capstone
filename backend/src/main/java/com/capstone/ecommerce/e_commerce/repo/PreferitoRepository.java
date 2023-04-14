package com.capstone.ecommerce.e_commerce.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.ecommerce.e_commerce.models.Preferito;

@Repository
public interface PreferitoRepository extends JpaRepository<Preferito, Long>{
	 @Query(
	            value = "select p, idPreferito from Preferito p where p.user.id = :id"
	    )
	    List<Preferito> findbyUser(@Param("id") Long id);

	    @Query(
	            value = "select p from Preferito p where p.prodotto.id = :id"
	    )
	    Optional<Preferito> findbyProdotto(@Param("id") Long id);
}
