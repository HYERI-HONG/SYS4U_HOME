package kr.sys4u.chatting.server;

import java.util.ArrayList;
import java.util.List;

public class ChattingRoomManager {

	List<ChattingRoom> roomList;

	public ChattingRoomManager() {
		roomList = new ArrayList<>();

	}

	public void addRoom(ChattingRoom room) {
		roomList.add(room);
	}

	public void removeRoom(ChattingRoom room) {
		if (roomList.contains(room)) {
			roomList.remove(room);
		}
	}

	public ChattingRoom findRoom(String roomName) {

		ChattingRoom foundroom = null;
		for (ChattingRoom room : roomList) {

			if (room.getRoomName().equals(roomName)) {
				foundroom = room;
			}
		}
		return foundroom;
	}

	public List<ChattingRoom> getRoomList() {
		return roomList;
	}

}
