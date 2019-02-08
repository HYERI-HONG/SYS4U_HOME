package kr.sys4u.plainhttpserver;

import java.io.IOException;
import java.net.Socket;

public class RunnableHttpServer implements Runnable {

	private final Socket socket;

	public RunnableHttpServer(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			String statusLine = new HttpRequestReceiver(socket).getStatusLine();
			new HttpResponseSender(new StatusLineParser(statusLine),socket).send();
			
			socket.close();
			
		} catch (IOException e) {
			
		}
	}
}
