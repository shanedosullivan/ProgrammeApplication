package com.programme.dao;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.programme.domain.Player;

public interface PlayerDao {

	Player retrievePlayer(String firstName, String lastName, String teamName) throws ClientProtocolException, IOException;
	
	List<Player> retrievePlayersOnTeam(String teamName) throws ClientProtocolException, IOException;
}
