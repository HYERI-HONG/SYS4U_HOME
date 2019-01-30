package kr.sys4u.infiniteloop;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loop {

	ExecutorService executorService = Executors.newFixedThreadPool(10);
	public boolean running = true;

	public void start() {
		executorService.submit(new ChangeLoop(this));
		while (running) {
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("작동");
		}
		executorService.shutdown();
	}

	public static void main(String[] args) throws InterruptedException {
		Loop loop = new Loop();
		loop.start();
	}
}
