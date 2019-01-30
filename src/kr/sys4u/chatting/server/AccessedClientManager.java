package kr.sys4u.chatting.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccessedClientManager {
	
	private final ServerSocket socket;
	private final ExecutorService threadPool;
	private final List<AccessedClientRunner> accessedClientList = new ArrayList<>();
	private final ChattingRoomManager chattingRoomManager;
	

	public AccessedClientManager(ServerSocket socket) {
		this.threadPool = Executors.newFixedThreadPool(10);
		this.socket = socket;
		this.chattingRoomManager = new ChattingRoomManager();
	}
	
	public void execute() throws IOException {
		while (true) {
			Socket clientSocket = socket.accept();
			AccessedClientRunner runner = new AccessedClientRunner(clientSocket, accessedClientList,chattingRoomManager);
			synchronized (accessedClientList) {
				accessedClientList.add(runner);
			}
			threadPool.execute(runner);
		
		}
	}
}