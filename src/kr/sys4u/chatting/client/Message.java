package kr.sys4u.chatting.client;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 9015857968867288444L;
	private String message;
	private String userId;
	private boolean hasCommand;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean getHasCommand() {
		return hasCommand;
	}

	public void setHasCommand(boolean hasCommand) {
		this.hasCommand = hasCommand;
	}


}
