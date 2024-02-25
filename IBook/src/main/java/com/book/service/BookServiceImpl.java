package com.book.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.book.dto.BookDTO;
import com.book.entity.Book;
import com.book.exception.BookException;
import com.book.repository.BookRepository;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	@Override
	public BookDTO getBookDetails(Integer bookId) throws BookException{
		Book book = bookRepository.findById(bookId).orElseThrow(() ->
													new BookException("Service.BOOK_DETAILS_NOT_FOUND"));
		BookDTO bookDTO = new BookDTO();
		bookDTO.setBookId(book.getBookId());
		bookDTO.setTitle(book.getTitle());
		bookDTO.setAuthorName(book.getAuthorName());
		bookDTO.setPublishedYear(book.getPublishedYear());
		bookDTO.setPublisher(book.getPublisher());
		bookDTO.setIsbn(book.getIsbn());
		bookDTO.setPrice(book.getPrice());
		
		return bookDTO;	
	}
	
	@Override
	public void addBook(BookDTO bookDTO) throws BookException {
		Validator.validate(bookDTO);
		if(bookRepository.existsById(bookDTO.getBookId())) throw new BookException("Service.BOOK_ALREADY_PRESENT");
		
		Book book = new Book();
		book.setBookId(bookDTO.getBookId());
		book.setTitle(bookDTO.getTitle());
		book.setAuthorName(bookDTO.getAuthorName());
		book.setPublishedYear(bookDTO.getPublishedYear());
		book.setPublisher(bookDTO.getPublisher());
		book.setIsbn(bookDTO.getIsbn());
		book.setPrice(bookDTO.getPrice());
		
	}
	
	@Override
	public List<BookDTO> getBookByAuthorName(String authorName) throws BookException{
		List<BookDTO> bookList = bookRepository.findByAuthorName(authorName).stream()
											   .map(book ->{
												   BookDTO bookDTO = new BookDTO();
												   bookDTO.setBookId(book.getBookId());
												   bookDTO.setTitle(book.getTitle());
												   bookDTO.setAuthorName(book.getAuthorName());
												   bookDTO.setPublishedYear(book.getPublishedYear());
												   bookDTO.setPublisher(book.getPublisher());
												   bookDTO.setIsbn(book.getIsbn());
												   bookDTO.setPrice(book.getPrice());
												   return bookDTO;
											   }).collect(Collectors.toList());
		if(bookList.isEmpty()) throw new BookException("Service.BOOKS_NOT_FOUND");
		
		return bookList;
	}
	
	@Override
	public List<BookDTO> getBookGreaterThanEqualToPrice(Integer price) throws BookException {
		List<BookDTO> bookList = bookRepository.findByPriceGreaterThanEqual(price).stream()
				.map(book ->{
					BookDTO bookDTO = new BookDTO();
					bookDTO.setBookId(book.getBookId());
					bookDTO.setTitle(book.getTitle());
					bookDTO.setAuthorName(book.getAuthorName());
					bookDTO.setPublishedYear(book.getPublishedYear());
					bookDTO.setPublisher(book.getPublisher());
					bookDTO.setIsbn(book.getIsbn());
					bookDTO.setPrice(book.getPrice());
					return bookDTO;
				}).collect(Collectors.toList());
		if(bookList.isEmpty()) throw new BookException("Serivce.BOOKS_NOT_FOUND");
		
		return bookList;
	}
	
	@Override
	public List<BookDTO> getBookLessThanPrice(Integer price) throws BookException {
		List<BookDTO> bookList = bookRepository.findByPriceGreaterThanEqual(price).stream()
				.map(book ->{
					BookDTO bookDTO = new BookDTO();
					bookDTO.setBookId(book.getBookId());
					bookDTO.setTitle(book.getTitle());
					bookDTO.setAuthorName(book.getAuthorName());
					bookDTO.setPublishedYear(book.getPublishedYear());
					bookDTO.setPublisher(book.getPublisher());
					bookDTO.setIsbn(book.getIsbn());
					bookDTO.setPrice(book.getPrice());
					return bookDTO;
				}).collect(Collectors.toList());
		if(bookList.isEmpty()) throw new BookException("Service.BOOKS_NOT_FOUND");
		
		return bookList;
		
	}
	
	@Override
	public List<BookDTO> bookPublishedBetweenYear(LocalDate startYear, LocalDate endYear) throws BookException {
		List<BookDTO> bookList = bookRepository.booksPublishedBetweenRange(startYear).stream()
				.map(book ->{
					BookDTO bookDTO = new BookDTO();
					bookDTO.setBookId(book.getBookId());
					bookDTO.setTitle(book.getTitle());
					bookDTO.setPublishedYear(book.getPublishedYear());
					bookDTO.setPublisher(book.getPublisher());
					bookDTO.setIsbn(book.getIsbn());
					bookDTO.setPrice(book.getPrice());
					return bookDTO;
				}).collect(Collectors.toList());
		if(bookList.isEmpty()) throw new BookException("Service.BOOKS_NOT_FOUND");
		
		return bookList;
	}
	
	@Override
	public List<BookDTO> bookPublishedAfterYear(LocalDate year) throws BookException {
		List<BookDTO> bookList = bookRepository.findByPublisherYearAfter(year).stream()
				.map(book ->{
					BookDTO bookDTO = new BookDTO();
					bookDTO.setBookId(book.getBookId());
					bookDTO.setTitle(book.getTitle());
					bookDTO.setAuthorName(book.getAuthorName());
					bookDTO.setPublishedYear(book.getPublishedYear());
					bookDTO.setPublisher(book.getPublisher());
					bookDTO.setIsbn(book.getIsbn());
					bookDTO.setPrice(book.getPrice());
				}).collect(Collectors.toList());
		if(bookList == null) throw new BookException("Service.BOOKS_NOT_FOUND");
		return bookList;
	}
	
	@Override
	public List<BookDTO> getBookByAuthorNameAndPublisher(String authorName, String publisher) throws BookException {
		List<BookDTO> bookList = bookRepository.booksByAuthorAndPublisher(authorName, publisher).stream()
				.map(book -> {
					BookDTO bookDTO = new BookDTO();
					bookDTO.setBookId(book.getBookId());
					bookDTO.setTitle(book.getTitle());
					bookDTO.setAuthorName(book.getAuthorName());
					bookDTO.setPublishedYear(book.getPublishedYear());
					bookDTO.setPublisher(book.getPublisher());
					bookDTO.setIsbn(book.getIsbn());
					bookDTO.setPrice(book.getPrice());
					return bookDTO;
				}).collect(Collectors.toList());
		if(bookList.isEmpty()) throw new BookException("Service.BOOKS_NOT_FOUND");
		return bookList;
	}
	
	@Override
	public void updateBookPrice(Integer bookId, Integer price) throws BookException {
		Book book = bookRepository.findById(bookId).orElseThrow(()->
													new BookException("Service.BOOK_NOT_FOUND_IN_DATABASE"));
		book.setPrice(price);
	}
	
	@Override
	public void deleteBook(Integer bookId) throws BookException {
		Book book = bookRepository.findById(bookId).orElseThrow(()->
													new BookException("Service.BOOK_NOT_FOUND_IN_DATABASE"));
		
		bookRepository.deleteById(bookId);		
	}
}
