package com.programme.dao;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.programme.StringUtilities;
import com.programme.domain.Fixture;
import com.programme.domain.Player;
import com.programme.rest.client.RestClient;

public class FixtureDaoImpl implements FixtureDao{
	
	private static final String baseUrl = "http://192.168.1.139:8080/ProgrammeServerApplication/";
	private static final String fixturesPathVariable = "fixtures";
	private static final String fixturePathVariable = "fixture";
	private static final String GET = "GET";

	@Override
	public List<Fixture> retrieveAllFixtures(Date date) {
		ArrayList<Fixture> fixtures = new ArrayList<Fixture>();
		RestClient restClient = new RestClient();
		String url = baseUrl+fixturesPathVariable;
		Map<String, String> params = new HashMap<String, String>();
		params.put("date", StringUtilities.convertDateToString(date, "yyyy-MM-dd"));
		url = StringUtilities.convertToRestUrl(url, params);
		Map<String, String> restParams = new HashMap<String, String>();
		restParams.put("url", url);
		restParams.put("type", GET);
		try {
			String result = restClient.execute(restParams).get();
			Type listType = new TypeToken<ArrayList<Fixture>>(){}.getType();
			if(!result.isEmpty()){
				fixtures = new Gson().fromJson(result, listType);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fixtures;
	}

	@Override
	public Fixture retrieveFixtureByTeamName(int homeTeamId, int awayTeamId, Date date) {
		Fixture fixture = new Fixture();
		RestClient restClient = new RestClient();
		String url = baseUrl+fixturePathVariable;
		Map<String, String> params = new HashMap<String, String>();
		params.put("date", StringUtilities.convertDateToString(date, "yyyy-MM-dd"));
		params.put("homeTeamId", homeTeamId + "");
		params.put("awayTeamId", awayTeamId + "");
		url = StringUtilities.convertToRestUrl(url, params);
		Map<String, String> restParams = new HashMap<String, String>();
		restParams.put("url", url);
		restParams.put("type", GET);
		try{
			String result = restClient.execute(restParams).get();
			Type listType = new TypeToken<Fixture>(){}.getType();
			fixture = new Gson().fromJson(result, listType);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fixture;
	}

}
