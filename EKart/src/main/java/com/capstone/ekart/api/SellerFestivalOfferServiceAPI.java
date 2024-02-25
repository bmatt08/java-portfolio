package com.capstone.ekart.api;

import java.util.List; 

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.capstone.ekart.dto.FestivalDTO;
import com.capstone.ekart.dto.FestivalOfferDTO;
import com.capstone.ekart.dto.SellerDTO;
import com.capstone.ekart.exception.EKartException;
import com.capstone.ekart.service.SellerFestivalOffersService;

@RequestMapping("/sellerOffer-api")
public class SellerFestivalOfferServiceAPI {
	
	@Autowired
	SellerFestivalOffersService sellerOffersService;
	
	//User Story 1
	@GetMapping("/seller/{emailId}/get")
	public ResponseEntity<SellerDTO> getSeller(@Email @NotNull @PathVariable String emailId) throws EKartException {
		return new ResponseEntity<SellerDTO>(sellerOffersService.getSeller(emailId), HttpStatus.OK);
	}
	
	//User Story 1
	@PostMapping("/sellerOffers/{sellerEmailId}/post")
	public ResponseEntity<FestivalOfferDTO> createFestivalOffer(@Email @NotNull @PathVariable String sellerEmailId, @RequestBody @Valid FestivalOfferDTO festivalOfferDto) throws EKartException{
		return new ResponseEntity<FestivalOfferDTO>(sellerOffersService.addFestivalOffer(festivalOfferDto, sellerEmailId), HttpStatus.OK);
	}
	
	//User Story 1
	@GetMapping("/festivals")
	public ResponseEntity<List<FestivalDTO>> getFestivals() throws EKartException {
		return new ResponseEntity<List<FestivalDTO>>(sellerOffersService.getFestivals(), HttpStatus.OK);
	}
	
	//User Story 2
	@GetMapping("/sellerOffers/{emailId}/get")
	public ResponseEntity<List<FestivalOfferDTO>> getSellerOffers(@Email @NotNull @PathVariable String emailId) throws EKartException {
		return new ResponseEntity<List<FestivalOfferDTO>>(sellerOffersService.getAllFestivalOffers(emailId), HttpStatus.OK);
	}
	
	//User Story 3
	@PutMapping("/sellerOffers/{sellerEmailId}/{festivalOfferId}")
	public ResponseEntity<FestivalOfferDTO> addFestivalOfferToSeller(@Email @NotNull @PathVariable String sellerEmailId,
															@Min(value=0) @PathVariable Integer festivalOfferID) throws EKartException {
		return new ResponseEntity<FestivalOfferDTO>(sellerOffersService.removeFestivalOffer(festivalOfferID, sellerEmailId), HttpStatus.OK);
	}
}
