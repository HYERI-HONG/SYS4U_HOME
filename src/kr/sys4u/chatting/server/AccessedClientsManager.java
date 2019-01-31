package kr.sys4u.chatting.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccessedClientsManager {
	
	private final ServerSocket socket;
	private final ExecutorService threadPool;
	private final List<Socket> accessedClientList;
	private final ChattingRoomManager chattingRoomManager;
	
	public AccessedClientsManager(ServerSocket socket) {
		this.socket = socket;
		this.threadPool = Executors.newFixedThreadPool(10);
		this.accessedClientList = new ArrayList<>();
		this.chattingRoomManager = new ChattingRoomManager();
		
	}
	
	public void execute() throws IOException {
		
		while (true) {
			
			Socket clientSocket = socket.accept();
			AccessedClient client = new AccessedClient(clientSocket, accessedClientList, chattingRoomManager);
			synchronized (accessedClientList) {
				accessedClientList.add(clientSocket);
			}
			threadPool.execute(client);
		}
	}
}
