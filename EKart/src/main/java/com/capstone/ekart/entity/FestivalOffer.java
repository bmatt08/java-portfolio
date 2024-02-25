package com.capstone.ekart.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EK_FESTIVAL_OFFERS")
public class FestivalOffer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer festivalOfferId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FESTIVAL_ID")
	private Festival festival;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	
	private Double festivalDiscount;
	
	@Enumerated(EnumType.STRING)
	private OfferStatus offerStatus;

	public Integer getFestivalOfferId() {
		return festivalOfferId;
	}

	public void setFestivalOfferId(Integer festivalOfferId) {
		this.festivalOfferId = festivalOfferId;
	}

	public Festival getFestival() {
		return festival;
	}

	public void setFestival(Festival festival) {
		this.festival = festival;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
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
		FestivalOffer other = (FestivalOffer) obj;
		return Objects.equals(festivalOfferId, other.festivalOfferId);
	}

	@Override
	public String toString() {
		return "FestivalOffer [festivalOfferId=" + festivalOfferId + ", festival=" + festival + ", product=" + product
				+ ", festivalDiscount=" + festivalDiscount + ", offerStatus=" + offerStatus + "]";
	}
	
	
	

}
