package kr.sys4u.thread3_assignment2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CallableUniqueNumberExecutor {

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

	public void execute(List<CallableRandomNumberAdder> generators) throws Exception {

		
		generators.forEach(generator -> {
			Future<Integer> future = null;
			try {
				do {
					future = executor.submit(generator);
				} while (generated.contains(future.get()));
				generated.add(future.get());

			} catch (Exception e) {

			}
		});
	}

	public static void main(String[] args) throws Exception {

		final List<CallableRandomNumberAdder> generators = new ArrayList<>();
		IntStream.range(0, 100).forEach(i -> generators.add(new CallableRandomNumberAdder()));

		CallableUniqueNumberExecutor callableUniqueNumberExecutor = new CallableUniqueNumberExecutor();
		callableUniqueNumberExecutor.init();

		long startTime = System.nanoTime();
		callableUniqueNumberExecutor.execute(generators);
		System.out.println("Elapsted time = " + (System.nanoTime() - startTime) / 1000_000 + "ms");

		callableUniqueNumberExecutor.shutdown();

		// sorting
		Collections.sort(callableUniqueNumberExecutor.generated);

		// printing
		callableUniqueNumberExecutor.generated.stream().forEach(System.out::println);
		
		System.out.println(System.currentTimeMillis()*1000);
		System.out.println(System.nanoTime());

	}
}
