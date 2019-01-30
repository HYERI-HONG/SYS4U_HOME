package kr.sys4u.thread3_assignment2;

import java.util.List;
import java.util.Random;

public class RunnableRandomNumberAdder implements Runnable{

	private List<Integer> randomIntList;

	public RunnableRandomNumberAdder(List<Integer> randomIntList) {
		this.randomIntList = randomIntList;
	}

	@Override
	public void run() {
		
		try {
			Thread.sleep(10L);
		} catch (InterruptedException e) {
		}
		
		while(true) {
			int randomNumber = new Random().nextInt(100);
			synchronized (randomIntList) {
				if(!randomIntList.contains(randomNumber)) {
					randomIntList.add(randomNumber);
				}
			}
		}
	}
}
