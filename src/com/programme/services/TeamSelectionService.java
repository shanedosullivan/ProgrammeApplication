package com.programme.services;

import java.util.ArrayList;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import com.programme.activity.TeamPageActivity;
import com.programme.dao.PlayerDao;
import com.programme.dao.PlayerDaoImpl;
import com.programme.domain.Player;

public class TeamSelectionService extends IntentService{

	public TeamSelectionService() {
		super("TeamSelectionService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String teamName = intent.getStringExtra("teamName");
		ArrayList<Player> playersOnTeam = new ArrayList<Player>();

		PlayerDao playerDao = new PlayerDaoImpl();		
		playersOnTeam = (ArrayList<Player>) playerDao.retrievePlayersOnTeam(teamName);
		
		Intent teamActivityIntent = new Intent(getBaseContext(), TeamPageActivity.class);
		teamActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		Bundle playersBundle = new Bundle();
		playersBundle.putParcelableArrayList("playersOnTeam", playersOnTeam);
		teamActivityIntent.putExtra("playersBundle", playersBundle);
		this.startActivity(teamActivityIntent);
		
	}

}
