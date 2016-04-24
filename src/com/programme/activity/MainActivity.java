package com.programme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.programme.R;
import com.programme.services.ConnectivityService;
import com.programme.services.FixtureService;
import com.programme.services.GCMRegistrationIntentService;

public class MainActivity extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_programme_main);
		
		Intent connectivityIntent = new Intent(getBaseContext(), ConnectivityService.class);
		startService(connectivityIntent);
		
		Intent registrationIntent = new Intent(getBaseContext(), GCMRegistrationIntentService.class);
		startService(registrationIntent);
		
		Intent fixtureServiceIntent = new Intent(getBaseContext(), FixtureService.class);
		startService(fixtureServiceIntent);
	}

	@Override
	protected void onResume(){
		super.onResume();
	}
	
	@Override
    protected void onPause() {
        super.onPause();
        finish();
    }
	
	@Override
    protected void onDestroy() {        
        super.onDestroy();
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
