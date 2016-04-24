package com.programme.services;

import com.google.android.gms.gcm.GcmListenerService;
import com.google.android.gms.gcm.GcmReceiver;
import com.programme.activity.PlayerActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class UpdateBroadcastService extends GcmListenerService {

	private static final String FINISH_ACTIVITY = "finish activity";
	
	@Override
	public void onMessageReceived(String from, Bundle data) {
	    Intent intent = new Intent();
	    intent.putExtra("playerName", data.getString("player"));
	    intent.putExtra("teamName", data.getString("team"));
	    intent.setAction(FINISH_ACTIVITY);
	    sendBroadcast(intent);
	}

}
