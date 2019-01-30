package kr.sys4u.infiniteloop;

public class ChangeLoop implements Runnable {

	private Loop loop;

	public ChangeLoop(Loop loop) {
		this.loop = loop;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(10000L);
			loop.running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
