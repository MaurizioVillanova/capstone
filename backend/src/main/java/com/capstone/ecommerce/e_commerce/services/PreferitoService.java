package com.capstone.ecommerce.e_commerce.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.ecommerce.e_commerce.models.Preferito;
import com.capstone.ecommerce.e_commerce.models.Prodotto;
import com.capstone.ecommerce.e_commerce.models.User;
import com.capstone.ecommerce.e_commerce.repo.PreferitoRepository;
import com.capstone.ecommerce.e_commerce.utils.PreferitoRequest;

@Service
public class PreferitoService {
	  @Autowired
	    PreferitoRepository preferitoRepository;

	    @Autowired
	    ProdottoService prodottoService;

	    @Autowired
	    UserService userService;

	    // CREATE AND SAVE
	  /*  public Preferito aggiungiAiPreferiti(PreferitoRequest preferitoRequest) throws Exception {
	        Preferito preferito = Preferito.builder()
	                .user(userService.getById((long) preferitoRequest.getUser().getIdUser()))
	                .prodotto(prodottoService.getById((long) preferitoRequest.getProdotto().getIdProdotto()))
	                .build();
	        preferitoRepository.save(preferito);
	        return preferito;
	    }*/
	    public Preferito aggiungiAiPreferitiParam(Long idProdotto, Long idUser) throws Exception {
	        Preferito preferito = Preferito.builder()
	        		.user(userService.getById(idUser))
	                .prodotto(prodottoService.getById(idProdotto))
	                .build();
	        preferitoRepository.save(preferito);
	        return preferito;
	    }
	    public List<Preferito> getAll() {
	        return preferitoRepository.findAll();
	    }


	    // GET PREFERITI FROM USER
	   /* public List<Prodotto> getPreferitiFromUser(Long id) {
	        List<Preferito> preferiti = preferitoRepository.findbyUser(id);
	        List<Prodotto> prodotti = new ArrayList<>();

	        for (Preferito preferito : preferiti){
	            prodotti.add(preferito.getProdotto());
	        }
	        return prodotti;
	    }*/
	 // GET PREFERITI FROM USER2
	    public List<Map<Long, Prodotto>> getPreferitiFromUser(Long id) {
	        List<Preferito> preferiti = preferitoRepository.findbyUser(id);
	       // List<Prodotto> prodotti = new ArrayList<>();
	        Map<Long, Prodotto> mapPreferiti = new HashMap<>();
	        List<Map<Long, Prodotto>> p = new ArrayList<>();
	        for (Preferito preferito : preferiti){
	           mapPreferiti.put(preferito.getIdPreferito(),  preferito.getProdotto());
	        }
	       // Object[] objectArray = mapPreferiti.entrySet().toArray();
	        mapPreferiti.forEach((key, value) -> System.out.println(key + " : " + value));
	        p.add(mapPreferiti);
	        return p;
	    }
	    // DELETE ELEMENT FROM PREFERITI
	    public void deleteElement(Integer elementoId, User user) throws Exception {
	        Optional<Preferito> optionalPreferito = preferitoRepository.findById(Long.valueOf(elementoId));


	        if(optionalPreferito.isEmpty()){
	            throw new Exception( "Elemento non trovato");
	        }
	        Preferito pref = optionalPreferito.get();

	        if(pref.getUser() != user){
	            throw new Exception( "Utente non trovato");
	        }
	        preferitoRepository.delete(pref);
	    }

	    // DELETE FROM PRODOTTOID
	    public void deleteElementByProdottoId(Integer prodottoId, User user) throws Exception {
	        Optional<Preferito> optionalPreferito = preferitoRepository.findbyProdotto(Long.valueOf(prodottoId));

	        if(optionalPreferito.isEmpty()){
	            throw new Exception( "Elemento non trovato");
	        }
	        Preferito pref = optionalPreferito.get();

	        if(pref.getUser() != user){
	            throw new Exception( "Utente non trovato");
	        }
	        preferitoRepository.delete(pref);
	    }
}
