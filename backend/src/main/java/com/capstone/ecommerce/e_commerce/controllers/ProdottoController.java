package com.capstone.ecommerce.e_commerce.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.ecommerce.e_commerce.models.Categoria;
import com.capstone.ecommerce.e_commerce.models.Disponibilità;
import com.capstone.ecommerce.e_commerce.models.Prodotto;
import com.capstone.ecommerce.e_commerce.services.ProdottoService;
import com.capstone.ecommerce.e_commerce.utils.ProdottoRequest;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/prodotti")
@Slf4j
@CrossOrigin(origins = "*")
public class ProdottoController {
    @Autowired
    ProdottoService service;

    // GET ALL
    @GetMapping("")
    public ResponseEntity<List<Prodotto>> getAll(){
        try{
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch( Exception e ) {
            log.error( e.getMessage());
        }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    // GET ALL
    @GetMapping("/all")
    public ResponseEntity<List<String>> getAll2(){
        try{
            return new ResponseEntity<>(service.getAll2(), HttpStatus.OK);
        } catch( Exception e ) {
            log.error( e.getMessage());
        }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // GET ALL AND PAGINATE
    @GetMapping("Iapi/prodotti")
    public ResponseEntity<Page<Prodotto>> getAllPageable(Pageable p){
        try{
            return new ResponseEntity<>(service.getAllPaginate(p), HttpStatus.OK);
        } catch( Exception e ) {
            log.error( e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Prodotto> getById(@PathVariable("id") Long id) throws Exception {
        try{
            return new ResponseEntity<>(service.getById( id), HttpStatus.OK);
        } catch( Exception e ) {
            log.error( e.getMessage() );
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // RITORNA UNA LISTA DI PRODOTTI FILTRATI PER CATEGORIA
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Prodotto>> getProdottoByCategoria( @PathVariable("categoria") String categoria ) {
        try{
            return new ResponseEntity<>(
                    service.getByCategory( categoria ),
                    HttpStatus.OK
            );
        } catch( Exception e ) {
            log.error( e.getMessage() );
        }
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
   
  

    @GetMapping("/categoria/{categoria}/nomeDesc")
    public ResponseEntity<List<Prodotto>> getProdottoByCategoriaOrderByNameDesc( @PathVariable("categoria") String categoria ) {
        try{
            return new ResponseEntity<>(
                    service.getByCategoryOrderByNomeDesc( categoria ),
                    HttpStatus.OK
            );
        } catch( Exception e ) {
            log.error( e.getMessage() );
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/categoria/{categoria}/nomeAsc")
    public ResponseEntity<List<Prodotto>> getProdottoByCategoriaOrderByNameAsc( @PathVariable("categoria") String categoria ) {
        try{
            return new ResponseEntity<>(
                    service.getByCategoryOrderByNomeAsc( categoria ),
                    HttpStatus.OK
            );
        } catch( Exception e ) {
            log.error( e.getMessage() );
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/categoria/{categoria}/prezzoAsc")
    public ResponseEntity<List<Prodotto>> getProdottoByCategoriaOrderByPrezzoAsc( @PathVariable("categoria") String categoria ) {
        try{
            return new ResponseEntity<>(
                    service.getByCategoryOrderByPrezzoAsc( categoria ),
                    HttpStatus.OK
            );
        } catch( Exception e ) {
            log.error( e.getMessage() );
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/categoria/{categoria}/prezzoDesc")
    public ResponseEntity<List<Prodotto>> getProdottoByCategoriaOrderByPrezzoDesc( @PathVariable("categoria") String categoria ) {
        try{
            return new ResponseEntity<>(
                    service.getByCategoryOrderByPrezzoDesc( categoria ),
                    HttpStatus.OK
            );
        } catch( Exception e ) {
            log.error( e.getMessage() );
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    // CREATE
    @PostMapping("/addprodotto")
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Prodotto> create(@RequestBody ProdottoRequest prodottoRequest){
        try{
            Prodotto prodotto = Prodotto.builder()
                    .nome(prodottoRequest.getNome())
                    .categoria(Categoria.valueOf( prodottoRequest.getCategoria()))
                    .descrizione(prodottoRequest.getDescrizione())
                    .disponibilita(Disponibilità.valueOf( prodottoRequest.getDisponibilita()))
                    .prezzo(prodottoRequest.getPrezzo())
                    .immagineUrl( prodottoRequest.getImmagineUrl())
                    .build();
            System.out.println(prodotto);
            service.save(prodotto);
            return new ResponseEntity<>(prodotto, HttpStatus.OK);
        } catch( Exception e ) {
            log.error( e.getMessage() );
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //UPDATE
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Prodotto> update( @RequestBody ProdottoRequest prodottoRequest, @PathVariable("id") Long id ) {

        try {

            return service.update( prodottoRequest, id );

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
  //  @PreAuthorize("hasRole('ADMIN')")
    public void deleteById( @PathVariable("id") Long id ) {

        try {

            service.delete( id );

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

    }
}
