package kr.sys4u.chatting.server.command;

import java.util.HashMap;
import java.util.Map;

import kr.sys4u.chatting.server.AccessedClientRunner;

public class DecitionMaker {


	private Map<String, CommandProcessor> map = new HashMap<>();
	

	public DecitionMaker(AccessedClientRunner runner) {
		
		map.put("roomlist", new ShowAllRoomListCommand(runner));
		map.put("makeroom", new MakeNewRoomCommand(runner));
		map.put("joinroom", new JoinTheRoomCommand(runner));
		map.put("userlist", new ShowAllUserListCommand(runner));
		map.put("exitroom", new ExitTheRoomCommand(runner));
	}

	public Map<String, CommandProcessor> getMap() {
		return map;
	}
	
	
}
