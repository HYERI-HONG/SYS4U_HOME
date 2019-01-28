package kr.sys4u.thread3_assignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Generated;

public class RunnableRandomNumberExcutor {

	public static void main(String[] args) {

		int count = 100;
		ExecutorService executor = Executors.newFixedThreadPool(50);

		List<Integer> generatored = new ArrayList<>();
		List<RunnableRandomNumberGenerator> generators = new ArrayList<>();

		long startTime = System.nanoTime();

		for (int i = 0; i < count; i++) {
			RunnableRandomNumberGenerator generator = new RunnableRandomNumberGenerator();
			generators.add(generator);
			executor.execute(generator);
		}
		executor.shutdown();
		try {
			executor.awaitTermination(5 * 60, TimeUnit.SECONDS); // 5분까지는 스레드가 종료되기를 기다림
		} catch (InterruptedException e) {
		}
		
		//getting
		generators.stream().forEach(generator -> generatored.add(generator.getNumber()));

		System.out.println("Elapsted time = " + (System.nanoTime() - startTime) / 1000_000 + "ms");

		// sorting
		Collections.sort(generatored);

		// printing
		generatored.stream().forEach(System.out::println);

	}

}
