package com.programme.services;

import java.util.ArrayList;
import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import com.programme.activity.FixturePageActivity;
import com.programme.dao.FixtureDao;
import com.programme.dao.FixtureDaoImpl;
import com.programme.domain.Fixture;

public class FixtureService extends Service{

	private FixtureDao fixtureDao;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		fixtureDao = new FixtureDaoImpl();
		ArrayList<Fixture> fixtures = (ArrayList<Fixture>)fixtureDao.retrieveAllFixtures(new Date());
		Intent fixturePageActivityIntent = new Intent(getBaseContext(), FixturePageActivity.class);
		fixturePageActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Bundle fixturesBundle = new Bundle();
		fixturesBundle.putParcelableArrayList("fixtures", fixtures);
		fixturePageActivityIntent.putExtra("fixturesBundle", fixturesBundle);
		this.startActivity(fixturePageActivityIntent);
		return START_NOT_STICKY;
	}
}
