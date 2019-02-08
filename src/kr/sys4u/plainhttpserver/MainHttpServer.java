package kr.sys4u.plainhttpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kr.sys4u.plainhttpserver.exception.CanNotConnectToClientException;

public class MainHttpServer {

	private final static int PORT = 1989;

	private boolean initialized;
	private ServerSocket serverSocket;
	private final ExecutorService executorService;

	public MainHttpServer() {
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
				RunnableHttpServer thread = new RunnableHttpServer(serverSocket.accept());
				System.out.println("client connected");
				executorService.execute(thread);
				
			} catch (IOException e) {
				throw new CanNotConnectToClientException();
			}
			if(serverSocket.isClosed()) {
				close();
			}
		}
	}

	public void close() throws IOException {
		
		if (!initialized) {
			initilize();
		}
		
		serverSocket.close();
	}

	public static void main(String[] args) throws IOException {

		MainHttpServer server = new MainHttpServer();
		server.initilize();
		server.execute();
		

	}
}
