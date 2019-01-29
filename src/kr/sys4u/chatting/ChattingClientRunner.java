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

	final List<ChattingClientRunner> threads;
	private Socket clientSocket;
	private String userId, content;
	private int option;
	private ExecutorService executorService;

	public ChattingClientRunner(Socket clientSocket, List<ChattingClientRunner> runners, ExecutorService e) {

		this.clientSocket = clientSocket;
		this.threads = runners;
		this.executorService = e;

	}

	@Override
	public void run() {

		while (true) {
			receive();
			//decideAction();
			
		}

	}

	private void decideAction() {

	}

	private void broadcast() {

		String time = new SimpleDateFormat("HH:mm").format(new Date());
		String data = userId + " : " + content + " (" + time + ")";
		System.out.println("[server]broadCast할 정보 :: " + content);

		
		synchronized (threads) {

			for (ChattingClientRunner thread : threads) {
				thread.send(data);
			}
		}
	}

	private void receive() {
		try{
			
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
			Message message = (Message) in.readObject();

			userId = message.getUserId();
			content = message.getContent();
			option = message.getOption();
			System.out.println("[server] 입력 아이디 :: " + userId);
			System.out.println("[server] 입력 메세지 :: " + content);
			System.out.println("[server] 입력 옵션 :: " + option);

		} catch (IOException | ClassNotFoundException e) {

		}finally {
			broadcast();
		}

	}

	private void send(String message) {
		try {
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

			out.writeUTF(message);
			out.flush();

		} catch (IOException e) {

		}
	}

}
