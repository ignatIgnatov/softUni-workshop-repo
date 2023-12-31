package com.example.books.service.impl;

import com.example.books.model.dto.AuthorDTO;
import com.example.books.model.dto.BookDTO;
import com.example.books.model.entity.AuthorEntity;
import com.example.books.model.entity.BookEntity;
import com.example.books.repository.AuthorRepository;
import com.example.books.repository.BookRepository;
import com.example.books.service.BooksService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BooksServiceImpl implements BooksService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public BooksServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookDTO> getAllBooks() {
      return   bookRepository
                .findAll()
                .stream()
                .map(this::asBook)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> getBookById(Long id) {
        return bookRepository
                .findById(id)
                .map(this::asBook);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public long createBook(BookDTO bookDTO) {

        AuthorEntity author = authorRepository
                .findByName(bookDTO.getAuthor().getName())
                .orElseGet(() -> new AuthorEntity().setName(bookDTO.getAuthor().getName()));

        this.authorRepository.save(author);

        BookEntity newBook = new BookEntity()
                .setAuthor(author)
                .setIsbn(bookDTO.getIsbn())
                .setTitle(bookDTO.getTitle());


        return bookRepository.save(newBook).getId();
    }

    @Override
    public Long updateBook(BookDTO bookDTO) {
        BookEntity bookEntity = bookRepository.findById(bookDTO.getId()).orElse(null);

        if (bookEntity == null) {
            return null;
        }

        AuthorEntity author = authorRepository.findByName(bookDTO.getAuthor().getName())
                .orElseGet(() -> {
                    AuthorEntity newAuthor = new AuthorEntity()
                            .setName(bookDTO.getAuthor().getName());
                    return authorRepository.save(newAuthor);
                });

        bookEntity.setTitle(bookDTO.getTitle())
                .setIsbn(bookEntity.getIsbn())
                .setAuthor(author);

        return bookRepository.save(bookEntity).getId();
    }

    @Override
    public Page<BookDTO> getBooks(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

       return bookRepository
               .findAll(pageable)
               .map(this::asBook);
    }

    private BookDTO asBook(BookEntity book) {
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        AuthorDTO authorDTO = modelMapper.map(book.getAuthor(), AuthorDTO.class);

        bookDTO.setAuthor(authorDTO);
        return bookDTO;
    }
}
