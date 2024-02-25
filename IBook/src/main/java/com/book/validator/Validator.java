package com.book.validator;

import java.time.LocalDate;

import com.book.dto.BookDTO;
import com.book.exception.BookException;

public class Validator {
	
	public static void validate(BookDTO bookDTO) throws BookException {
		if(!validateYear(bookDTO.getPublishedYear())) throw new BookException("Validator.INVALID_YEAR");
	}
	
	public static boolean validateYear(LocalDate year) {
		return year.isBefore(LocalDate.now());
	}

}
