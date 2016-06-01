package com.programme.services;


import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

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
		dao = new PlayerDaoImpl();
		
		String playerText = intent.getStringExtra("playerText");
		String teamName = intent.getStringExtra("teamName");
		String[] playerNames = playerText.split("\\s+");
		
		Intent playerActivityIntent = new Intent(getBaseContext(), PlayerActivity.class);
		playerActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		Player player = dao.retrievePlayer(playerNames[0], playerNames[1], teamName);
		playerActivityIntent.putExtra("player", player);
		this.startActivity(playerActivityIntent);
		
	}
}
