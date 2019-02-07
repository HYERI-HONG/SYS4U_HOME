package kr.sys4u.httpsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlainHttpClient {

	public static void main(String[] args) throws IOException {

		String host = "search.naver.com";
		int port = 80;
		String address ="search.naver?sm=top_hty&fbm=1&ie=utf8&query=설날";

		try(Socket socket = new Socket(host, port);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));){

			out.println("GET /"+address+" HTTP/1.1");
			out.println("Accept: */*");
			out.println("Accpet-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
			out.println("User-Agent: Mozilla/4.0 (compatible; MSIE 8.0; Windows NT6.1; Trident/4.0)");
			out.println("Host: " + host);
			out.println("Connection: close");
			out.println();
			out.flush();

			StringBuilder stringBuilder = new StringBuilder();
			String response;

			while ((response = in.readLine()) != null) {
				stringBuilder.append(response).append("\n");
			}
			System.out.println(stringBuilder.toString());

		} catch (IOException e) {

		}

	}

}
