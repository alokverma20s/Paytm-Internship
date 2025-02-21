package com.reactive.app.services.impl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.app.entities.Book;
import com.reactive.app.repositories.BookRepository;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class BookServicesImplTest {
  private static final Logger Log = LoggerFactory.getLogger(BookServicesImplTest.class);

  @Mock
  private BookRepository bookRepository;

  @InjectMocks
  private BookServicesImpl bookServicesImpl;

  private Book book;

  @BeforeEach
  public void init() {
    Log.info("Initializing Book");
    book = new Book(
        1,
        "Test Book",
        "This is the test book",
        "Test Publisher",
        "Test Author");
  }

  @Test
  void testCreateBook() {
    when(bookRepository.save(book)).thenReturn(Mono.just(book)); // Mock behavior

    Mono<Book> savedBook = bookServicesImpl.createBook(book);

    // Then - Verify using StepVerifier
    StepVerifier.create(savedBook)
        .expectNextMatches(b -> b.getBookId() == 1 &&
            b.getName().equals("Test Book") &&
            b.getDescription().equals("This is the test book") &&
            b.getPublisher().equals("Test Publisher") &&
            b.getAuthor().equals("Test Author"))
        .verifyComplete();

    // Verify the repository method was called once
    verify(bookRepository, times(1)).save(book);
  }

  @Test
  void testDeleteBook() {
    // Mock findById to return a Mono containing the book
    when(bookRepository.findById(book.getBookId())).thenReturn(Mono.just(book));
    
    when(bookRepository.delete(book)).thenReturn(Mono.empty());

    // Execute the method and block to ensure it runs
    bookServicesImpl.deleteBook(1).block();

    // Verify that delete and findById was called once
    verify(bookRepository, times(1)).findById(1);
    verify(bookRepository, times(1)).delete(book);
  }

  @Test
  void testGetAll() {

  }

  @Test
  void testGetById() {

  }

  @Test
  void testSearch() {

  }

  @Test
  void testUpdateBook() {

  }
}
