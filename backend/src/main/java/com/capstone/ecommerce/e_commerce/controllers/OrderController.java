package com.capstone.ecommerce.e_commerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.ecommerce.e_commerce.models.Carrello;
import com.capstone.ecommerce.e_commerce.models.Preferito;
import com.capstone.ecommerce.e_commerce.services.CarrelloService;
import com.capstone.ecommerce.e_commerce.services.OrderService;
import com.capstone.ecommerce.e_commerce.services.PreferitoService;
import com.capstone.ecommerce.e_commerce.utils.CarrelloRequest;
import com.capstone.ecommerce.e_commerce.utils.CheckoutRequest;
import com.capstone.ecommerce.e_commerce.utils.StripeResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;


@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {

	@Autowired
    OrderService orderService;
	@Autowired
	CarrelloService carrelloService;

     // STRIPE CHECKOUT API
  //  @PostMapping("/create-checkout-session")
    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    //public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutRequest> checkoutRequestList) throws StripeException {
       // Session session = orderService.createSession(checkoutRequestList);
       // StripeResponse stripeResponse = new StripeResponse(session.getId());
      //  return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    
//}
    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<CarrelloRequest> getElementoDelCarrello(@PathVariable("id") Long id) {
        try{
            return new ResponseEntity<>(carrelloService.getAllElements(id), HttpStatus.OK);
        } catch( Exception e ) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND );
    }
}

