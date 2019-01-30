package kr.sys4u.thread3_assignment2;

import java.util.Random;
import java.util.concurrent.Callable;

public class CallableRandomNumberAdder implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		Thread.sleep(10L);	
		return new Random().nextInt(100);
	}

}
