package kr.sys4u.chatting.server.command;

import java.net.Socket;

import kr.sys4u.chatting.client.Message;
import kr.sys4u.chatting.server.AccessedClientRunner;

public class RemoveRoomCommand implements CommandProcessor{

	private final AccessedClientRunner runner;
	
	public RemoveRoomCommand(AccessedClientRunner runner) {
		this.runner = runner;
	}

	@Override
	public void process(Socket socket, Message message) {
		
		synchronized (runner.getRoomManager()) {
			runner.getRoomManager().getRoomList().remove(runner.getRoomManager().findRoom(message.getMessage()));
		}
	}
}
