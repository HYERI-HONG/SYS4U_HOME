package kr.sys4u.chatting.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import kr.sys4u.chatting.client.Message;
import kr.sys4u.chatting.server.command.CommandProcessor;
import kr.sys4u.chatting.server.command.CommandIdentifier;

public class ServerReceiver implements Runnable {

	private final Socket clientSocket;
	private final AccessedClient runner;
	private final CommandIdentifier identifier;

	public ServerReceiver(Socket clientSocket, AccessedClient runner) {
		this.clientSocket = clientSocket;
		this.runner = runner;
		this.identifier = new CommandIdentifier(runner);
	}

	@Override
	public void run() {


		try {
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
			
			String returnMessage = "";
			String time;
			
			while (true) {
				
				Message messageObject = (Message) in.readObject();
				if (messageObject.getHasCommand()) {

					CommandProcessor processor = identifier.getMap()
							.get(getCommandFromMessage(messageObject.getMessage()));
					processor.process(clientSocket, messageObject);
					continue;

				}
				
				time = new SimpleDateFormat("mm:ss a").format(new Date());
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

		String command = message.contains("/") ? message.split("/")[0] : message;
		System.out.println("command : "+command);
		return command;

	}

}
