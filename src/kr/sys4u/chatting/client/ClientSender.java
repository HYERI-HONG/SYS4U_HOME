package kr.sys4u.chatting.client;

/**
 * 
 * 메세지 종류
 * 1. Normal Message : 바로 내용 입력
 * 2. Command Message : "command"입력 후 원하는 명령어 입력
 * 
 * 명령어 종류
 * 1. roomlist : 채팅방 목록 보기
 * 2. makeroom/room이름 : 채팅방 만들기
 * 3. joinroom/room이름  : 채팅방 참여하기
 * 4. removeroom/room이름 : 채팅방 삭제하기
 * 5. userlist : 채팅방 참여 사용자 명단 보기
 * 6. exitroom : 채팅방 나가기
 * 7. exit : 접속 종료
 * 
 * */
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSender implements Runnable {

	private final Socket socket;
	private final String userId;

	public ClientSender(Socket socket, String userId) {

		this.socket = socket;
		this.userId = userId;
	}

	@SuppressWarnings("resource")
	@Override
	public void run() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			Message messageObject;
			Scanner scan;
			while (true) {
				scan = new Scanner(System.in);
				String message = scan.nextLine();
				messageObject = new Message();
				messageObject.setUserId(userId);

				if (message.equals("command")) {
					messageObject.setHasCommand(true);
					message = scan.nextLine();
					if (message.equals("exit")) {
						socket.close();
					}
				}
				messageObject.setMessage(message);
				out.writeObject(messageObject);
				out.flush();
			}

		} catch (IOException e) {
			// fail to get dataObject from server
		}

	}

}
