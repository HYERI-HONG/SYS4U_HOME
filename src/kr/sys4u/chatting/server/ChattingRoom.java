package kr.sys4u.chatting.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChattingRoom {

	private String roomName;
	private List<String> userIdList;
	private List<Socket> userSocketList;

	public ChattingRoom() {
		this.userIdList = new ArrayList<>();
		this.userSocketList = new ArrayList<>();
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public List<String> getUserIdList() {
		return userIdList;
	}

	public void addUserId(String userId) {
		this.userIdList.add(userId);
	}

	public void removeUserId(String userId) {
		if (userIdList.contains(userId)) {
			userIdList.remove(userId);
		}
	}

	public List<Socket> getUserList() {
		return userSocketList;
	}

	public void addUserSocketList(Socket userSocket) {
		this.userSocketList.add(userSocket);
	}

	public void removeUserSocket(Socket socket) {
		if (userSocketList.contains(socket)) {
			userSocketList.remove(socket);
		}
	}

}
