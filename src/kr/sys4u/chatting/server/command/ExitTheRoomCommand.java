package kr.sys4u.chatting.server.command;

import java.net.Socket;

import kr.sys4u.chatting.server.Message;
import kr.sys4u.chatting.server.AccessedClient;
import kr.sys4u.chatting.server.ChattingRoom;
import kr.sys4u.chatting.server.ServerSender;

public class ExitTheRoomCommand implements CommandProcessor{
	
	private final AccessedClient runner;
	
	public ExitTheRoomCommand(AccessedClient runner) {
		this.runner = runner;
	}

	@Override
	public void process(Socket socket, Message message) {
		ChattingRoom exitRoom = runner.getRoomManager().findRoom(runner.getJoinRoomName());
		exitRoom.getUserIdList().remove(message.getUserId());
		exitRoom.getUserList().remove(socket);
		
		synchronized (runner) {
			
			runner.getAccessedClientsSocketList().add(socket);
			runner.setJoinRoomName("home");
			
		}
		
		ServerSender sender = new ServerSender(runner);
		sender.sendToRoom(message.getUserId()+"님이 채팅방을 나가셨습니다.", exitRoom);
		sender.send(socket, "------------------home------------------");
		
		
	}

}
