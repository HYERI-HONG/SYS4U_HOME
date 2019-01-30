package kr.sys4u.chatting.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccessedClientRunner implements Runnable {

	private List<AccessedClientRunner> accessedClientList = new ArrayList<>();
	private Socket clientSocket;
	private DataOutputStream out;
	private ExecutorService executorService;

	public AccessedClientRunner(Socket clientSocket, List<AccessedClientRunner> accessedClientList) throws IOException {

		this.clientSocket = clientSocket;
		this.accessedClientList = accessedClientList;
		this.out = new DataOutputStream(clientSocket.getOutputStream());
		this.executorService = Executors.newFixedThreadPool(10);

	}

	@Override
	public void run() {
		
		executorService.submit(new ServerReceiver(clientSocket,this));
	
	}
	public void broadCast(String message) {
		
		for (AccessedClientRunner client : accessedClientList) {
			client.send(message);
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