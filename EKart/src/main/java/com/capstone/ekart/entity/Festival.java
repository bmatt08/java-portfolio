package com.capstone.ekart.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EK_FESTIVALS")
public class Festival {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer festivalId;
	private String festivalName;
	private LocalDateTime festivalStartsAt;
	private LocalDateTime festivalEndsAt;
	
	
	public Integer getFestivalId() {
		return festivalId;
	}
	public void setFestivalId(Integer festivalId) {
		this.festivalId = festivalId;
	}
	public String getFestivalName() {
		return festivalName;
	}
	public void setFestivalName(String festivalName) {
		this.festivalName = festivalName;
	}
	public LocalDateTime getFestivalStartsAt() {
		return festivalStartsAt;
	}
	public void setFestivalStartsAt(LocalDateTime festivalStartsAt) {
		this.festivalStartsAt = festivalStartsAt;
	}
	public LocalDateTime getFestivalEndsAt() {
		return festivalEndsAt;
	}
	public void setFestivalEndsAt(LocalDateTime festivalEndsAt) {
		this.festivalEndsAt = festivalEndsAt;
	}
	@Override
	public String toString() {
		return "Festival [festivalId=" + festivalId + ", festivalName=" + festivalName + ", festivalStartsAt="
				+ festivalStartsAt + ", festivalEndsAt=" + festivalEndsAt + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(festivalId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Festival other = (Festival) obj;
		return Objects.equals(festivalId, other.festivalId);
	}
	
	
	

}