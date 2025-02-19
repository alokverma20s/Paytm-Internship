package com.reactive.app.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.reactive.app.entities.Book;

import reactor.core.publisher.Flux;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {
  public Flux<Book> findByNameContainingIgnoreCase(String name);

  public Flux<Book> findByAuthorContainingIgnoreCase(String author);

  public Flux<Book> findByPublisherContainingIgnoreCase(String publisher);

  @Query("SELECT * FROM book_details WHERE author = :author")
  public Flux<Book> getAllBooksByAuthor(String author);
}
