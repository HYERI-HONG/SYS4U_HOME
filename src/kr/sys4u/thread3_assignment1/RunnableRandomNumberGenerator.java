package kr.sys4u.thread3_assignment1;

import java.util.Random;

public class RunnableRandomNumberGenerator implements Runnable{

	private int number;
	
	public int getNumber() {
		return number;
	}

	@Override
	public void run(){
	
		try {
			Thread.sleep(10L);
		} catch (InterruptedException e) {
			//ignored
		}
		number = new Random().nextInt(100);	
	}
}
