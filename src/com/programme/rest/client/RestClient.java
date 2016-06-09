package com.programme.rest.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.os.AsyncTask;
import android.util.Log;


public class RestClient{

	private static final String GET = "GET";
	private static final String POST = "POST";
	private static final String PUT = "PUT";
	private static final String DELETE = "DELETE";
	private static final String URL = "url";


	public String get(String url) throws ClientProtocolException, IOException{
		
		String jsonString = new String();
		ResponseConverter responseConverter = new ResponseConverter();
		
		HttpClient httpClient = new DefaultHttpClient();
		
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, 20000);
		HttpConnectionParams.setSoTimeout(params, 20000);
		
		HttpResponse httpResponse = httpClient.execute(new HttpGet(url));
		
		InputStream inputStreamResponse = httpResponse.getEntity().getContent();
		
		if(inputStreamResponse!=null){
			jsonString = responseConverter.convertResponseToString(inputStreamResponse);
		}
		
		return jsonString;
	}
	
	public String post(String url) throws ClientProtocolException, IOException{
		
		String jsonString = new String();
		ResponseConverter responseConverter = new ResponseConverter();
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, 20000);
		HttpConnectionParams.setSoTimeout(params, 20000);
		
		HttpResponse httpResponse = httpClient.execute(new HttpPost(url));
		
		InputStream inputStreamResponse = httpResponse.getEntity().getContent();
		
		if(inputStreamResponse!=null){
			jsonString = responseConverter.convertResponseToString(inputStreamResponse);
		}
		
		return jsonString;
	}
}
