package kr.sys4u.plainhttpserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class PlainHttpThread implements Runnable {

	private final static String WEB_ROOT = "C:/Users/notebiz7750/Documents/test";
	private final Socket socket;

	public PlainHttpThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String url = in.readLine().split(" ")[1];
			System.out.println(url);
			
			String fileName = url.substring(url.lastIndexOf("/"));
			String[] fileNameAndExtension = fileName.split("\\.");
			boolean hasExtension = fileNameAndExtension.length >= 2;
			
			String extension = null;
			if(hasExtension) {
				extension =  fileNameAndExtension[fileNameAndExtension.length-1];
			}
			
			if(!hasExtension || extension.equals("html")) {
				sendHtmlFile(WEB_ROOT+url);
			}else if(extension.equals("jpg")) {
				sendImageFile(WEB_ROOT+url);
			}
			
			//기능 분리 - 파일을 읽는놈, url을 파싱하는놈 - commandlineparser
			//해당 파일이 없을 경우 처리 필요
			in.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendHtmlFile(String path) {

		File file = new File(path);
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {

			String body = "";
			String line = null;
			while ((line = reader.readLine()) != null) {
				body += line;
			}
			
			String response = "HTTP/1.1 200 OK\r\n" 
								+ "Content-Type: text/html; charset=UTF-8\r\n" 
								+ "Content-Length: " + body.getBytes().length
								+ "\r\n\r\n" + body;
			out.write(response);
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendImageFile(String path) {

		File file = new File(path);
		
		byte[] fileData = new byte[8192];

		try (FileInputStream in = new FileInputStream(file);
				PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
				OutputStream out = socket.getOutputStream();){
			
			String response = "HTTP/1.1 200 OK\r\n" 
					+ "Content-Type: image/jpg\r\n" 
					+ "Content-Length: " +file.length()
					+ "\r\n\r\n";
			
			writer.write(response);
			writer.flush();
			
			
			int readData = 0;
			while ((readData = in.read(fileData))!= -1) {
				
				out.write(fileData, 0, readData);
				out.flush();
			}

		} catch (FileNotFoundException e) {

		} catch (IOException e1) {

		}
	}

}
