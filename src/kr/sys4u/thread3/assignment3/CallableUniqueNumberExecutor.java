package kr.sys4u.thread3.assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableUniqueNumberExecutor {

	private ExecutorService executor;
	private Set<Integer> generated = new TreeSet<>();

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

	public void execute() throws Exception {

		while (generated.size() < 100) {
			List<Future<Integer>> futures = new ArrayList<>();

			for (int i = 0; i < 50; i++) {

				futures.add(executor.submit(new CallableRandomNumberAdder()));
			}

			for (Future<Integer> future : futures) {

				if (generated.size() >= 100) {
					break;
				}
				try {
					generated.add(future.get());
				} catch (Exception e) {
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {

		CallableUniqueNumberExecutor callableUniqueNumberExecutor = new CallableUniqueNumberExecutor();
		callableUniqueNumberExecutor.init();

		long startTime = System.nanoTime();
		callableUniqueNumberExecutor.execute();
		System.out.println("Elapsted time = " + (System.nanoTime() - startTime) / 1000_000 + "ms");
		callableUniqueNumberExecutor.shutdown();

		callableUniqueNumberExecutor.generated.stream().forEach(System.out::println);

	}
}
