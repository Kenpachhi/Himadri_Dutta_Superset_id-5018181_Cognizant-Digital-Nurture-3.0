package com.bookstoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.bookstoreapi.dto.BookDTO;
import com.bookstoreapi.service.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

	@InjectMocks
	BookController bookController;

	@Mock
	BookServiceImpl bookService;

	@Test
	void getBookByIdTest() {
		HttpHeaders header = new HttpHeaders();
		BookDTO books = new BookDTO();
		books.setId(1l);
		Optional<BookDTO> bookDTO = Optional.of(books);
		Mockito.when(bookService.getBookById(Mockito.anyLong())).thenReturn(bookDTO);
		var response = bookController.getBookById(1l, header);
		assertEquals(1l, response.getBody().getId());
	}

	@Test
	void getAllBooksTest() {
		List<BookDTO> books = new ArrayList<>();
		BookDTO book = new BookDTO();
		book.setId(1l);
		books.add(book);
		Mockito.when(bookService.getAllBooks()).thenReturn(books);
		var response = bookController.getAllBooks();
		assertEquals(1l, response.getBody().get(0).getId());

	}

	@Test
	void updateBookTest() {
		HttpHeaders header = new HttpHeaders();
		BookDTO books = new BookDTO();
		books.setId(1l);
		Mockito.when(bookService.updateBook(Mockito.anyLong(), any())).thenReturn(books);
		var response = bookController.updateBook(1l, books);
		assertEquals(1l, response.getBody().getId());
	}

	@Test
	void createBookTest() {
		HttpHeaders header = new HttpHeaders();
		BookDTO books = new BookDTO();
		books.setId(1l);
		Mockito.when(bookService.createBook(Mockito.any())).thenReturn(books);
		var response = bookController.createBook(books);
		assertEquals(1l, response.getBody().getId());
	}

	@Test
	void deleteBookTest() {
		Mockito.doNothing().when(bookService).deleteBook(Mockito.anyLong());
		var response = bookController.deleteBook(1l);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

}
