package com.bookstoreapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstoreapi.dto.BookDTO;
import com.bookstoreapi.model.Book;
import com.bookstoreapi.repository.BookRepository;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	private BookRepository bookRepository;

	private final Counter bookCreationCounter;

	@Autowired
	public BookServiceImpl(MeterRegistry meterRegistry, BookRepository bookRepository) {
		this.bookCreationCounter = meterRegistry.counter("books_created_total", "type", "book");
		this.bookRepository = bookRepository;
	}

	@Override
	public BookDTO createBook(BookDTO bookDTO) {

		Optional<Book> existingBook = bookRepository.findById(bookDTO.getId());

		if (!existingBook.isPresent()) {
			Book book = new Book();
			book.setTitle(bookDTO.getTitle());
			book.setAuthor(bookDTO.getAuthor());
			book.setPrice(bookDTO.getPrice());
			book.setIsbn(bookDTO.getIsbn());
			Book savedBook = bookRepository.save(book);
			bookCreationCounter.increment(); 
			return convertToDTO(savedBook);
		} else {
			Book book = existingBook.get();
			book.setTitle(bookDTO.getTitle());
			book.setAuthor(bookDTO.getAuthor());
			book.setPrice(bookDTO.getPrice());
			book.setIsbn(bookDTO.getIsbn());
			Book savedBook = bookRepository.save(book);
			bookCreationCounter.increment(); 
			return convertToDTO(savedBook);
		}
	}

	@Override
	public Optional<BookDTO> getBookById(Long id) {
		return bookRepository.findById(id).map(this::convertToDTO);
	}

	@Override
	public List<BookDTO> getAllBooks() {
		return bookRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public BookDTO updateBook(Long id, BookDTO bookDTO) {
		Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setPrice(bookDTO.getPrice());
		book.setIsbn(bookDTO.getIsbn());
		Book updatedBook = bookRepository.save(book);
		return convertToDTO(updatedBook);
	}

	@Override
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}

	private BookDTO convertToDTO(Book book) {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(book.getId());
		bookDTO.setTitle(book.getTitle());
		bookDTO.setAuthor(book.getAuthor());
		bookDTO.setPrice(book.getPrice());
		bookDTO.setIsbn(book.getIsbn());
		return bookDTO;
	}
}
