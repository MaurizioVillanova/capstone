package com.capstone.ecommerce.e_commerce.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.ecommerce.e_commerce.models.Preferito;
import com.capstone.ecommerce.e_commerce.models.Prodotto;
import com.capstone.ecommerce.e_commerce.models.User;
import com.capstone.ecommerce.e_commerce.services.PreferitoService;
import com.capstone.ecommerce.e_commerce.utils.ApiResponse;
import com.capstone.ecommerce.e_commerce.utils.PreferitoRequest;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/preferiti")
@Slf4j
@CrossOrigin(origins = "*")
public class PreferitoController {

	 @Autowired
	    PreferitoService preferitoService;
	    @GetMapping("/all")
	  //  @PreAuthorize("hasAnyRole( 'USER','ADMIN')")
	    public ResponseEntity<List<Preferito>> getAll(){
	        try{
	            return new ResponseEntity<>(preferitoService.getAll(), HttpStatus.OK);
	        } catch( Exception e ) {
	            log.error( e.getMessage());
	        }
	       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	 // ADD PRODOTTO A PREFERITI
	  /*  @PostMapping("/aggiungi")
	    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	    public ResponseEntity<Preferito> addToPreferiti(@RequestBody PreferitoRequest preferitoRequest){
	        try{
	            return new ResponseEntity<>(preferitoService.aggiungiAiPreferiti(preferitoRequest), HttpStatus.CREATED);
	        } catch (Exception e) {
	            log.error( e.getMessage());
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }*/
	    @PostMapping("/aggiungiparam")
	    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	    public ResponseEntity<Preferito> aggiungiAiPreferitiParam(@RequestParam Long idProdotto,@RequestParam Long idUser){
	        try{
	            return new ResponseEntity<>(preferitoService.aggiungiAiPreferitiParam(  idProdotto,  idUser), HttpStatus.CREATED);
	        } catch (Exception e) {
	            log.error( e.getMessage()+ "patata");
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    /*@GetMapping("/")
	  	@PreAuthorize("hasRole('USER, ADMIN')")
	    	public ResponseEntity<List<Preferiti>>
	    }*/
	    
	    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	   // public ResponseEntity<List<Prodotto>> getPreferiti(@PathVariable("id") Long id){
	        /*try{
	            return new ResponseEntity<>(preferitoService.getPreferitiFromUser(id), HttpStatus.OK);
	        } catch( Exception e ) {
	            log.error( e.getMessage());
	        }
	        return new ResponseEntity<>( HttpStatus.NOT_FOUND );*/
	    	/*try {
	            List<Prodotto> prodotti = preferitoService.getPreferitiFromUser(id);
	            return ResponseEntity.ok(prodotti);
	        } catch (Exception e) {
	            log.error(e.getMessage());
	            return ResponseEntity.notFound().build();
	        }
	    }*/
	 // GET ALL FOR USER
	   /* @GetMapping("/{id}")
	    public ResponseEntity<Prodotto[]> getPreferiti(@PathVariable("id") Long id){
	        try {
	            List<Prodotto> prodotti = preferitoService.getPreferitiFromUser(id);
	            Prodotto[] prodottiArray = prodotti.toArray(new Prodotto[prodotti.size()]);
	            return new ResponseEntity<>(prodottiArray, HttpStatus.OK);
	        } catch (Exception e) {
	            log.error(e.getMessage());
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }*/
	   /* @GetMapping("/{id}")
	  //  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	    public ResponseEntity<List<Prodotto>> getPreferiti(@PathVariable("id") Long id){
	        try{
	            return new ResponseEntity<>(preferitoService.getPreferitiFromUser(id), HttpStatus.OK);
	        } catch( Exception e ) {
	            log.error( e.getMessage());
	        }
	        return new ResponseEntity<>( HttpStatus.NOT_FOUND );
	    }*/
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<List<Map<Long, Prodotto>>> getPreferiti(@PathVariable("id") Long id){
	        try {
	        	List<Map<Long, Prodotto>> listaPreferiti = preferitoService.getPreferitiFromUser(id);
	        	List myfavourite = new ArrayList<>();
	        	 // listaPreferiti.forEach((key, value) -> System.out.println(key + " : " + value));
	         //   Prodotto[] prodottiArray = prodotti.toArray(new Prodotto[prodotti.size()]);
	            return new ResponseEntity<>(listaPreferiti, HttpStatus.OK);
	        } catch (Exception e) {
	            log.error(e.getMessage());
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    // DELETE ELEMENT FROM PREFERITI
	    @DeleteMapping("/delete/{preferitoId}/{userId}")
	  //  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	    public ResponseEntity<ApiResponse> deleteElement(@PathVariable("preferitoId") Integer preferitoId, @PathVariable("userId") User userId  ) throws Exception {
	        preferitoService.deleteElement(preferitoId, userId);

	        return new ResponseEntity<>(new ApiResponse( true, "Elemento eliminato"), HttpStatus.OK);
	    }

	    //DELETE ELEMENT FROM PRODOTTO ID
	  /*  @DeleteMapping("/delete/prodotto/{userId}/{prodottoId}")
	    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	    public ResponseEntity<ApiResponse> deleteElementFromProdotto(@PathVariable("userId") User userId, @PathVariable("prodottoId") Integer prodottoId) throws Exception {
	        preferitoService.deleteElementByProdottoId(prodottoId, userId);

	        return new ResponseEntity<>(new ApiResponse( true, "Elemento eliminato"), HttpStatus.OK);
	    }*/
}
