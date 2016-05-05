package com.programme.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.programme.StringUtilities;
import com.programme.rest.client.RestClient;

public class GCMRegistrationDaoImpl implements GCMRegistrationDao{

	private static final String baseUrl = "http://192.168.1.139:8080/ProgrammeServerApplication/";
	private static final String registrationPathVariable = "registration";
	private static final String POST = "POST";
	
	@Override
	public void sendTokenToServer(String token) {
		
		RestClient restClient = new RestClient();
		String url = baseUrl + registrationPathVariable;
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put("token", token);
		url = StringUtilities.convertToRestUrl(url, urlParams);
		Map<String, String> restParams = new HashMap<String, String>();
		restParams.put("url", url);
		restParams.put("type", POST);
		try {
			restClient.execute(restParams).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
