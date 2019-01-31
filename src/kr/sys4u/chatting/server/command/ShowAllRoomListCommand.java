package kr.sys4u.chatting.server.command;

import java.net.Socket;

import kr.sys4u.chatting.server.Message;
import kr.sys4u.chatting.server.AccessedClient;
import kr.sys4u.chatting.server.ChattingRoom;
import kr.sys4u.chatting.server.ServerSender;

public class ShowAllRoomListCommand implements CommandProcessor {

	private final AccessedClient runner;

	public ShowAllRoomListCommand(AccessedClient runner) {
		this.runner = runner;
	}

	@Override
	public void process(Socket socket, Message message) {

		String roomList = "< 채팅방 목록 >\n";
		int count = 1;
		for (ChattingRoom room : runner.getRoomManager().getRoomList()) {
			roomList += count + "." + room.getRoomName() + "\n";
			count++;
			
		}
		new ServerSender(runner).send(socket, roomList);
	}

}
