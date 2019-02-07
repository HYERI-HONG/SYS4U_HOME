package kr.sys4u.httpsearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * client ID : sTOqHrU0IjN3pW0ctbmb 
 * client Secret : zE4e_zAxTa
 */
public class HttpUrlConnectionClient {

	public static void main(String[] args) {

		String clientId = "sTOqHrU0IjN3pW0ctbmb";
		String clientSecret = "zE4e_zAxTa";

		try {

			String word = URLEncoder.encode("설날", "UTF-8");
			String apiUrl = "https://openapi.naver.com/v1/search/news?query=" + word;
			URL url = new URL(apiUrl);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("X-Naver-Client-Id", clientId);
			connection.setRequestProperty("X-Naver-Client-Secret", clientSecret);

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
