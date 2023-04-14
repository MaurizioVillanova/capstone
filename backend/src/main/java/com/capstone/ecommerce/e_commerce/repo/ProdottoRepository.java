package com.capstone.ecommerce.e_commerce.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.ecommerce.e_commerce.models.Categoria;
import com.capstone.ecommerce.e_commerce.models.Prodotto;
@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
	@Query(
            value = "select p from Prodotto p where p.categoria = :categoria"
    )
    List<Prodotto> findProdottoByCategoria(@Param("categoria") Categoria categoria);

    @Query(
            value = "select p from Prodotto p where p.categoria = :categoria order by nome asc"
    )
    List<Prodotto> findProdottoByCategoriaAndOrderByNomeAsc(@Param("categoria") Categoria categoria);

    @Query(
            value = "select p from Prodotto p where p.categoria = :categoria order by nome desc"
    )
    List<Prodotto> findProdottoByCategoriaAndOrderByNomeDesc(@Param("categoria") Categoria categoria);

    @Query(
            value = "select p from Prodotto p where p.categoria = :categoria order by prezzo desc"
    )
    List<Prodotto> findProdottoByCategoriaAndOrderByPrezzoDesc(@Param("categoria") Categoria categoria);

    @Query(
            value = "select p from Prodotto p where p.categoria = :categoria order by prezzo asc"
    )
    List<Prodotto> findProdottoByCategoriaAndOrderByPrezzoAsc(@Param("categoria") Categoria categoria);
    
	@Query( value = "select p.categoria, p.nome, p.immagineUrl, p.prezzo, p.descrizione, p.disponibilita from Prodotto p" )
    List<String> findAllProdotti();
	
	 List<Prodotto> findByCategoria(Categoria categoria);
}
