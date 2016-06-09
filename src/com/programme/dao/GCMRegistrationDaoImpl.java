package com.programme.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.http.client.ClientProtocolException;

import com.programme.StringUtilities;
import com.programme.rest.client.RestClient;

public class GCMRegistrationDaoImpl implements GCMRegistrationDao{

	private static final String baseUrl = "http://192.168.1.139:8080/ProgrammeServerApplication/";
	private static final String registrationPathVariable = "registration";
	private static final String POST = "POST";
	
	@Override
	public void sendTokenToServer(String token) throws ClientProtocolException, IOException {
		
		RestClient restClient = new RestClient();
		String url = baseUrl + registrationPathVariable;
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put("token", token);
		url = StringUtilities.convertToRestUrl(url, urlParams);

		restClient.post(url);
	}

}
