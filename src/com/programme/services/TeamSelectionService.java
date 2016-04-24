package com.programme.services;

import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import com.programme.activity.TeamPageActivity;
import com.programme.dao.PlayerDao;
import com.programme.dao.PlayerDaoImpl;
import com.programme.domain.Player;

public class TeamSelectionService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
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
		return START_NOT_STICKY;
	}

}
