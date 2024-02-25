package com.capstone.ekart.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capstone.ekart.entity.FestivalOffer;
import com.capstone.ekart.entity.OfferStatus;

public interface FestivalOfferRepository extends CrudRepository<FestivalOffer, Integer> {
	
	@Query("select fo from FestivalOffer fo where fo.offerStatus= ?1 and fo.festival.festivalStartsAt > ?2")
	List<FestivalOffer> findByOfferStatusActiveAndAfter(OfferStatus active, LocalDateTime currenDateTime);
}
