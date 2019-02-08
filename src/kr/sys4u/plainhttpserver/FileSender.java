package kr.sys4u.plainhttpserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import kr.sys4u.plainhttpserver.exception.CanNotReadFileException;

public class FileSender {

	private final Socket socket;
	
	public FileSender(Socket socket) {
		this.socket = socket;
	}
	public void send(File file) {

		try(FileInputStream in = new FileInputStream(file);
				OutputStream out = socket.getOutputStream();) {
			
			byte[] data = new byte[8192];
			int count = 0;
			while ((count = in.read(data)) != -1) {

				out.write(data, 0, count);
				out.flush();
			}
		} catch (IOException e) {
			throw new CanNotReadFileException(e);
			
		}
		
		
		
	}

}
