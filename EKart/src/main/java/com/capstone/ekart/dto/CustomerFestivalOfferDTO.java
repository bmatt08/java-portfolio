package com.capstone.ekart.dto;

import java.util.List;

public class CustomerFestivalOfferDTO {
	
	private List<FestivalOfferDTO> festivalOffersDTOs;
	
	private List<FestivalDTO> festivalDTOs;

	public List<FestivalOfferDTO> getFestivalOffersDTOs() {
		return festivalOffersDTOs;
	}

	public void setFestivalOffersDTOs(List<FestivalOfferDTO> festivalOffersDTOs) {
		this.festivalOffersDTOs = festivalOffersDTOs;
	}

	public List<FestivalDTO> getFestivalDTOs() {
		return festivalDTOs;
	}

	public void setFestivalDTOs(List<FestivalDTO> festivalDTOs) {
		this.festivalDTOs = festivalDTOs;
	}
}
