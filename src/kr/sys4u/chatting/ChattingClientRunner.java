package kr.sys4u.chatting;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class ChattingClientRunner implements Runnable {

	private ChattingServer server;
	private Socket clientSocket;
	private String userId, content;
	private int option;
	private ObjectInputStream in;
	private DataOutputStream out;


	public ChattingClientRunner(Socket clientSocket, ChattingServer server) throws IOException {

		this.clientSocket = clientSocket;
		this.server = server;
		this.in =  new ObjectInputStream(clientSocket.getInputStream());
		this.out = new DataOutputStream(clientSocket.getOutputStream());

	}

	@Override
	public void run() {
			
			Message message;
			String time = new SimpleDateFormat("HH:mm").format(new Date());
			String sendData;
			
			while (true) {
				
				try {
					message = (Message) in.readObject();
					userId = message.getUserId();
					content = message.getContent();
					option = message.getOption();
					
				} catch (ClassNotFoundException e) {
					
				} catch (IOException e) {
				}
				
				sendData = userId + " : " + content + " (" + time + ")";
				server.broadCast(sendData);
				
			}
	}

	public void send(String message) {
		try {
			
			out.writeUTF(message);
			out.flush();

		} catch (IOException e) {

		}
	}

}
