package com.capstone.ekart.service;

import java.util.List;

import com.capstone.ekart.dto.FestivalDTO;
import com.capstone.ekart.dto.FestivalOfferDTO;
import com.capstone.ekart.dto.SellerDTO;
import com.capstone.ekart.exception.EKartException;

public interface SellerFestivalOffersService {
	
	List<FestivalOfferDTO> getAllFestivalOffers(String sellerEmailId) throws EKartException;
	
	List<FestivalDTO> getFestivals() throws EKartException;
	
	FestivalOfferDTO addFestivalOffer(FestivalOfferDTO festivalOfferDTO, String sellerEmailId) throws EKartException;
	FestivalOfferDTO removeFestivalOffer(Integer festivalOfferId, String sellerEmailId) throws EKartException;
	
	SellerDTO getSeller(String emailId) throws EKartException;
}
