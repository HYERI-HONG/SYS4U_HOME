package kr.sys4u.httpmain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.ssl.SSLSocketFactory;

public class PlainHttpClient {

	public static void main(String[] args) throws UnknownHostException {

		
		String address = "www.naver.com";
		int port = 443;

		try {

			SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			Socket socket = socketFactory.createSocket(InetAddress.getByName(address), port);
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			out.println("GET / HTTP/1.1");
			out.println("Host: www.naver.com");
			out.println("Connection: Close/n");
			out.flush();

			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			while ((line = in.readLine()) != null) {

				stringBuilder.append(line).append("\n");
			}
			System.out.println(stringBuilder.toString());

			in.close();
			out.close();
			socket.close();

		} catch (IOException e) {

		}

	}

}
