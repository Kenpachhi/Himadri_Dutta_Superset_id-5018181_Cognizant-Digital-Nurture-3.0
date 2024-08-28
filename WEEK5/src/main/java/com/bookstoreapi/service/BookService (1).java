package com.bookstoreapi.service;

import com.bookstoreapi.dto.BookDTO;
import java.util.List;
import java.util.Optional;

public interface BookService {
    BookDTO createBook(BookDTO bookDTO);
    Optional<BookDTO> getBookById(Long id); 
    List<BookDTO> getAllBooks();
    BookDTO updateBook(Long id, BookDTO bookDTO);
    void deleteBook(Long id);
}
