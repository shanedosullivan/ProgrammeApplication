package com.programme.services;


import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.programme.activity.ErrorActivity;
import com.programme.activity.PlayerActivity;
import com.programme.dao.PlayerDao;
import com.programme.dao.PlayerDaoImpl;
import com.programme.domain.LiveScoreInfo;
import com.programme.domain.Player;

public class PlayerSelectionService extends IntentService{

	public PlayerSelectionService() {
		super("PlayerSelectionService");
	}

	private PlayerDao dao;

	@Override
	protected void onHandleIntent(Intent intent) {
		try{
			dao = new PlayerDaoImpl();
			
			String playerText = intent.getStringExtra("playerText");
			String teamName = intent.getStringExtra("teamName");
			String[] playerNames = playerText.split("\\s+");
			
			Intent playerActivityIntent = new Intent(getBaseContext(), PlayerActivity.class);
			playerActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			
			Player player = dao.retrievePlayer(playerNames[0], playerNames[1], teamName);
			playerActivityIntent.putExtra("player", player);
			this.startActivity(playerActivityIntent);
		}catch(Exception e){
			Log.d("Exception thrown!", e.getMessage());
			Intent errorIntent = new Intent(getApplicationContext(), ErrorActivity.class);
			errorIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			errorIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(errorIntent);
			
		}
	}
}
