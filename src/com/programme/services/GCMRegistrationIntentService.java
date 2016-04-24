package com.programme.services;

import java.io.IOException;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.programme.R;
import com.programme.dao.GCMRegistrationDao;
import com.programme.dao.GCMRegistrationDaoImpl;

import android.app.IntentService;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;

public class GCMRegistrationIntentService extends IntentService{

	private static final String NAME = "GCMRegistrationIntentService";
	
	private GCMRegistrationDao gcmRegistrationDao;
	
	public GCMRegistrationIntentService() {
		super(NAME);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		
		try {
		
			gcmRegistrationDao = new GCMRegistrationDaoImpl();
			InstanceID instanceID = InstanceID.getInstance(this);
			String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
			gcmRegistrationDao.sendTokenToServer(token);
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

}
