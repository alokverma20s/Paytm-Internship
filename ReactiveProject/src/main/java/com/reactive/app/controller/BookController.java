package com.reactive.app.controller;

import java.time.Duration;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.app.entities.Book;
import com.reactive.app.services.BookServices;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/books")
public class BookController {
  private final BookServices bookServices;

  public BookController(BookServices bookServices) {
    this.bookServices = bookServices;
  }

  // Book CRUD operations here...
  @PostMapping
  public ResponseEntity<Mono<Book>> createBook(@RequestBody Book book){
    Mono<Book> savedBook = bookServices.createBook(book);
    return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
  }

  @GetMapping ResponseEntity<Flux<Book>> getAllBook(){
    Flux<Book> allBooks = bookServices.getAll();
    return new ResponseEntity<>(allBooks, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Mono<Book>> getBookById(@PathVariable String id){
    Mono<Book> bookById = bookServices.getById(Integer.parseInt(id));
    return new ResponseEntity<>(bookById, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Mono<Void>> deleteBook(@PathVariable String id){
    Mono<Void> deletedBook = bookServices.deleteBook(Integer.parseInt(id));
    return new ResponseEntity<>(deletedBook, HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Mono<Book>> updateBook(@PathVariable String id, @RequestBody Book updatedBook){
    Mono<Book> updatedBookMono = bookServices.updateBook(Integer.parseInt(id), updatedBook);
    return new ResponseEntity<>(updatedBookMono, HttpStatus.OK);
  }

  @GetMapping("/search")
  public ResponseEntity<Flux<Book>> searchBooks(@RequestParam String query){
    Flux<Book> searchedBooks = bookServices.search(query);
    return new ResponseEntity<>(searchedBooks, HttpStatus.OK);
  }
}
