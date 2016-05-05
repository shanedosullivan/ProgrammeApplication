package com.programme.activity;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.programme.CalculationUtilities;
import com.programme.R;
import com.programme.dao.FixtureDao;
import com.programme.dao.FixtureDaoImpl;
import com.programme.domain.Fixture;
import com.programme.services.FixtureService;
import com.programme.services.PlayerSelectionService;

public class FixturePageActivity extends Activity implements OnClickListener{

	private static final String FINISH_ACTIVITY = "finish activity";
	private ProgressBar spinner;
	private boolean isRefreshed;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent fixturesIntent = getIntent();
		Bundle fixturesBundle = fixturesIntent.getBundleExtra("fixturesBundle");
		ArrayList<Fixture> fixtures = fixturesBundle.getParcelableArrayList("fixtures");
		setContentView(R.layout.fixture_page_activity);
		spinner = (ProgressBar)findViewById(R.id.refreshSpinner);
		spinner.setVisibility(View.GONE);
		registerReceiver(updateActivityBroadcastReceiver, new IntentFilter(FINISH_ACTIVITY));
		this.setFixtureInfo(fixtures);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		registerReceiver(updateActivityBroadcastReceiver, new IntentFilter(FINISH_ACTIVITY));
	}
	
	@Override
	protected void onRestart(){
		super.onRestart();
		Intent fixtureServiceIntent = new Intent(getBaseContext(), FixtureService.class);
		startService(fixtureServiceIntent);
		isRefreshed = true;
	}
	
	@Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(updateActivityBroadcastReceiver);
        if(isRefreshed){
        	spinner.setVisibility(View.VISIBLE);
        	finish();
        }
    }
	
	
	@Override
    protected void onDestroy() {        
        super.onDestroy();
    }
	
    BroadcastReceiver updateActivityBroadcastReceiver = new BroadcastReceiver(){
    	
        @Override
        public void onReceive(Context context, Intent intent) {
    		Intent fixtureServiceIntent = new Intent(getBaseContext(), FixtureService.class);
    		startService(fixtureServiceIntent);
    		isRefreshed = true;
        }
    };
	
	private void setFixtureInfo(ArrayList<Fixture> fixtures){
		
		RelativeLayout activityLayout = (RelativeLayout)findViewById(R.id.fixture_page_activity);
		int id = 1;
		
		TextView noFixturesTextView = (TextView)findViewById(R.id.noFixturesTextView);
		if(fixtures.isEmpty()){
			noFixturesTextView.setText("No Fixtures Avalilable");
		}
		
		for(Fixture fixture:fixtures){
			Button fixtureButton = new Button(this);
			
			String teamName1 = fixture.getTeams().get(0).getName();
			String teamName2 = fixture.getTeams().get(1).getName();
			String teamScore1 = CalculationUtilities.calculatePrettyPrintScore(fixture.getTeams().get(0).getLiveScoreInfo().getGoals(), fixture.getTeams().get(0).getLiveScoreInfo().getPoints());
			String teamScore2 = CalculationUtilities.calculatePrettyPrintScore(fixture.getTeams().get(1).getLiveScoreInfo().getGoals(), fixture.getTeams().get(1).getLiveScoreInfo().getPoints());
			
			fixtureButton.setId(id);
			fixtureButton.setBackground(getResources().getDrawable(R.drawable.buttonshapewhite));
			fixtureButton.setTextColor(Color.parseColor("#36AEFF"));
			fixtureButton.setText(teamName1+" "+teamScore1+"  "+teamScore2+" "+teamName2);
			fixtureButton.setTag(fixture);
			fixtureButton.setOnClickListener(this);
			
			RelativeLayout.LayoutParams buttonLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			if(id>1){
				buttonLayoutParams.addRule(RelativeLayout.BELOW, fixtures.get(id-2).getId());
			};
			
			id++;
			activityLayout.addView(fixtureButton, buttonLayoutParams);
		}
	}
	
	public void startCoverPageActivity(View view){
		Intent coverPageActivityIntent = new Intent(getBaseContext(), CoverPageActivity.class);
		coverPageActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		coverPageActivityIntent.putExtra("fixture", (Fixture) view.getTag());
		this.startActivity(coverPageActivityIntent);
	}

	@Override
	public void onClick(View v) {
		this.startCoverPageActivity(v);
	}
}
