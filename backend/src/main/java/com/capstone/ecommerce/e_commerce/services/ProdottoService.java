package com.capstone.ecommerce.e_commerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capstone.ecommerce.e_commerce.models.Categoria;
import com.capstone.ecommerce.e_commerce.models.Disponibilità;
import com.capstone.ecommerce.e_commerce.models.Prodotto;
import com.capstone.ecommerce.e_commerce.repo.ProdottoRepository;
import com.capstone.ecommerce.e_commerce.utils.ProdottoRequest;

@Service
public class ProdottoService {

	  @Autowired
	    ProdottoRepository repository;

	    // GET BY ID
	    public Prodotto getById(Long id ) throws Exception {
	        Optional<Prodotto> prodotto = repository.findById( id );
	        if( prodotto.isEmpty() )
	            throw new Exception( "Prodotto not available" );
	        return prodotto.get();
	    }

	    // GET ALL AND PAGINATE
	    public Page<Prodotto> getAllPaginate(Pageable p ) {
	        return repository.findAll( p );
	    }

	    // GET ALL
	    public List<Prodotto> getAll() {
	        return repository.findAll();
	    }
	 // GET ALL2
	    public List<String> getAll2() {
	        return repository.findAllProdotti();
	    }

	    // GET BY CATEGORIA
	   /* public List<Prodotto> getByCategory(String categoria){
	        return repository.findProdottoByCategoria(Categoria.valueOf(categoria));
	    }*/
	    public List<Prodotto> getByCategory(String categoria) {
	        try {
	            Categoria cat = Categoria.valueOf(categoria.toUpperCase());
	            return repository.findByCategoria(cat);
	        } catch (IllegalArgumentException e) {
	            // La categoria non è valida
	            throw new IllegalArgumentException("La categoria " + categoria + " non è valida");
	        }
	    } 

	    // GET BY CATEGORIA AND ORDER BY NOME ASC
	    public List<Prodotto> getByCategoryOrderByNomeAsc(String categoria){
	        return repository.findProdottoByCategoriaAndOrderByNomeAsc(Categoria.valueOf(categoria));
	    }

	    // GET BY CATEGORIA AND ORDER BY NOME DESC
	    public List<Prodotto> getByCategoryOrderByNomeDesc(String categoria){
	        return repository.findProdottoByCategoriaAndOrderByNomeDesc(Categoria.valueOf(categoria));
	    }

	    // GET BY CATEGORIA AND ORDER BY PREZZO ASC
	    public List<Prodotto> getByCategoryOrderByPrezzoAsc(String categoria){
	        return repository.findProdottoByCategoriaAndOrderByPrezzoAsc(Categoria.valueOf(categoria));
	    }

	    // GET BY CATEGORIA AND ORDER BY PREZZO DESC
	    public List<Prodotto> getByCategoryOrderByPrezzoDesc(String categoria){
	        return repository.findProdottoByCategoriaAndOrderByPrezzoDesc(Categoria.valueOf(categoria));
	    }

	    // CREATE
	    public void save( Prodotto prodotto ) {
	        repository.save( prodotto );
	    }

	    // UPDATE
	    public ResponseEntity<Prodotto> update(ProdottoRequest prodottoRequest, Long id) {
	        Optional<Prodotto> prodottoFinded = repository.findById( id);

	        if(prodottoFinded.isPresent()){
	            Prodotto p = Prodotto.builder()
	                    .idProdotto(prodottoFinded.get().getIdProdotto())
	                    .nome(prodottoRequest.getNome() == null ? prodottoFinded.get().getNome() : prodottoRequest.getNome())
	                    .prezzo( prodottoRequest.getPrezzo() == 0 ? prodottoFinded.get().getPrezzo() : prodottoRequest.getPrezzo())
	                    .immagineUrl( prodottoRequest.getImmagineUrl() == null ? prodottoFinded.get().getImmagineUrl() : prodottoRequest.getImmagineUrl() )
	                    .descrizione( prodottoRequest.getDescrizione() == null ? prodottoFinded.get().getDescrizione() : prodottoRequest.getDescrizione())
	                    .categoria( prodottoRequest.getCategoria() == null ? prodottoFinded.get().getCategoria() : Categoria.valueOf(prodottoRequest.getCategoria()))
	                    .disponibilita( prodottoRequest.getDisponibilita() == null ? prodottoFinded.get().getDisponibilita() : Disponibilità.valueOf(prodottoRequest.getDisponibilita()))
	                    .build();

	            return new ResponseEntity<>(repository.save(p), HttpStatus.OK);
	        }
	        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
	    }

	    // DELETE
	    public void delete(Long id) throws Exception {
	        Optional<Prodotto> prodotto = repository.findById( id);
	        if ( prodotto.isPresent() ){
	            repository.delete( prodotto.get() );
	        } else {
	            throw new Exception( "Prodotto non trovato");
	        }
	    }
}
