package com.example.books.web;

import com.example.books.model.dto.BookDTO;
import com.example.books.service.BooksService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    //called on http://localhost:8080/books
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {

        List<BookDTO> allBooks = booksService.getAllBooks();

        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<BookDTO>> getAllBooks(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "3") Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(
                booksService.getBooks(pageNumber, pageSize, sortBy)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long id) {
        Optional<BookDTO> book = booksService.getBookById(id);
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(book.get());
        }
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(
            @PathVariable("id") long bookId,
            @RequestBody BookDTO bookDTO) {

        Long updateBookId = booksService.updateBook(bookDTO.setId(bookId));
        return updateBookId != null ?
                ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    //Create
    @PostMapping()
    public ResponseEntity<BookDTO> create(
            @RequestBody BookDTO bookDTO,
            UriComponentsBuilder builder) {
        long bookId = booksService.createBook(bookDTO);

        URI location = builder.path("/books/{id}").buildAndExpand(bookId).toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable("id") Long id) {
        booksService.deleteBook(id);

        return ResponseEntity.noContent().build();
    }
}
