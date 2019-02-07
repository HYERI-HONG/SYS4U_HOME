package kr.sys4u.httpsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.net.ssl.SSLSocketFactory;

public class PlainHttpClient {

	public static void main(String[] args) throws IOException {

		//String word = "시스포유아이앤씨";
		String address = "openapi.naver.com/v1/search/news?query=설날";
		int port = 80;

		try {

			SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			Socket socket = socketFactory.createSocket(address, port);
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			out.print("GET HTTP/1.1\r\n");
			//out.print("Host: openapi.naver.com/v1/search/news?query=설날\r\n");
			out.print("X-Naver-Client-Id: sTOqHrU0IjN3pW0ctbmb\r\n");
			out.print("X-Naver-Client-Secret: zE4e_zAxTa\n\r\n");
			//out.print("Connection: Close\n\r\n");
			out.flush();

			boolean loop = true;
			StringBuilder stringBuilder = new StringBuilder(8096);
			while (loop) {
				if (in.ready()) {
					int i = 0;
					while (i != -1) {
						i = in.read();
						stringBuilder.append((char) i);
					}
					loop = false;
				}
			}
			System.out.println(stringBuilder.toString());
			socket.close();

		} catch (IOException e) {

		}

	}

}
