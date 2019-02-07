package kr.sys4u.httpsearch;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class ApacheHttpClient {

	public static void main(String[] args) throws IOException {

		String clientId = "sTOqHrU0IjN3pW0ctbmb";
		String clientSecret = "zE4e_zAxTa";

		String word = URLEncoder.encode("시스포유아이앤씨", "UTF-8");
		String uri = "https://openapi.naver.com/v1/search/news?query=" + word;

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);

		httpGet.addHeader("X-Naver-Client-Id", clientId);
		httpGet.addHeader("X-Naver-Client-Secret", clientSecret);

		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

			@Override
			public String handleResponse(HttpResponse response) throws IOException {
				int status = response.getStatusLine().getStatusCode();

				HttpEntity entity = null;
				if (status == 200) {
					entity = response.getEntity();
				}
				return EntityUtils.toString(entity);
			}
		};

		String response = httpClient.execute(httpGet, responseHandler);
		System.out.println(response);

	}
}
