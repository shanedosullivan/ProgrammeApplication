package com.programme.services;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

public class CustomInstanceIDListenerService extends InstanceIDListenerService{

    @Override
    public void onTokenRefresh() {

        Intent intent = new Intent(this, GCMRegistrationIntentService.class);
        startService(intent);
    }
}
