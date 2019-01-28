package kr.sys4u.thread3_assignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlainThread {
	
	public static void main(String[] args) {
		final int count = 100;
		final List<Integer> generatedIntegers = new ArrayList<Integer>();
		final List<RunnableRandomNumberGenerator> generators = new ArrayList<RunnableRandomNumberGenerator>();
		final List<Thread> threads = new ArrayList<Thread>();
		long startTime = System.nanoTime();
		
		//execute Thread
		for(int i = 0; i<count; i++) {
			RunnableRandomNumberGenerator generator = new RunnableRandomNumberGenerator();
			generators.add(generator);
			Thread thread = new Thread(generator);
			thread.start();
			threads.add(thread);
		}
		
		//watting
		for(Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				
			}
		}
		
		System.out.println("Elapsted time = " + (System.nanoTime() - startTime) / 1000_000 + "ms");
		
		//getting
		for(RunnableRandomNumberGenerator generator : generators) {
			generatedIntegers.add(generator.getNumber());
		}
		
		//sorting
		Collections.sort(generatedIntegers);
		
		//printing
		generatedIntegers.stream().forEach(System.out::println);
		
		
	}

}
