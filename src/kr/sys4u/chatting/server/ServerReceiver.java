package kr.sys4u.chatting.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import kr.sys4u.chatting.client.Message;
import kr.sys4u.chatting.server.command.CommandProcessor;
import kr.sys4u.chatting.server.command.DecitionMaker;

public class ServerReceiver implements Runnable {

	private final Socket clientSocket;
	private final AccessedClientRunner runner;
	private final DecitionMaker decitionMaker;

	public ServerReceiver(Socket clientSocket, AccessedClientRunner runner) {
		this.clientSocket = clientSocket;
		this.runner = runner;
		this.decitionMaker = new DecitionMaker(runner);
	}

	@Override
	public void run() {

		String returnMessage = "";
		String time;

		try {
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
			while (true) {
				time = new SimpleDateFormat("mm:ss a").format(new Date());
				Message messageObject = (Message) in.readObject();
				
				if (messageObject.getHasCommand() == true) {

					CommandProcessor processor = decitionMaker.getMap()
							.get(getCommandFromMessage(messageObject.getMessage()));
					processor.process(clientSocket, messageObject);
					continue;

				}
				returnMessage = messageObject.getUserId() + " : " + messageObject.getMessage() + " (" + time + ")";
				new ServerSender(runner).sendAll(returnMessage);
				runner.setUserId(messageObject.getUserId());

			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			// fail to get dataObject from client
		}
	}

	public String getCommandFromMessage(String message) {

		return message.contains("/") ? message.split("/")[0] : message;

	}

}
