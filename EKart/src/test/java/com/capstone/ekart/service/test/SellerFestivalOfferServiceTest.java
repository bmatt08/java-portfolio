package com.capstone.ekart.service.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.ekart.dto.FestivalDTO;
import com.capstone.ekart.dto.FestivalOfferDTO;
import com.capstone.ekart.dto.ProductDTO;
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
import com.capstone.ekart.service.SellerFestivalOffersService;

@SpringBootTest
public class SellerFestivalOfferServiceTest {
	
	@Mock
	FestivalRepository festivalRepository;
	
	@Mock
	FestivalOfferRepository festivalOfferRepository;
	
	@Mock 
	ProductRepository productRepository;
	
	@Mock
	SellerRepository sellerRepository;
	
	@InjectMocks
	SellerFestivalOffersService sellerFestivalOffersService;
	
	@Test
	void addFestivalOfferSellerNotFoundTest() {
		Mockito.when(sellerRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		
		EKartException e = Assertions.assertThrows(EKartException.class,
												  () -> sellerFestivalOffersService.addFestivalOffer(new FestivalOfferDTO(), "nonexistant@id.com"));
		Assertions.assertEquals("Service.SELLER_NOT_FOUND", e.getMessage());
	}
	
	@Test
	void addFestivalOfferNotFestivalInDtoTest() {
		FestivalOfferDTO festivalOfferDTO = new FestivalOfferDTO();
		festivalOfferDTO.setFestival(new FestivalDTO());
		Mockito.when(sellerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(new Seller()));
		
		EKartException e = Assertions.assertThrows(EKartException.class,
												  () -> sellerFestivalOffersService.addFestivalOffer(festivalOfferDTO, "existant@id.com"));
		Assertions.assertEquals("Service.FESTIVAL_ID_ABSENT", e.getMessage());
	}
	
	@Test
	void addFestivalOfferFestivalNotFoundTest() {
		FestivalOfferDTO festivalOfferDTO = new FestivalOfferDTO();
		FestivalDTO festivalDTO = new FestivalDTO();
		festivalDTO.setFestivalId(123);
		festivalOfferDTO.setFestival(festivalDTO);
		
		Mockito.when(sellerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(new Seller()));
		Mockito.when(festivalRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		
		EKartException e = Assertions.assertThrows(EKartException.class,
												  () -> sellerFestivalOffersService.addFestivalOffer(festivalOfferDTO, "existant@id.com"));
		Assertions.assertEquals("Service.FESTIVAL_NOT_FOUND",e.getMessage());
	}
	
	@Test
	void addFestivalOfferFestivalInPastTest() {
		FestivalOfferDTO festivalOfferDTO = new FestivalOfferDTO();
		FestivalDTO festivalDTO = new FestivalDTO();
		festivalDTO.setFestivalId(123);
		festivalOfferDTO.setFestival(festivalDTO);
		
		Festival returnedFestival = new Festival();
		returnedFestival.setFestivalStartsAt(LocalDateTime.now().minusDays(10));
		
		Mockito.when(sellerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(new Seller()));
		Mockito.when(festivalRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(returnedFestival));
		
		EKartException e = Assertions.assertThrows(EKartException.class,
													() -> sellerFestivalOffersService.addFestivalOffer(festivalOfferDTO, "existant@id.com"));
		Assertions.assertEquals("Service.FESTIVAL_IN_PAST",e.getMessage());
	}
	
	@Test
	void addFestivalOfferProductIdNotFoundTest() {
		FestivalOfferDTO festivalOfferDTO = new FestivalOfferDTO();
		FestivalDTO festivalDTO = new FestivalDTO();
		festivalOfferDTO.setFestival(festivalDTO);
		festivalDTO.setFestivalId(123);
		Festival returnedFestival = new Festival();
		returnedFestival.setFestivalStartsAt(LocalDateTime.now().plusDays(10));
		festivalOfferDTO.setProduct(new ProductDTO());
		
		Mockito.when(sellerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(new Seller()));
		Mockito.when(festivalRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(returnedFestival));
		
		EKartException e = Assertions.assertThrows(EKartException.class,
												  () -> sellerFestivalOffersService.addFestivalOffer(festivalOfferDTO, "existant@id.com"));
		Assertions.assertEquals("Service.PRODUCT_ID_ABSENT", e.getMessage());
	}
	
	@Test
	void addFestivalOfferProductNotFoundTest() {
		FestivalOfferDTO festivalOfferDTO = new FestivalOfferDTO();
		FestivalDTO festivalDTO = new FestivalDTO();
		festivalDTO.setFestivalId(123);
		festivalOfferDTO.setFestival(festivalDTO);
		
		Festival returnedFestival = new Festival();
		returnedFestival.setFestivalStartsAt(LocalDateTime.now().plusDays(10));
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId(42);
		festivalOfferDTO.setProduct(productDTO);
		
		Mockito.when(sellerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(new Seller()));
		Mockito.when(festivalRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(returnedFestival));
		Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		
		EKartException e = Assertions.assertThrows(EKartException.class,
												  () -> sellerFestivalOffersService.addFestivalOffer(festivalOfferDTO, "existant@id.com"));
		Assertions.assertEquals("Service.PRODUCT_NOT_FOUND", e.getMessage());
	}
	
	@Test
	void addFestivalOfferSellerDoesNotSellProductTest() {
		FestivalOfferDTO festivalOfferDTO = new FestivalOfferDTO();
		FestivalDTO festivalDTO = new FestivalDTO();
		festivalDTO.setFestivalId(123);
		festivalOfferDTO.setFestival(festivalDTO);
		
		Festival returnedFestival = new Festival();
		returnedFestival.setFestivalStartsAt(LocalDateTime.now().plusDays(10));
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId(42);
		festivalOfferDTO.setProduct(productDTO);
		
		Product returnedProduct = new Product();
		Seller returnedSeller = new Seller();
		returnedSeller.setProduct(new ArrayList<Product>());
		
		Mockito.when(sellerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(returnedSeller));
		Mockito.when(festivalRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(returnedFestival));
		Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(returnedProduct));
		
		EKartException e = Assertions.assertThrows(EKartException.class,
												  () -> sellerFestivalOffersService.addFestivalOffer(festivalOfferDTO, "existant@id.com"));
		Assertions.assertEquals("Service.SELLER_DOES_NOT_SELL_THIS_PRODUCT", e.getMessage());
	}
	
	@Test
	void addFestivalOfferProductIsAlreadyInTheFestivalTest() {
		FestivalOfferDTO festivalOfferDTO = new FestivalOfferDTO();
		FestivalDTO festivalDTO = new FestivalDTO();
		festivalDTO.setFestivalId(123);
		festivalOfferDTO.setFestival(festivalDTO);
		
		Festival returnedFestival = new Festival();
		returnedFestival.setFestivalStartsAt(LocalDateTime.now().plusDays(10));
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId(42);
		festivalOfferDTO.setProduct(productDTO);
		
		Product returnedProduct = new Product();
		returnedProduct.setProductId(42);
		
		Seller returnedSeller = new Seller();
		returnedSeller.setProduct(new ArrayList<Product>());
		returnedSeller.getProduct().add(returnedProduct);
		returnedSeller.setFestivalOffers(new ArrayList<FestivalOffer>());
		
		FestivalOffer festivalOffer = new FestivalOffer();
		festivalOffer.setFestival(returnedFestival);
		festivalOffer.setProduct(returnedProduct);
		festivalOffer.setOfferStatus(OfferStatus.ACTIVE);
		
		returnedSeller.getFestivalOffers().add(festivalOffer);
		
		Mockito.when(sellerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(returnedSeller));
		Mockito.when(festivalRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(returnedFestival));
		Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(returnedProduct));
		
		EKartException e = Assertions.assertThrows(EKartException.class,
												  () -> sellerFestivalOffersService.addFestivalOffer(festivalOfferDTO, "existant@id.com"));
		Assertions.assertEquals("Service.PRODUCT_ALREADY_IN_FESTIVAL", e.getMessage());
	}
	
	@Test
	void addFestivalOfferSuccessfulTest() throws EKartException {
		FestivalOfferDTO festivalOfferDTO = new FestivalOfferDTO();
		FestivalDTO festivalDTO = new FestivalDTO();
		festivalDTO.setFestivalId(123);
		festivalOfferDTO.setFestival(festivalDTO);
		Festival returnedFestival = new Festival();
		returnedFestival.setFestivalStartsAt(LocalDateTime.now().plusDays(10));
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId(42);
		festivalOfferDTO.setProduct(productDTO);
		
		Product returnedProduct = new Product();
		returnedProduct.setProductId(42);
		
		Seller returnedSeller = new Seller();
		returnedSeller.setProduct(new ArrayList<Product>());
		returnedSeller.getProduct().add(returnedProduct);
		returnedSeller.setFestivalOffers(new ArrayList<FestivalOffer>());
		
		FestivalOffer festivalOffer = new FestivalOffer();
		festivalOffer.setFestival(returnedFestival);
		
		Product anotherProduct = new Product();
		anotherProduct.setProductId(7878);
		festivalOffer.setProduct(anotherProduct);
		festivalOffer.setOfferStatus(OfferStatus.ACTIVE);
		
		returnedSeller.getFestivalOffers().add(festivalOffer);
		
		FestivalOffer returnedFestivalOffer = new FestivalOffer();
		returnedFestivalOffer.setFestivalOfferId(199);
		returnedFestivalOffer.setFestival(returnedFestival);
		returnedFestivalOffer.setProduct(anotherProduct);
		
		Mockito.when(sellerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(returnedSeller));
		Mockito.when(festivalRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(returnedFestival));
		Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(returnedProduct));
		Mockito.when(festivalOfferRepository.save(Mockito.any(FestivalOffer.class))).thenReturn(returnedFestivalOffer);
		
		Integer returnedId = sellerFestivalOffersService.addFestivalOffer(festivalOfferDTO, "existant@id.com").getFestivalOfferId();
		Assertions.assertTrue(returnedId > 0);
	}
}
