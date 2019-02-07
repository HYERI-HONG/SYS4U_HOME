package kr.sys4u.httpmain;

import java.io.IOException;
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

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://www.naver.com");
	
		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
			
			@Override
			public String handleResponse( final HttpResponse response) throws ClientProtocolException, IOException {
				int status = response.getStatusLine().getStatusCode();
				HttpEntity entity=null;
				if(status==200) {
					entity = response.getEntity();
				}
				return EntityUtils.toString(entity);
			}
		};
		
		String response = httpClient.execute(httpGet,responseHandler);
		System.out.println(response);
		
	}
}
