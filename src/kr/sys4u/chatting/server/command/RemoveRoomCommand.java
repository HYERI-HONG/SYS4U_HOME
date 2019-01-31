package kr.sys4u.chatting.server.command;

import java.net.Socket;
import kr.sys4u.chatting.server.Message;
import kr.sys4u.chatting.server.*;

public class RemoveRoomCommand implements CommandProcessor{

	private final AccessedClient runner;
	
	public RemoveRoomCommand(AccessedClient runner) {
		this.runner = runner;
	}

	@Override
	public void process(Socket socket, Message message) {
		synchronized (runner) {
			ChattingRoom room = (runner.getRoomManager()).findRoom(message.getMessage().split("/")[1]);
			runner.getRoomManager().removeRoom(room);
		}
	}
}
