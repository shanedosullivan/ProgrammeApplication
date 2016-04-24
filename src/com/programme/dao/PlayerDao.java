package com.programme.dao;

import java.util.List;

import com.programme.domain.Player;

public interface PlayerDao {

	Player retrievePlayer(String firstName, String lastName, String teamName);
	
	List<Player> retrievePlayersOnTeam(String teamName);
}
