package com.programme.services;

import java.util.ArrayList;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.programme.activity.ErrorActivity;
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
		try{
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
		}catch(Exception e){
			Log.d("Exception thrown!", e.getMessage());
			Intent errorIntent = new Intent(getApplicationContext(), ErrorActivity.class);
			errorIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			errorIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(errorIntent);
		}

		
	}

}
