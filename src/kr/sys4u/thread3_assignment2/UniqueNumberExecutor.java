package kr.sys4u.thread3_assignment2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class UniqueNumberExecutor {

	private ExecutorService executor;
	private List<Integer> generated = new ArrayList<>();

	public void init() {
		executor = Executors.newFixedThreadPool(50);
	}

	public void shutdown() {
		executor.shutdown();
		try {
			executor.awaitTermination(5, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			
		}

	}

	public void execute(List<RunnableRandomNumberAdder> generators) {

		generators.stream().forEach(generator -> executor.execute(generator));

	}
	
	public static void main(String[] args) {

		UniqueNumberExecutor uniqueNumberExecutor = new UniqueNumberExecutor();

		final List<RunnableRandomNumberAdder> generators = new ArrayList<>();
		IntStream.range(0, 100).forEach(i -> generators.add(new RunnableRandomNumberAdder(uniqueNumberExecutor.generated)));

		uniqueNumberExecutor.init();
		long startTime = System.nanoTime();
		uniqueNumberExecutor.execute(generators);
		uniqueNumberExecutor.shutdown();
		System.out.println("Elapsted time = " + (System.nanoTime() - startTime) / 1000_000 + "ms");

		Collections.sort(uniqueNumberExecutor.generated);
		uniqueNumberExecutor.generated.stream().forEach(System.out::println);

	}
}
