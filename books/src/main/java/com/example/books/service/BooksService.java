package com.example.books.service;

import com.example.books.model.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BooksService {

    List<BookDTO> getAllBooks();

    Optional<BookDTO> getBookById(Long id);

    void deleteBook(Long id);

    long createBook(BookDTO bookDTO);

}
