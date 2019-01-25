package kr.sys4u.thread2_nopool;

import java.util.List;

public class RandomIntThread implements Runnable {

	private List<Integer> randomIntList;
	private int num;

	public RandomIntThread(List<Integer> randomIntList) {
		this.randomIntList = randomIntList;
	}

	@Override
	public void run() {
		
		calcUniqueRandomInt((int) (Math.random() * 100));
		
		synchronized (randomIntList) {
			randomIntList.add(num);
		}
	}
	private void calcUniqueRandomInt(int num) {
		
		if (randomIntList.contains(num)) {
			calcUniqueRandomInt((int) (Math.random() * 100));
		}else {
			this.num = num;
		}
		
	}
}
