package com.capstone.ecommerce.e_commerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.ecommerce.e_commerce.models.Carrello;
import com.capstone.ecommerce.e_commerce.models.Preferito;
import com.capstone.ecommerce.e_commerce.models.User;
import com.capstone.ecommerce.e_commerce.services.CarrelloService;
import com.capstone.ecommerce.e_commerce.services.PreferitoService;
import com.capstone.ecommerce.e_commerce.utils.AggiungiAlCarrelloRequest;
import com.capstone.ecommerce.e_commerce.utils.ApiResponse;
import com.capstone.ecommerce.e_commerce.utils.CarrelloRequest;
import com.capstone.ecommerce.e_commerce.utils.UpdateQuantityRequest;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/carrello")
@Slf4j
@CrossOrigin(origins = "*")
public class CarrelloController {

	@Autowired
    CarrelloService carrelloService;
	
    @GetMapping("/all")
    public ResponseEntity<List<Carrello>> getAll(){
        try{
            return new ResponseEntity<>(carrelloService.getAll(), HttpStatus.OK);
        } catch( Exception e ) {
            log.error( e.getMessage());
        }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // AGGIUNGI PRODOTTO AL CARRELLO
    @PostMapping("/aggiungi")
    @PreAuthorize("hasRole( 'ADMIN')")
    public ResponseEntity<Carrello> aggiungiAlCarrello(@RequestBody AggiungiAlCarrelloRequest aggiungiAlCarrelloRequest) throws Exception {
        try{
            return new ResponseEntity<>(carrelloService.aggiungiAlCarrello(aggiungiAlCarrelloRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND );
    }
    @PostMapping("/aggiungiparam")
  //  @PreAuthorize("hasAnyRole( 'ADMIN','USER')")
    public ResponseEntity<Carrello> aggiungiAlCarrelloParam(@RequestParam Long idProdotto, Long idUser, int quantity) throws Exception {
        try{
            return new ResponseEntity<>(carrelloService.aggiungiAlCarrelloParam( idProdotto,  idUser,  quantity), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND );
    }

    // GET CARRELLO FOR A USER
    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<CarrelloRequest> getElementoDelCarrello(@PathVariable("id") Long id) {
        try{
            return new ResponseEntity<>(carrelloService.getAllElements(id), HttpStatus.OK);
        } catch( Exception e ) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND );
    }
    

    // DELETE ELEMENTO DEL CARRELLO BY USER
    @DeleteMapping("/delete/{userId}/{elementoId}")
    //@PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ApiResponse> deleteElement(@PathVariable("userId") User userId, @PathVariable("elementoId") Integer elementoId) throws Exception {

        carrelloService.deleteElement(elementoId, userId);

        return new ResponseEntity<>(new ApiResponse(true, "Elemento eliminato"), HttpStatus.OK);
    }

     // PUT QUANTITY
    @PutMapping("update/{elementoId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Carrello> update(@RequestBody UpdateQuantityRequest carrelloRequest, @PathVariable("elementoId") Long elementoId) throws Exception {
        try {
            return new ResponseEntity<>(carrelloService.update(carrelloRequest, elementoId), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
