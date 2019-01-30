package kr.sys4u.chatting.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import kr.sys4u.chatting.client.Message;

public class ServerReceiver implements Runnable{
	
	private final Socket clientSocket;
	private final AccessedClientRunner runner;
	
	
	public ServerReceiver(Socket clientSocket, AccessedClientRunner runner) {
		this.clientSocket = clientSocket;
		this.runner = runner;
	}

	@Override
	public void run() {
		
		String returnMessage ="";
		String time;
		
		try(ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());){
			while(true) {
				time = new SimpleDateFormat("mm:ss a").format(new Date());
				Message messageObject = (Message) in.readObject();
				
				if(messageObject.getHasCommand()) {
					new DecitionMaker();
					continue;
					
				}
				returnMessage = messageObject.getUserId()+" : "+ messageObject.getMessage()+ " (" + time + ")";
				runner.broadCast(returnMessage);
				
			}	
		} catch (IOException | ClassNotFoundException e) {
			//fail to get dataObject from client
		}
		
	}

}
