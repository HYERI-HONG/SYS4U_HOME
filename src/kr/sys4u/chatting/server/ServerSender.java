package kr.sys4u.chatting.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSender {
	
	private final AccessedClientRunner runner;
	
	public ServerSender(AccessedClientRunner runner) {
		this.runner = runner;
	}

	public void send(Socket clientSocket, String message) {
		try {
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
			out.writeUTF(message);
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
			//fail to send the message
		}
	}
	public void sendAll(String message) {
		List<Socket> sendSocketlist =runner.getAccessedClientsSocketList(); 
		if(!runner.getJoinRoomName().equals("home")) {
			sendSocketlist = runner.getRoomManager().findRoom(runner.getJoinRoomName()).getUserList();
		}
		for (Socket clientSocket : sendSocketlist) {
			send(clientSocket, message);
		}
	}
	
	public void sendToRoom(String message, ChattingRoom room) {
		
		for(Socket clientSocket : room.getUserList()) {
			send(clientSocket, message);
		}
		
	}

}
