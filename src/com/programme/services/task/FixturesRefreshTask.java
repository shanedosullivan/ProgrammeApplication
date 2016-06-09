package com.programme.services.task;

import java.util.Date;
import java.util.Map;

import com.programme.activity.CoverPageActivity;
import com.programme.activity.ErrorActivity;
import com.programme.dao.FixtureDao;
import com.programme.dao.FixtureDaoImpl;
import com.programme.domain.Fixture;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class FixturesRefreshTask extends AsyncTask<Map<String, Object>, Void, Fixture> {

	Context ctx;
	FixtureDao fixtureDao; 
	
	@Override
	protected Fixture doInBackground(Map<String, Object>... params) {
		try{
			Map<String, Object> taskParams = params[0];
			ctx = (Context)taskParams.get("ctx");
			fixtureDao = new FixtureDaoImpl();
			return fixtureDao.retrieveFixtureByTeamName(Integer.valueOf(taskParams.get("teamId1").toString()), Integer.valueOf(taskParams.get("teamId2").toString()), new Date());
		}catch(Exception e){
			Log.d("Exception thrown!", e.getMessage());
			Intent errorIntent = new Intent(ctx, ErrorActivity.class);
			errorIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			errorIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			ctx.startActivity(errorIntent);
			return null;
		}
	}
	
	@Override
	protected void onPostExecute(Fixture result){
		if(result!=null){
			Intent coverPageActivityIntent = new Intent(ctx, CoverPageActivity.class);
			coverPageActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			coverPageActivityIntent.putExtra("fixture", result);
			ctx.startActivity(coverPageActivityIntent);
		}
	}

}
