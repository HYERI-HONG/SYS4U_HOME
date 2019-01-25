package kr.sys4u.thread1_nopool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.sys4u.thread1_nopool.RandomIntThread;

public class RandomIntListMain {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		List<Integer> randomIntList = new ArrayList<>();
		
		for(int i=0; i<100; i++) {
			RandomIntThread r = new RandomIntThread();
			Thread thread = new Thread(r);
			thread.start();
			thread.join();
			randomIntList.add(r.getNum());
			
		}
		Collections.sort(randomIntList);
		
		for(int i : randomIntList) {
			System.out.println("N : "+i);	
		}
	}
}
