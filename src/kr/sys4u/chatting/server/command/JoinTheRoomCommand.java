package kr.sys4u.chatting.server.command;

import java.net.Socket;

import kr.sys4u.chatting.server.Message;
import kr.sys4u.chatting.server.AccessedClient;
import kr.sys4u.chatting.server.ChattingRoom;
import kr.sys4u.chatting.server.ServerSender;

public class JoinTheRoomCommand implements CommandProcessor {

	private final AccessedClient runner;

	public JoinTheRoomCommand(AccessedClient runner) {
		this.runner = runner;
	}

	@Override
	public void process(Socket socket, Message message) {
		
		ChattingRoom joinRoom = runner.getRoomManager().findRoom(message.getMessage().split("/")[1]);
		synchronized (joinRoom) {
			joinRoom.addUserSocketList(socket);
			joinRoom.addUserId(message.getUserId());
		}
		
		synchronized (runner) {
			if(runner.getAccessedClientsSocketList().contains(socket)) {
				runner.getAccessedClientsSocketList().remove(socket);
			}
			runner.setJoinRoomName(joinRoom.getRoomName());
		}
		
		ServerSender sender = new ServerSender(runner);
		sender.send(socket, "------------------" + joinRoom.getRoomName() + "------------------\n");
		sender.sendToRoom(message.getUserId() + "님이 입장하셨습니다.", joinRoom);
		
	}

}
