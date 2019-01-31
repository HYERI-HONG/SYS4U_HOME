package kr.sys4u.chatting.server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerMain {

	private final int port;
	private boolean initialized;
	private ServerSocket serverSocket;
	

	public ServerMain(int port) {

		if (port < 1024) {
			throw new IllegalArgumentException("Long Port Number was entered");
		}
		this.port = port;
		this.initialized = false;
	}

	private void initialize() throws IOException {
		if (initialized) {
			return;
		}
		
		this.serverSocket = new ServerSocket(port);
		initialized = true;

	}

	private void execute() throws IOException {
		if (!initialized) {
			initialize();
		}
		new AccessedClientsManager(serverSocket).execute();
		
		if(serverSocket.isClosed()) {
			close();
		}
	}

	private void close() throws IOException {
		if (!initialized) {
			initialize();
		}
		serverSocket.close();
	}

	public static void main(String[] args) throws IOException {

		int port = 9000;
		ServerMain chattingServer = new ServerMain(port);
		chattingServer.initialize();
		chattingServer.execute();
	}
}
