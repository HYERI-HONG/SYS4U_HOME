package kr.sys4u.thread3_assignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CallableRandomNumberExcutor {
	private ExecutorService executor;
	private List<Integer> generated = new ArrayList<>();

	public void init() {
		executor = Executors.newFixedThreadPool(100);
	}

	public void shutdown() {
		executor.shutdown();
		try {
			executor.awaitTermination(5, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			
		}
	}

	public void execute(List<CallableRandomNumberGenerator> generators) {
		long startTime = System.nanoTime();
		List<Future<Integer>> futureIntegers = null;
		
		try {
			futureIntegers = executor.invokeAll(generators, 5, TimeUnit.MINUTES); // ��ü �޴µ� �ɸ��� �ð�)
		} catch (InterruptedException e) {
			
		}
		System.out.println((System.nanoTime() - startTime) / 1000_000 + "ms");
		
		// �� ������ �� ������ �ͺ�
		futureIntegers.stream().forEach(future -> {
			try {
				generated.add(future.get());
			} catch (Exception e) {

			}
		});
		
	}

	public static void main(String[] args) {
				
		
		final List<CallableRandomNumberGenerator> generator = new ArrayList<>();
		
		IntStream.range(0, 100).forEach(i -> generator.add(new CallableRandomNumberGenerator()));
		 
		CallableRandomNumberExcutor callableExecutor = new CallableRandomNumberExcutor();
		callableExecutor.init();
		callableExecutor.execute(generator);
		callableExecutor.shutdown();

		// ��Ű�� ��Ű�� �޾ƾ��� �ް� ��Ű�� �ް� ��Ű�� �ƴϴ�
		
		
		// sorting
		Collections.sort(callableExecutor.generated);

		// printing

		callableExecutor.generated.stream().forEach(System.out::println);
		
	}
}