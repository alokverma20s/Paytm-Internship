package com.reactive.app;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

@SpringBootTest
class ReactiveProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void workingWithMono() throws InterruptedException {
		// Mono -> publisher that have 0..1 items
		// created mono
		// Mono<Object> errorMono = Mono.error(new RuntimeException("Error!!"));
		// Mono<Object> m1 = Mono
		// .just("Learning Reactive Programming")
		// .log().then(errorMono);
		// // Consumeing the mono by subscribing
		// m1.subscribe(System.out::println);

		// errorMono.subscribe(System.out::println);

		Mono<String> m1 = Mono.just("This is MONO 1");
		Mono<String> m2 = Mono.just("This is MONO 2");
		Mono<Integer> m3 = Mono.just(1343);

		// Mono<Tuple2<String, String>> combinedData = Mono.zip(m1, m2);
		// Mono<Tuple2<Tuple2<String, String>, Integer>> m4 = combinedData.zipWith(m3);

		// m4.subscribe(data ->{
		// System.out.println(data);
		// });

		// Mono<String> resultMapMono = m1.map(item -> item.toUpperCase());
		// Mono<String[]> resultFlatMapMono = m1.flatMap(item ->
		// Mono.just(item.split("")));
		// resultMapMono.subscribe(System.out::println);
		// resultFlatMapMono.subscribe(data ->{
		// for(String item : data){
		// System.out.println(item);
		// }
		// });

		// System.out.println("Using `flatMapMany`");
		// Flux<String> flatMapMany = m1.flatMapMany(item -> Flux.just(item.split("
		// "))).log();
		// flatMapMany.subscribe(System.out::println);

		System.out.println("Currently running thread is : " + Thread.currentThread().getName());
		Flux<String> concatWith = m1.concatWith(m2).delayElements(Duration.ofMillis(2000));
		concatWith.subscribe(data -> {
			System.out.println("Flux thread is : " + Thread.currentThread().getName());
			System.out.println(data);
		});
		Thread.sleep(4000);
		System.out.println("Main thread terminated");
	}

}
