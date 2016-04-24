package com.programme.broadcast.gcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RegistrationBroadcastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		String registrationId = intent.getStringExtra("registration_id");	
	}

}
