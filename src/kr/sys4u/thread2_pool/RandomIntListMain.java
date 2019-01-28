package kr.sys4u.thread2_pool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kr.sys4u.thread2_nopool.RandomIntThread;

public class RandomIntListMain {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		List<Integer> randomIntList = new ArrayList<>();
		ExecutorService threadPool = Executors.newCachedThreadPool();
		
		for(int i=0; i<100; i++) {
			threadPool.execute(new RandomIntThread(randomIntList));
		}
		
		while(true) {
			if(randomIntList.size()==100) {
				Collections.sort(randomIntList);
				for(int i : randomIntList) {
					System.out.println("N : "+i);	
				}
				break;
			}
			
		}
		threadPool.shutdown();
	}
}
