package com.capstone.ecommerce.e_commerce.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.capstone.ecommerce.e_commerce.models.Carrello;
import com.capstone.ecommerce.e_commerce.models.Preferito;
import com.capstone.ecommerce.e_commerce.repo.CarrelloRepository;
import com.capstone.ecommerce.e_commerce.utils.CheckoutRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class OrderService {
	@Value("${BASE_URL}")
	String baseURL;

	@Value("${STRIPE_SECRET_KEY}")
	String apiKey;
	@Autowired
	CarrelloRepository carRepository;

	public Session createSession(List<CheckoutRequest> checkoutRequestList) throws StripeException {
		String successURL = baseURL + "payment/success";

		String failureURL = baseURL + "payment/failure";

		Stripe.apiKey = apiKey;

		List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();

		for (CheckoutRequest checkoutRequest : checkoutRequestList) {
			sessionItemList.add(createSessionLineItem(checkoutRequest));
		}

		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT).setCancelUrl(failureURL).setSuccessUrl(successURL)
				.addAllLineItem(sessionItemList)
				.setShippingAddressCollection(SessionCreateParams.ShippingAddressCollection.builder()
						.addAllowedCountry(SessionCreateParams.ShippingAddressCollection.AllowedCountry.IT).build())
				.addShippingOption(SessionCreateParams.ShippingOption.builder()
						.setShippingRateData(SessionCreateParams.ShippingOption.ShippingRateData.builder()
								.setType(SessionCreateParams.ShippingOption.ShippingRateData.Type.FIXED_AMOUNT)
								.setFixedAmount(SessionCreateParams.ShippingOption.ShippingRateData.FixedAmount
										.builder().setAmount(0L).setCurrency("eur").build())
								.setDisplayName("Consegna gratuita")
								.setDeliveryEstimate(
										SessionCreateParams.ShippingOption.ShippingRateData.DeliveryEstimate.builder()
												.setMinimum(
														SessionCreateParams.ShippingOption.ShippingRateData.DeliveryEstimate.Minimum
																.builder()
																.setUnit(
																		SessionCreateParams.ShippingOption.ShippingRateData.DeliveryEstimate.Minimum.Unit.BUSINESS_DAY)
																.setValue(5L).build())
												.setMaximum(
														SessionCreateParams.ShippingOption.ShippingRateData.DeliveryEstimate.Maximum
																.builder()
																.setUnit(
																		SessionCreateParams.ShippingOption.ShippingRateData.DeliveryEstimate.Maximum.Unit.BUSINESS_DAY)
																.setValue(7L).build())
												.build())
								.build())
						.build())
			
				.build();
		return Session.create(params);
	}

	private SessionCreateParams.LineItem createSessionLineItem(CheckoutRequest checkoutRequest) {

		return SessionCreateParams.LineItem.builder().setPriceData(createPriceData(checkoutRequest))
				.setQuantity(Long.parseLong(String.valueOf(checkoutRequest.getQuantity()))).build();
	}

	private SessionCreateParams.LineItem.PriceData createPriceData(CheckoutRequest checkoutRequest) {
		return SessionCreateParams.LineItem.PriceData.builder().setCurrency("eur")
				.setUnitAmount((long) (checkoutRequest.getPrice() * 100))
				.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
						.setName(checkoutRequest.getProductName()).build())
				.build();
	}
	 public List<Carrello> getAll() {
	        return carRepository.findAll();
	    }
}
