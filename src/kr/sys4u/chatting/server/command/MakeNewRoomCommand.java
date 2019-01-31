package kr.sys4u.chatting.server.command;

import java.net.Socket;

import kr.sys4u.chatting.server.Message;
import kr.sys4u.chatting.server.AccessedClient;
import kr.sys4u.chatting.server.ChattingRoom;

public class MakeNewRoomCommand implements CommandProcessor{
	
	private final AccessedClient runner;
	
	public MakeNewRoomCommand(AccessedClient runner) {
		this.runner = runner;
	}

	@Override
	public void process(Socket socket, Message message) {
		ChattingRoom newRoom = new ChattingRoom();
		newRoom.setRoomName(message.getMessage().split("/")[1]);
		synchronized (runner.getRoomManager()) {
			runner.getRoomManager().addRoom(newRoom);
		}
		
	}

}
