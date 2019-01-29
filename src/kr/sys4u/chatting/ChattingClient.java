package kr.sys4u.chatting;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChattingClient {

	private final String userId = "User1";
	private int port;
	private String address;
	private boolean initialized;
	private Socket socket;

	public ChattingClient(int port, String address) {
		this.port = port;
		this.address = address;
		this.initialized = false;
	}

	private void initialize() {
		if (initialized) {
			return;
		}

		try {
			socket = new Socket(address, port);
		} catch (Exception e) {

		}
	}

	private void execute() {

		receive();
		send();
	}

	private void close() throws IOException {

		socket.close();
	}

	private void receive() {

		new Thread(() -> {

			try(DataInputStream in = new DataInputStream(socket.getInputStream());) {
				while (true) {
					System.out.println(in.readUTF());
				}
			} catch (IOException e) {

			}
		}).start();
	}

	private void send() {

		new Thread(() -> {

			try(ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());) {
				Scanner scan;
				Message message;
				while (true) {
					scan = new Scanner(System.in);
					String content = scan.nextLine();
					message = new Message();
					message.setContent(content);
					message.setUserId(userId);
					message.setOption(1);
					
					out.writeObject(message);
					out.flush();
				}
				
			} catch (IOException e1) {
				
			}
			
//				if() {
//					scan.close();
//				}
		}).start();

	} 

	public static void main(String[] args) throws IOException {

		int port = 9000;
		String address = "127.0.0.1";

		ChattingClient chattingClient = new ChattingClient(port, address);
		chattingClient.initialize();
		chattingClient.execute();
		//chattingClient.close();
	}

}
