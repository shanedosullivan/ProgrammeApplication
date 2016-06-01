package com.programme.dao;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.programme.StringUtilities;
import com.programme.domain.Player;
import com.programme.rest.client.RestClient;

public class PlayerDaoImpl implements PlayerDao{

	private final static String baseUrl = "http://192.168.1.139:8080/ProgrammeServerApplication/";
	private final static String playerPathVariable = "player";
	private final static String playersPathVariable = "players";
	private static final String GET = "GET";
	
	@Override
	public Player retrievePlayer(String firstName, String lastName, String teamName) {
		
		Player player = new Player();
		RestClient restClient = new RestClient();
		String url = baseUrl+playerPathVariable;
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put("firstName", firstName);
		urlParams.put("lastName", lastName);
		urlParams.put("team", teamName);
		url = StringUtilities.convertToRestUrl(url, urlParams);

		String jsonResponseArray;
		jsonResponseArray = restClient.get(url);
		player = new Gson().fromJson(jsonResponseArray, Player.class);
		return player;
	}

	@Override
	public List<Player> retrievePlayersOnTeam(String teamName) {
		List<Player> playersOnTeam = new ArrayList<Player>();
		RestClient restClient = new RestClient();
		String url = baseUrl+playersPathVariable;
		Map<String, String> params = new HashMap<String, String>();
		params.put("team", teamName);
		url = StringUtilities.convertToRestUrl(url, params);

		String jsonResponseArray;
		jsonResponseArray = restClient.get(url);
		Type listType = new TypeToken<ArrayList<Player>>(){}.getType();
		playersOnTeam = new Gson().fromJson(jsonResponseArray, listType);
		
		return playersOnTeam;
	}

}
