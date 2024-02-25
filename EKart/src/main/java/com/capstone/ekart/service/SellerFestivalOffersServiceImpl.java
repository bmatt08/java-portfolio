package com.capstone.ekart.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.ekart.dto.FestivalDTO;
import com.capstone.ekart.dto.FestivalOfferDTO;
import com.capstone.ekart.dto.ProductDTO;
import com.capstone.ekart.dto.SellerDTO;
import com.capstone.ekart.entity.Festival;
import com.capstone.ekart.entity.FestivalOffer;
import com.capstone.ekart.entity.OfferStatus;
import com.capstone.ekart.entity.Product;
import com.capstone.ekart.entity.Seller;
import com.capstone.ekart.exception.EKartException;
import com.capstone.ekart.repository.FestivalOfferRepository;
import com.capstone.ekart.repository.FestivalRepository;
import com.capstone.ekart.repository.ProductRepository;
import com.capstone.ekart.repository.SellerRepository;

@Service(value = "sellerFestivalOfferService")
@Transactional
public class SellerFestivalOffersServiceImpl implements SellerFestivalOffersService {
	
	@Autowired
	SellerRepository sellerRepository;
	
	@Autowired
	FestivalOfferRepository festivalOfferRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	FestivalRepository festivalRepository;
	
	private static ProductDTO entityToDTO(Product entity) {
		ProductDTO dto = new ProductDTO();
		dto.setBrand(entity.getBrand());
		dto.setCategory(entity.getCategory());
		dto.setDescription(entity.getDescription());
		dto.setDiscount(entity.getDiscount());
		dto.setName(entity.getName());
		dto.setPrice(entity.getPrice());
		dto.setProductId(entity.getProductId());
		dto.setQuantity(entity.getQuantity());
		return dto;
	}
	
	static FestivalOfferDTO entityToDTO(FestivalOffer entity) {
		FestivalOfferDTO dto = new FestivalOfferDTO();
		dto.setFestivalDiscount(entity.getFestivalDiscount());
		dto.setFestivalOfferId(entity.getFestivalOfferId());
		dto.setOfferStatus(entity.getOfferStatus());
		dto.setFestival(entityToDTO(entity.getFestival()));
		dto.setProduct(entityToDTO(entity.getProduct()));
		return dto;
	}
	
	static FestivalDTO entityToDTO(Festival entity) {
		FestivalDTO dto = new FestivalDTO();
		dto.setFestivalId(entity.getFestivalId());
		dto.setFestivalName(entity.getFestivalName());
		dto.setFestivalStartsAt(entity.getFestivalStartsAt());
		dto.setFestivalEndsAt(entity.getFestivalEndsAt());
		return dto;
	}
	
	static SellerDTO entityToDTO(Seller entity) {
		SellerDTO dto = new SellerDTO();
		
		
		dto.setFestivalOfferDTOs(entity.getFestivalOffers().stream().map(SellerFestivalOffersServiceImpl::entityToDTO).collect(Collectors.toList()));
		dto.setProducts(entity.getProduct().stream().map(SellerFestivalOffersServiceImpl::entityToDTO).collect(Collectors.toList()));
		return dto;
	}
	
	private static FestivalOffer dtoToEntity(FestivalOfferDTO dto) {
		FestivalOffer entity = new FestivalOffer();
		entity.setFestivalDiscount(dto.getFestivalDiscount());
		entity.setOfferStatus(dto.getOfferStatus());
		
		if(dto.getFestival() != null)
			entity.setFestival(dtoToEntity(dto.getFestival()));
		if(dto.getProduct() != null)
			entity.setProduct(dtoToEntity(dto.getProduct()));
		
		return entity;
	}
	
	private static Festival dtoToEntity(FestivalDTO dto) {
		Festival entity  = new Festival();
		entity.setFestivalEndsAt(dto.getFestivalEndsAt());
		entity.setFestivalStartsAt(dto.getFestivalStartsAt());
		entity.setFestivalName(dto.getFestivalName());
		entity.setFestivalId(dto.getFestivalId());
		return entity;
	}
	
	private static Product dtoToEntity(ProductDTO dto) {
		Product entity = new Product();
		entity.setBrand(dto.getBrand());
		entity.setCategory(dto.getCategory());
		entity.setDescription(dto.getDescription());
		entity.setDiscount(dto.getDiscount());
		entity.setName(dto.getName());
		entity.setPrice(dto.getPrice());
		entity.setQuantity(dto.getQuantity());
		return entity;
	}
	
	@Override
	public List<FestivalOfferDTO> getAllFestivalOffers(String sellerEmailId) throws EKartException {
		Seller seller = this.sellerRepository.findById(sellerEmailId).orElseThrow(()-> new EKartException("Service.SELLER_EMAIL_NOT_FOUND")); 
		return seller.getFestivalOffers().stream().map(SellerFestivalOffersServiceImpl::entityToDTO).collect(Collectors.toList());
	}
	
	@Override
	public FestivalOfferDTO addFestivalOffer(FestivalOfferDTO festivalOfferDTO, String sellerEmailId) throws EKartException {
		Seller seller = sellerRepository.findById(sellerEmailId).orElseThrow(() -> new EKartException("Service.SELLER_NOT_FOUND"));
		FestivalOffer newOffer = dtoToEntity(festivalOfferDTO);
		if(festivalOfferDTO.getFestival().getFestivalId()==null)
			throw new EKartException("Service.FESTIVAL_ID_ABSENT");
		
		Festival festival = festivalRepository.findById(newOffer.getFestival().getFestivalId()).orElseThrow(() -> new EKartException("Service.FESTIVAL_NOT_FOUND"));
		if(festival.getFestivalEndsAt().isBefore(festival.getFestivalStartsAt()))
			throw new EKartException("Service.INVALID_FESTIVAL_DURATION");
		
		if(festivalOfferDTO.getProduct().getProductId()==null)
			throw new EKartException("Service.PRODUCT_ID_ABSENT");
		
		Product product = productRepository.findById(newOffer.getProduct().getProductId()).orElseThrow(() -> new EKartException("Service.PRODUCT_NOT_FOUND"));
		if(!seller.getProduct().contains(product))
			throw new EKartException("Service.SELLER_DOES_NOT_SELL_THIS_PRODUCT");
		
		List<FestivalOffer> festivalsOfTheProduct = seller.getFestivalOffers()
														  .stream()
														  .filter(offer -> offer.getOfferStatus().equals(OfferStatus.ACTIVE) && offer.getProduct().equals(product))
														  .collect(Collectors.toList());
		if(festivalsOfTheProduct.contains(festival)) throw new EKartException("Service.PRODUCT_ALREADY_IN_FESTIVAL");
		
		newOffer.setFestival(festival);
		newOffer.setProduct(product);
		
		FestivalOffer addedOfferDTO = festivalOfferRepository.save(newOffer);
		
		if(seller.getFestivalOffers()==null)
			seller.setFestivalOffers(new ArrayList<>());
		seller.getFestivalOffers().add(newOffer);
		
		return entityToDTO(addedOfferDTO);
	}
	
	@Override
	public FestivalOfferDTO removeFestivalOffer(Integer festivalOfferId, String sellerEmailId) throws EKartException {
		Seller seller = sellerRepository.findById(sellerEmailId).orElseThrow(() -> new EKartException("Service.SELLER_NOT_FOUND"));
		if(seller.getFestivalOffers()==null)
			throw new EKartException("Service.SELLER_DOES_NOT_HAVE_OFFER");
		
		List<FestivalOffer> festivalOffers = seller.getFestivalOffers().stream()
																	   .filter(offer -> offer.getFestivalOfferId()
																	   .equals(festivalOfferId))
																	   .collect(Collectors.toList());
		if(festivalOffers.isEmpty()) {
			throw new EKartException("This Seller Does Not Have This Offer To Remove");
		}
		FestivalOffer removedOffer = festivalOffers.get(0);
		removedOffer.setOfferStatus(OfferStatus.INACTIVE);
		return entityToDTO(removedOffer);
	}
	@Override
	public SellerDTO getSeller(String emailId) throws EKartException {
		Seller seller = sellerRepository.findById(emailId).orElseThrow(()-> new EKartException("Service.SELLER_NOT_FOUND"));
		return entityToDTO(seller);
	}
	@Override public List<FestivalDTO> getFestivals() throws EKartException {
		List<FestivalDTO> festivalDTOs = festivalRepository.findByFestivalStartsAfter(LocalDateTime.now()).stream()
																										  .map(SellerFestivalOffersServiceImpl::entityToDTO)
																										  .collect(Collectors.toList());
		return festivalDTOs;
	}
}
