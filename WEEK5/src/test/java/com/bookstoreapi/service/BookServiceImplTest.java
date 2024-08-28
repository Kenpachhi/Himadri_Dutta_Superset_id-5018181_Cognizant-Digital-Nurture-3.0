package com.bookstoreapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookstoreapi.dto.BookDTO;
import com.bookstoreapi.model.Book;
import com.bookstoreapi.repository.BookRepository;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

	@Mock
	private BookRepository bookRepository;

	@Mock
	private MeterRegistry meterRegistry;

	@Mock
	private Counter bookCreationCounter;

	@InjectMocks
	private BookServiceImpl bookService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetBookById_Found() {
		Book book = new Book();
		book.setId(1L);
		when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(book));
		Optional<BookDTO> result = bookService.getBookById(1L);
		assertTrue(result.isPresent());
	}

	@Test
	public void testGetBookById_NotFound() {
		when(bookRepository.findById(1L)).thenReturn(Optional.empty());
		Optional<BookDTO> result = bookService.getBookById(1L);
		assertFalse(result.isPresent());
	}

	@Test
	public void testGetAllBooks() {
		List<Book> books = new ArrayList<>();
		Book book = new Book();
		book.setId(1l);
		books.add(book);
		when(bookRepository.findAll()).thenReturn(books);
		List<BookDTO> result = bookService.getAllBooks();
		assertEquals(1, result.size());
	}

	@Test
	public void testUpdateBook() {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(1l);
		Book book = new Book();
		book.setId(1L);
		book.setTitle("Updated Book");
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		when(bookRepository.save(any(Book.class))).thenReturn(book);
		BookDTO updatedBook = bookService.updateBook(1L, bookDTO);
		assertNotNull(updatedBook);
		assertEquals("1", updatedBook.getId().toString());
	}

	@Test
	public void testDeleteBook() {
		doNothing().when(bookRepository).deleteById(1L);
		bookService.deleteBook(1L);
		verify(bookRepository).deleteById(1L);
	}

}
