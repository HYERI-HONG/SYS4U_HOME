package kr.sys4u.httpmain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * client ID : sTOqHrU0IjN3pW0ctbmb 
 * client Secret : zE4e_zAxTa
 */
public class HttpUrlConnectionClient {

	public static void main(String[] args) {

		try {

			String apiURL = "https://www.naver.com";
			URL url = new URL(apiURL);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();
			BufferedReader in;
			
			if (responseCode == 200) {
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			} else {
				in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			}
	
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine).append("\n");
			}
			in.close();
			System.out.println(response.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
