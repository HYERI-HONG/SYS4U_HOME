package kr.sys4u.thread2_pool;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import kr.sys4u.thread1_pool.RandomIntThread;

public class RandomIntListMain {
	
	public static void main(String[] args) throws Exception {
		
		List<Integer> randomIntList = new ArrayList<>();
		
		for(int i=0; i<100; i++) {
			ExecutorService threadPool = Executors.newCachedThreadPool();
			
			Future<Integer> future = (Future<Integer>) threadPool.submit(new RandomIntThread());
			randomIntList.add(future.get());
		}
		
		Collections.sort(randomIntList);
		for(int i : randomIntList) {
			System.out.println("N : "+i);	
		}
	}
}
