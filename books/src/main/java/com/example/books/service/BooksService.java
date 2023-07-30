package com.example.books.service;

import com.example.books.model.dto.BookDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BooksService {

    List<BookDTO> getAllBooks();

    Optional<BookDTO> getBookById(Long id);

    void deleteBook(Long id);

    long createBook(BookDTO bookDTO);

    Long updateBook(BookDTO bookDTO);

    Page<BookDTO> getBooks(int pageNumber, int pageSize, String sortBy);
}
