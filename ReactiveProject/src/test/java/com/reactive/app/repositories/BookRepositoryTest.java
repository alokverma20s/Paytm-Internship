package com.reactive.app.repositories;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.reactive.app.entities.Book;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
public class BookRepositoryTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(BookRepositoryTest.class);
  @Autowired
  private BookRepository bookRepository;

  @Test
  void testFindByAuthorContainingIgnoreCase() {
    Flux<Book> book = bookRepository.findByAuthorContainingIgnoreCase("Alok");
    StepVerifier.create(book)
        .expectNextCount(2)
        .verifyComplete();
  }

  @Test
  void testFindByNameContainingIgnoreCase() {
    Flux<Book> book = bookRepository.findByNameContainingIgnoreCase("book");
    StepVerifier.create(book)
        .expectNextCount(1)
        .verifyComplete();
  }

  @Test
  void testFindByPublisherContainingIgnoreCase() {
    Flux<Book> books = bookRepository.findByPublisherContainingIgnoreCase("ram");
    StepVerifier.create(books)
        .expectNextCount(1)
        .verifyComplete();
  }

  @Test
  void testGetAllBooksByAuthor() {
    LOGGER.info("GET ALLBooksByAuthor");
    bookRepository.getAllBooksByAuthor("RDJ")
        .log()
        // .subscribe(data -> LOGGER.info(data.getName()));
        .as(StepVerifier::create)
        .expectNextCount(1)
        .verifyComplete();
  }
}
