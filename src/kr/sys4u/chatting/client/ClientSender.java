package kr.sys4u.chatting.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSender implements Runnable{
	
	private final Socket socket;
	private final String userId;
	
	public ClientSender(Socket socket, String userId) {
		
		this.socket = socket;
		this.userId = userId;
	}

	@Override
	public void run() {
		try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				 ) {
			
			Message messageObject;
			Scanner scan;
			while(true) {
				scan = new Scanner(System.in);
				String message = scan.nextLine();
				messageObject = new Message();
				messageObject.setUserId(userId);
				
				if(message.equals("command")) {
					messageObject.setHasCommand(true);
					message = scan.nextLine();
					if(message.equals("exit")) {
						socket.close();
					}
				}
				messageObject.setMessage(message);
				messageObject.setHasCommand(false);
				out.writeObject(messageObject);
				out.flush();
			}
				
				
		} catch (IOException e) {
			//fail to get dataObject from server
		}
		
	}

}
