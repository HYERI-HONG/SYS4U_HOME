package kr.sys4u.chatting.client;

import java.io.IOException;
import java.net.Socket;


public class ClientMain {

	private final String userId;
	private final int port;
	private final String address;

	private boolean initialized;
	private Socket socket;

	public ClientMain(int port, String address, String userId) {

		if (userId == null && address == null && userId == null) {
			throw new IllegalArgumentException("Entered Wrong User ID, Port Number or IP Address  ");
		}

		this.port = port;
		this.address = address;
		this.userId = userId;
		this.initialized = false;
	}

	public void initialize() {
		if (initialized) {
			return;
		}

		try {
			socket = new Socket(address, port);
		} catch (Exception e) {
			// fail to connect server on this port and address
		}
	}

	public void execute() {

		new Thread(new ClientReceiver(socket)).start();
		new Thread(new ClientSender(socket, userId)).start();

	}

	public void close() throws IOException {

		socket.close();
	}

	public static void main(String[] args) throws IOException {

		int port = 9000;
		String address = "127.0.0.1";
		String user1 = "hong";

		ClientMain chattingClient = new ClientMain(port, address, user1);
		chattingClient.initialize();
		chattingClient.execute();
		//chattingClient.close();
	}
}
