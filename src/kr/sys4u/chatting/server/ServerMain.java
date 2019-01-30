package kr.sys4u.chatting.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {

	private int port;
	private boolean initialized;
	ServerSocket serverSocket;
	ExecutorService threadPool;

	public final List<AccessedClientRunner> accessedClientList = new ArrayList<>();

	public ServerMain(int port) {

		if (port < 1024) {
			throw new IllegalArgumentException("Long Port Number was entered");
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
			Socket clientSocket = serverSocket.accept();
			AccessedClientRunner runner = new AccessedClientRunner(clientSocket, accessedClientList);
			synchronized (accessedClientList) {
				accessedClientList.add(runner);
			}
			threadPool.execute(runner);
		
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
		ServerMain chattingServer = new ServerMain(port);
		chattingServer.initialize();
		chattingServer.execute();
	}
}
