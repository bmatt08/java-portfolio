package com.book.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Book.bookByAuthorAndPublisher",
			query="select b from Book b where b.authorName = :author and b.publisher = :publisher")


public class Book {
	@Id
	private Integer bookId;
	@Column(name="book_name")
	private String title;
	private String authorName;
	@Column(name="year")
	private LocalDate publishedYear;
	private String publisher;
	private Long isbn;
	private Integer price;
	
	
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public LocalDate getPublishedYear() {
		return publishedYear;
	}
	public void setPublishedYear(LocalDate publishedYear) {
		this.publishedYear = publishedYear;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Long getIsbn() {
		return isbn;
	}
	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", authorName=" + authorName + ", publisher=" + publisher + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(bookId, isbn, price, publishedYear);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Book other = (Book) obj;
		return Objects.equals(bookId, other.bookId) && Objects.equals(isbn, other.isbn)
				&& Objects.equals(price, other.price) && Objects.equals(publishedYear, other.publishedYear);
	}

	
	
}
