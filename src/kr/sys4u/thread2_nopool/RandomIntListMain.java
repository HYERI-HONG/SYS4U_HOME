package kr.sys4u.thread2_nopool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.sys4u.thread2_nopool.RandomIntThread;

public class RandomIntListMain {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		List<Integer> randomIntList = new ArrayList<>();
		
		for(int i=0; i<100; i++) {
			Thread thread = new Thread(new RandomIntThread(randomIntList));
			thread.start();	
		}
		while(true) {
			if(Thread.activeCount()==1) {
				Collections.sort(randomIntList);
				for(int i : randomIntList) {
					System.out.println("N : "+i);	
				}
				break;
			}
			
		}
	}
}
