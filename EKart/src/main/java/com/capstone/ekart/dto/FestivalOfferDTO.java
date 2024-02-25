package com.capstone.ekart.dto;

import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.capstone.ekart.entity.OfferStatus;

public class FestivalOfferDTO {

	@Null(message = "{festivalOffer:invalid.id}")
	private Integer festivalOfferId;
	
	@NotNull
	private FestivalDTO festival;
	
	@NotNull
	private ProductDTO product;
	
	@NotNull
	@Min(value = 1, message = "{festivalOffer.invalid.discount}")
	private Double festivalDiscount;
	
	@NotNull
	private OfferStatus offerStatus;

	public FestivalOfferDTO() {
		super();
	}
	
	
	
	
	public FestivalOfferDTO(Integer festivalOfferId, FestivalDTO festival, ProductDTO product, Double festivalDiscount, OfferStatus offerStatus) {
		super();
		this.festivalOfferId = festivalOfferId;
		this.festival = festival;
		this.product = product;
		this.festivalDiscount = festivalDiscount;
		this.offerStatus = offerStatus;
	}




	public Integer getFestivalOfferId() {
		return festivalOfferId;
	}

	public void setFestivalOfferId(Integer festivalOfferId) {
		this.festivalOfferId = festivalOfferId;
	}

	public FestivalDTO getFestival() {
		return festival;
	}

	public void setFestival(FestivalDTO festival) {
		this.festival = festival;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public Double getFestivalDiscount() {
		return festivalDiscount;
	}

	public void setFestivalDiscount(Double festivalDiscount) {
		this.festivalDiscount = festivalDiscount;
	}

	public OfferStatus getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(OfferStatus offerStatus) {
		this.offerStatus = offerStatus;
	}

	@Override
	public String toString() {
		return "FestivalOfferDTO [festivalOfferId=" + festivalOfferId + ", festival=" + festival + ", product="
				+ product + ", festivalDiscount=" + festivalDiscount + ", offerStatus=" + offerStatus + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(festivalOfferId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FestivalOfferDTO other = (FestivalOfferDTO) obj;
		return Objects.equals(festivalOfferId, other.festivalOfferId);
	}
	
	
	
	
	
}
