package com.capstone.ekart.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="EK_SELLER")
public class Seller {
	@Id
	@Column(name="EMAIL_ID")
	private String emailId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name="ADDRESS")
	private String address;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="SELLER_EMAIL_ID")
	private List<Product> product;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="SELLER_EMAIL_ID")
	private List<FestivalOffer> festivalOffers;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public List<FestivalOffer> getFestivalOffers() {
		return festivalOffers;
	}

	public void setFestivalOffers(List<FestivalOffer> festivalOffers) {
		this.festivalOffers = festivalOffers;
	}

	@Override
	public int hashCode() {
		return Objects.hash(emailId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return Objects.equals(emailId, other.emailId);
	}

	@Override
	public String toString() {
		return "Seller [emailId=" + emailId + ", name=" + name + ", password=" + password + ", phoneNumber="
				+ phoneNumber + ", address=" + address + ", product=" + product + ", festivalOffers=" + festivalOffers
				+ "]";
	}
	
	
}
