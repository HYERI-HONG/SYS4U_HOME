package kr.sys4u.plainhttpserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import kr.sys4u.plainhttpserver.exception.NoSuchMIMETypeException;

public class HttpResponseSender {

	//private final static String WEB_ROOT = "C:/Users/notebiz7750/Documents/test/";
	private final static String WEB_ROOT = "C:/Users/hyeri/Documents/test";
	private StatusLineParser parser;
	private final Socket socket;

	public HttpResponseSender(StatusLineParser parser, Socket socket) {

		this.parser = parser;
		this.socket = socket;
	}

	public void send() {
		
		File file = new File(WEB_ROOT + parser.getFileName());
		try (PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			) {
			String mimeType = new MIMETypeManager().getMIMEType(parser.getExtension());
			
			//해당 MIME 타입이 없을 경우 처리 수정 필요
			if(mimeType==null) {
				throw new NoSuchMIMETypeException();
			}
			
			String response =  String.format("HTTP/1.1 200 OK\r\n"
					+ "Content-Type: %s; charset=UTF-8\r\n"
					+ "Content-Length: %d\r\n\r\n", mimeType, file.length());
			
			writer.write(response);
			writer.flush();
			
			new FileSender(socket).send(file);

		} catch (FileNotFoundException e) {

		} catch (IOException e1) {

		}

	}

}
