package com.reactive.app.services;

import com.reactive.app.entities.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookServices {
  public Mono<Book> createBook(Book book);

  public Flux<Book> getAll();

  public Mono<Book> getById(Integer id);

  public Mono<Book> updateBook(Integer id, Book book);

  public Mono<Void> deleteBook(Integer id);

  public Flux<Book> search(String query);
}
