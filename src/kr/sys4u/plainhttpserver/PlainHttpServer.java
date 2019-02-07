package kr.sys4u.plainhttpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlainHttpServer {

	private final static int PORT = 1989;

	private boolean initialized;
	private ServerSocket serverSocket;
	private final ExecutorService executorService;

	public PlainHttpServer() {
		this.initialized = false;
		this.executorService = Executors.newFixedThreadPool(10);
	}

	public void initilize() throws IOException {

		if (initialized) {
			return;
		}

		this.serverSocket = new ServerSocket(PORT);
		this.initialized = true;
	}

	public void execute() throws IOException {

		if (!initialized) {
			initilize();
		}
		while (true) {
			
			try {
				PlainHttpThread thread = new PlainHttpThread(serverSocket.accept());
				System.out.println("client connected");
				executorService.execute(thread);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void close() throws IOException {
		serverSocket.close();
	}

	public static void main(String[] args) throws IOException {

		PlainHttpServer server = new PlainHttpServer();
		server.initilize();
		server.execute();
		//server.close();

	}
}
