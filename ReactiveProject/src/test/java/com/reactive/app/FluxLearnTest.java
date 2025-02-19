package com.reactive.app;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.reactive.app.services.FluxLearnService;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
public class FluxLearnTest {
  @Autowired
  private FluxLearnService fluxLearnService;

  @Test
  public void simpleFluxTest() {
    Flux<String> nameFlux = fluxLearnService.getFlux().log();
    nameFlux.subscribe(data -> {
      System.out.println(data);
    });
  }

  @Test
  public void fruitFluxTest() {
    Flux<String> fruitFlux = fluxLearnService.fruitsFlux().log();
    fruitFlux.subscribe(data -> {
      System.out.println(data);
    });
  }

  @Test
  public void emptyFluxTest() {
    Flux<Void> emptyFlux = fluxLearnService.getEmpyFlux().log();
    emptyFlux.subscribe();
  }

  @Test
  public void mapExampleFluxTest() {
    Flux<String> mapExampleFlux = fluxLearnService.mapExampleFlux().log();
    StepVerifier.create(mapExampleFlux)
        .expectNext("Alok".toUpperCase(), "Ankit".toUpperCase(), "Abhay".toUpperCase(), "Ajay".toUpperCase())
        .verifyComplete();
  }

  @Test
  public void filterExampleFluxTest() {
    Flux<String> filterFlux = fluxLearnService.filterExampleFlux().log();
    StepVerifier.create(filterFlux)
        .expectNext("Ankit", "Abhay")
        .verifyComplete();
  }

  @Test
  public void flatExampleFluxTest() {
    Flux<String> flatExampleFlux = fluxLearnService.flatExampleFlux().log();
    StepVerifier.create(flatExampleFlux)
        .expectNext("A", "l", "o", "k", "A", "n", "k", "i", "t", "A", "b", "h", "a", "y", "A", "j", "a", "y")
        .verifyComplete();
  }

  @Test
  public void funInterfaceExampleFluxTest() {
    Flux<String> flux = fluxLearnService.funInterfaceExampleFlux().log();
    StepVerifier.create(flux)
        .expectNextCount(4)
        .verifyComplete();
  }

  @Test
  public void ifExampleTest() {
    Flux<String> flux = fluxLearnService.ifExample(8);
    StepVerifier.create(flux)
        .expectNextCount(3)
        .verifyComplete();
  }

  @Test
  public void concatExample() {
    Flux<String> concat = fluxLearnService.concatExample().log();
    StepVerifier.create(concat)
        .expectNextCount(7)
        .verifyComplete();
  }

  @Test
  public void repeatExample() {
    Flux<String> repeat = fluxLearnService.repeatExample().log().delayElements(Duration.ofSeconds(1));
    StepVerifier.create(repeat)
        .expectNextCount(16)
        .verifyComplete();
  }

  @Test
  public void mergeExample() {
    Flux<String> merge = fluxLearnService.mergeExample().log().delayElements(Duration.ofSeconds(1));
    StepVerifier.create(merge)
        .expectNextCount(7)
        .verifyComplete();
  }

  @Test
  public void zipExample() {
    Flux<String> zip = fluxLearnService.zipExample().log();
    StepVerifier.create(zip)
    .expectNextCount(4)
    .verifyComplete();
  }
  @Test
  public void zipWithExample() {
    Flux<String> zip = fluxLearnService.zipWithExample().log();
    StepVerifier.create(zip)
    .expectNextCount(3)
    .verifyComplete();
  }

  @Test
  public void sideEffect() {
    fluxLearnService.sideEffect().subscribe(System.out::println);
  }
}
