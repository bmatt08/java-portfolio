package com.book.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.book.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
	
	List<Book> findByAuthorName(String authorName);
	
	List<Book> findByPriceGreaterThanEqual (Integer price);
	
	List<Book> findByPriceLessThanEqual(Integer price);
	
	List<Book> findByPublisherYearAfter(LocalDate publishedYear);
	
	@Query("select b from Book b where b.publishedYear between :fromYear and :toYear")
	List<Book> booksPublishedBetweenRange(@Param("fromYear") LocalDate fromYear);
	
	List<Book> booksByAuthorAndPublisher(@Param("author") String authorName,
										 @Param("publisher") String publisher);
}
