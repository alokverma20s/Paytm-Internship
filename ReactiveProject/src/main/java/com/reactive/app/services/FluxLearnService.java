package com.reactive.app.services;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class FluxLearnService {
  private static final Logger LOGGER = LoggerFactory.getLogger(FluxLearnService.class);

  public Flux<String> getFlux() {
    LOGGER.info("Flux testing service");

    return Flux.just("Alok", "Ankit", "Abhay", "Ajay");
  }

  public Flux<String> fruitsFlux() {
    List<String> fruitsNames = List.of("Mango", "Apple", "Grapes");
    return Flux.fromIterable(fruitsNames);
  }

  public Flux<Void> getEmpyFlux() {
    return Flux.empty();
  }

  public Flux<String> mapExampleFlux() {
    Flux<String> capName = getFlux().map(String::toUpperCase);
    return capName;
  }

  public Flux<String> filterExampleFlux() {
    Flux<String> filteredName = getFlux().filter(name -> name.length() > 4);
    return filteredName;
  }

  public Flux<String> flatExampleFlux() {
    return getFlux()
        .flatMap(name -> Flux.just(name.split("")))
        .delayElements(Duration.ofSeconds(1));
  }

  Function<Flux<String>, Flux<String>> funInterface = (name) -> name.map(String::toUpperCase);

  public Flux<String> funInterfaceExampleFlux() {
    return getFlux().transform(funInterface);
  }

  // defaultIfEmpty & switchIfEmpty
  public Flux<String> ifExample(int length) {
    return getFlux()
        .filter(name -> name.length() > length)
        // .defaultIfEmpty("No Name Available greater than " + length)
        .switchIfEmpty(fruitsFlux())
        .log();
  }

  public Flux<String> concatExample() {
    // return Flux.concat(getFlux(), fruitsFlux());
    return getFlux().concatWith(fruitsFlux());
  }

  public Flux<String> repeatExample() {
    return getFlux().repeat(3);
  }

  public Flux<String> mergeExample() {
    return Flux.merge(getFlux(), fruitsFlux());
  }

  public Flux<String> zipExample() {
    return Flux.zip(getFlux(), Flux.just(123, 23, 423, 67), (first, second) -> {
      return first + ": " + second;
    });
  }

  public Flux<String> zipWithExample() {
    return getFlux().zipWith(fruitsFlux(), (first, second) -> {
      return first + ": " + second;
    });
  }

  public Flux<String> sideEffect() {
    return getFlux()
        .doOnNext(data -> {
          System.out.println(data + " On next");
        })
        .doOnSubscribe(data -> {
          System.out.println(data + ": On Subscribe");
        })
        .doOnEach(data -> {
          System.out.println(data + ": On OnEach");
        })
        .doOnComplete(()-> System.out.println("Completed"));
  }
}
