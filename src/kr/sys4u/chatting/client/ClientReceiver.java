package kr.sys4u.chatting.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientReceiver implements Runnable {
	private final Socket socket;

	public ClientReceiver(Socket socket) {
		this.socket = socket;

	}

	@Override
	public void run() {

		try (DataInputStream in = new DataInputStream(socket.getInputStream());) {
			while(true) {
				System.out.println(in.readUTF());
			}
				
		} catch (IOException e) {
			//fail to get dataObject from server
		}

	}

}
