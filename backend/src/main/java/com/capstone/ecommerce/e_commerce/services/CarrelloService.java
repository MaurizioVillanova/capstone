package com.capstone.ecommerce.e_commerce.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.ecommerce.e_commerce.models.Carrello;
import com.capstone.ecommerce.e_commerce.models.Preferito;
import com.capstone.ecommerce.e_commerce.models.User;
import com.capstone.ecommerce.e_commerce.repo.CarrelloRepository;
import com.capstone.ecommerce.e_commerce.utils.AggiungiAlCarrelloRequest;
import com.capstone.ecommerce.e_commerce.utils.CarrelloRequest;
import com.capstone.ecommerce.e_commerce.utils.ElementoDelCarrello;
import com.capstone.ecommerce.e_commerce.utils.UpdateQuantityRequest;

@Service
public class CarrelloService {

	 @Autowired
	    CarrelloRepository carrelloRepository;

	    @Autowired
	    ProdottoService prodottoService;

	    @Autowired
	    UserService userService;



	    // CREATE AND SAVE
	    public Carrello aggiungiAlCarrello(AggiungiAlCarrelloRequest aggiungiAlCarrelloRequest) throws Exception {
	        Carrello carrello = Carrello.builder()
	                .user(userService.getById((long) aggiungiAlCarrelloRequest.getUser().getIdUser()))
	                .prodotto(prodottoService.getById((long) aggiungiAlCarrelloRequest.getProdotto().getIdProdotto()))
	                .quantity(aggiungiAlCarrelloRequest.getQuantity())
	                .createdDate(new Date())
	                .build();
	        carrelloRepository.save(carrello);
	        return carrello;
	    }
	    public Carrello aggiungiAlCarrelloParam(Long idProdotto, Long idUser, int quantity) throws Exception {
	        Carrello carrello = Carrello.builder()
	                .user(userService.getById(idUser))
	                .prodotto(prodottoService.getById(idProdotto))
	                .quantity(quantity)
	                .createdDate(new Date())
	                .build();
	        carrelloRepository.save(carrello);
	        return carrello;
	    }

	    // GET CARRELLO FROM USER
	    public CarrelloRequest getAllElements(Long id) {
	       List<Carrello> listaCarrello = carrelloRepository.findbyUserOrderByCreatedDateDesc(id);

	       List<ElementoDelCarrello> elementoDelCarrello = new ArrayList<>();
	       double totalCost = 0;

	       for(Carrello carrello : listaCarrello) {
	            ElementoDelCarrello elementoDelCarr = new ElementoDelCarrello(carrello);
	            totalCost += elementoDelCarr.getQuantity() * carrello.getProdotto().getPrezzo();
	            elementoDelCarrello.add(elementoDelCarr);
	       }
	       CarrelloRequest carr = new CarrelloRequest();
	       carr.setTotalCost(totalCost);
	       carr.setCartItems(elementoDelCarrello);
	       return carr;
	    }

	    // DELETE ELEMENT FROM LIST
	    public void deleteElement(Integer elementoId, User user) throws Exception {
	        Optional<Carrello> optionalCarrello = carrelloRepository.findById(Long.valueOf(elementoId));
	        if(optionalCarrello.isEmpty()){
	            throw new Exception( "Elemento non trovato" );
	        }
	        Carrello carr = optionalCarrello.get();
	        if(carr.getUser() != user){
	            throw new Exception( "Utente non trovato");
	        }
	        carrelloRepository.delete(carr);
	    }

	    public Carrello update(UpdateQuantityRequest updateQuantityRequest, Long id) throws Exception {
	        Optional<Carrello> carrFind = carrelloRepository.findById(id);
	        if (carrFind.isPresent()) {
	            Carrello carrello = Carrello.builder()
	                    .idCarrello(carrFind.get().getIdCarrello())
	                    .user(carrFind.get().getUser())
	                    .prodotto(carrFind.get().getProdotto())
	                    .createdDate(carrFind.get().getCreatedDate())
	                    .quantity(updateQuantityRequest.getQuantity())
	                    .build();
	            carrelloRepository.save(carrello);
	            return carrello;
	        }else{
	            throw new Exception( "Elemento non trovato" );
	        }

	    }
	    public List<Carrello> getAll() {
	        return carrelloRepository.findAll();
	    }
}
