package kr.sys4u.thread1_nopool;

public class RandomIntThread implements Runnable {

	private int num;

	public RandomIntThread() {
		this.num = 0;
	}

	@Override
	public void run() {

		num = (int) (Math.random() * 100);

	}

	public int getNum() {
		return num;
	}

}
