package com.capstone.ekart.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.ekart.dto.CustomerFestivalOfferDTO;
import com.capstone.ekart.service.CustomerFestivalOffersService;

@RestController
@CrossOrigin
@RequestMapping(value = "/customerFestivalOffers-api")
public class CustomerFestivalOffersAPI {
	
	@Autowired
	CustomerFestivalOffersService customerOfferService;
	
	@GetMapping("/festivalOffers")
	public ResponseEntity<CustomerFestivalOfferDTO> getFestivalOfferDtos(){
		return new ResponseEntity<CustomerFestivalOfferDTO>(customerOfferService.getFestivalsAndOffers(), HttpStatus.OK);
	}
}
