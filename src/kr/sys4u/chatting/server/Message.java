package kr.sys4u.chatting.server;

import java.io.Serializable;

public class Message implements Serializable{
	
	private static final long serialVersionUID = 9015857968867288444L;
	private String message;
	private String userId;
	private boolean hasCommand;
	private String command;

	public Message() {
		hasCommand = false;
	}

	public String getContent() {
		return message;
	}

	public void setContent(String content) {
		this.message = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isHasCommand() {
		return hasCommand;
	}

	public void setHasCommand(boolean hasCommand) {
		this.hasCommand = hasCommand;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
	

}
