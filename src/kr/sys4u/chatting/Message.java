package kr.sys4u.chatting;

import java.io.Serializable;

/*
 * content : 메세지 내용
 * userId : 사용자 아이디
 * option : 0 - 종료
 *          1 - 메세지 전송
 *          2 - ...
 *          3 - ... 
 * */
public class Message implements Serializable{
	
	private static final long serialVersionUID = 9015857968867288444L;
	private String content;
	private String userId;
	private int option;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

}
