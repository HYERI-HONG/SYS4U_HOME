package kr.sys4u.chatting;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChattingServer {

	private int port;
	private boolean initialized;
	ServerSocket serverSocket;
	ExecutorService threadPool;

	final List<ChattingClientRunner> accessedClientList = new ArrayList<>();

	public ChattingServer(int port) {

		if (port < 1024) {
			throw new IllegalArgumentException("[Server] Long Port Number was entered");
		}
		this.port = port;
		this.initialized = false;
	}

	private void initialize() {
		if (initialized) {
			return;
		}
		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// fail to open a socket
		}
		this.threadPool = Executors.newFixedThreadPool(10);
		initialized = true;

	}

	private void execute() throws IOException {
		if (!initialized) {
			initialize();
		}
		
		while (true) {
			System.out.println("[Server] Watting...");
			ChattingClientRunner runner = new ChattingClientRunner(serverSocket.accept(), accessedClientList,threadPool);
			accessedClientList.add(runner);
			threadPool.execute(runner);
			if (serverSocket.isClosed()) {
				close();
			}
		
		}
	}

	private void close() throws IOException {
		if (!initialized) {
			initialize();
		}
		serverSocket.close();
		System.out.println("[Server] Close the Server");
	}

	public static void main(String[] args) throws IOException {

		int port = 9000;

		ChattingServer chattingServer = new ChattingServer(port);
		chattingServer.initialize();
		chattingServer.execute();
	}
}
