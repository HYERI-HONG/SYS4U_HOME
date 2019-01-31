package kr.sys4u.chatting.server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccessedClientRunner implements Runnable {

	private List<Socket> accessedClientsSocketList = new ArrayList<>();
	private Socket clientSocket;
	private ExecutorService executorService;
	private ChattingRoomManager roomManager;
	
	private String joinRoomName;
	private String userId;

	public AccessedClientRunner(Socket clientSocket, List<Socket> accessedClientsSocketList,
			ChattingRoomManager roomManager) throws IOException {

		this.clientSocket = clientSocket;
		this.accessedClientsSocketList = accessedClientsSocketList;
		this.executorService = Executors.newFixedThreadPool(10);
		this.roomManager = roomManager;
		this.joinRoomName = "home";
	}

	@Override
	public void run() {

		new ServerSender(this).send(clientSocket,"-----------Welcome to Chatting Program-----------");
		executorService.submit(new ServerReceiver(clientSocket, this));

	}

	public ChattingRoomManager getRoomManager() {
		return roomManager;
	}

	public List<Socket> getAccessedClientsSocketList() {
		return accessedClientsSocketList;
	}

	public String getJoinRoomName() {
		return joinRoomName;
	}

	public void setJoinRoomName(String joinRoomName) {
		this.joinRoomName = joinRoomName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
