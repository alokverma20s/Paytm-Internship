package com.reactive.app.services.impl;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.reactive.app.entities.Book;
import com.reactive.app.repositories.BookRepository;
import com.reactive.app.services.BookServices;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServicesImpl implements BookServices {

  private final BookRepository bookRepository;

  public BookServicesImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public Mono<Book> createBook(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Flux<Book> getAll() {
    return bookRepository.findAll();
        // .delayElements(Duration.ofSeconds(2))
        // .log();
  }

  @Override
  public Mono<Book> getById(Integer id) {
    return bookRepository.findById(id);
  }

  @Override
  public Mono<Book> updateBook(Integer id, Book book) {
    return bookRepository.findById(id)
        .flatMap(existingBook -> {
          existingBook.setName(book.getName());
          existingBook.setDescription(book.getDescription());
          existingBook.setPublisher(book.getPublisher());
          existingBook.setAuthor(book.getAuthor());
          return bookRepository.save(existingBook);
        });
  }

  @Override
  public Mono<Void> deleteBook(Integer id) {
    return bookRepository.findById(id)
        .flatMap(book -> bookRepository.delete(book));
  }

  @Override
  public Flux<Book> search(String query) {
    return bookRepository.findByNameContainingIgnoreCase(query);
  }

}
