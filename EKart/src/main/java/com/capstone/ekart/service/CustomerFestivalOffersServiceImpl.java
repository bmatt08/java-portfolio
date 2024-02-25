package com.capstone.ekart.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.ekart.dto.CustomerFestivalOfferDTO;
import com.capstone.ekart.dto.FestivalDTO;
import com.capstone.ekart.dto.FestivalOfferDTO;
import com.capstone.ekart.entity.OfferStatus;
import com.capstone.ekart.repository.FestivalOfferRepository;
import com.capstone.ekart.repository.FestivalRepository;

@Service(value = "customerFestivalOfferService")
@Transactional
public class CustomerFestivalOffersServiceImpl implements CustomerFestivalOffersService {

	@Autowired
	FestivalOfferRepository festivalOfferRepository;
	
	@Autowired
	FestivalRepository festivalRepository;
	
	
	@Override
	public CustomerFestivalOfferDTO getFestivalsAndOffers() {
		
		CustomerFestivalOfferDTO customerFestivalOffers = new CustomerFestivalOfferDTO();
		List<FestivalDTO> festivalDTOs = festivalRepository.findByFestivalStartsAfter(LocalDateTime.now())
				.stream().map(SellerFestivalOffersServiceImpl::entityToDTO).collect(Collectors.toList());
		
		List<FestivalOfferDTO> festivalOfferDTOs = festivalOfferRepository.findByOfferStatusActiveAndAfter(OfferStatus.ACTIVE, LocalDateTime.now())
				.stream().map(SellerFestivalOffersServiceImpl::entityToDTO).collect(Collectors.toList());
		customerFestivalOffers.setFestivalDTOs(festivalDTOs);
		customerFestivalOffers.setFestivalOffersDTOs(festivalOfferDTOs);
		return customerFestivalOffers;
	}
	
}
