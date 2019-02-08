package kr.sys4u.plainhttpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HttpRequestReceiver {

	private String request;
	private String statusLine;
	private String header;
	private String body;

	public HttpRequestReceiver(Socket socket) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.statusLine = in.readLine();
		
		//추가 수정 필요
		this.request = null;
		this.header = null;
		this.body = null;
		
	}

	public String getRequest() {
		return request;
	}

	public String getStatusLine() {
		return statusLine;
	}

	public String getHeader() {
		return header;
	}

	public String getBody() {
		return body;
	}
	
}
