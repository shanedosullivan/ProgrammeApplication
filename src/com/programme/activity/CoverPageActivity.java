package com.programme.activity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.circularimageview.CircularImageView;
import com.programme.CalculationUtilities;
import com.programme.ImageUtilities;
import com.programme.R;
import com.programme.StringUtilities;
import com.programme.dao.FixtureDao;
import com.programme.dao.FixtureDaoImpl;
import com.programme.domain.Fixture;
import com.programme.services.TeamSelectionService;
import com.programme.services.task.FixturesRefreshTask;

public class CoverPageActivity extends Activity{
	
	private static final String FINISH_ACTIVITY = "finish activity";
	private static final String REFRESH_MSG = "Refreshing Info...";
	
	private FixtureDao fixtureDao;
	
	private boolean isRefreshed;
	private ProgressBar spinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent fixturesIntent = getIntent();
		Fixture selectedFixture = fixturesIntent.getParcelableExtra("fixture");
		setContentView(R.layout.cover_page_activity);
		spinner = (ProgressBar)findViewById(R.id.refreshSpinner);
		spinner.setVisibility(View.GONE);
		registerReceiver(updateActivityBroadcastReceiver, new IntentFilter(FINISH_ACTIVITY));
		this.setFixtureInfo(selectedFixture);
	}
	
	public void startTeamActivity(View view){
		spinner.setVisibility(View.VISIBLE);
		Intent teamSelectionServiceIntent = new Intent(getBaseContext(), TeamSelectionService.class);
		teamSelectionServiceIntent.putExtra("teamName", (String) view.getTag());
		startService(teamSelectionServiceIntent);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		spinner.setVisibility(View.GONE);
		registerReceiver(updateActivityBroadcastReceiver, new IntentFilter(FINISH_ACTIVITY));
	}
	
	@Override
	protected void onRestart(){
		super.onRestart();
    	fixtureDao = new FixtureDaoImpl();
		Intent fixturesIntent = getIntent();
		Fixture selectedFixture = fixturesIntent.getParcelableExtra("fixture");
		Map<String, Object> taskParams = new HashMap<String, Object>();
		taskParams.put("ctx", getApplicationContext());
		taskParams.put("teamId1", selectedFixture.getTeams().get(0).getId());
		taskParams.put("teamId2", selectedFixture.getTeams().get(1).getId());
		new FixturesRefreshTask().execute(taskParams);
		isRefreshed = true;
	}
	
	@Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(updateActivityBroadcastReceiver);
        if(isRefreshed){
        	Toast.makeText(getApplicationContext(), REFRESH_MSG, Toast.LENGTH_SHORT).show();
        	spinner.setVisibility(View.VISIBLE);
        	finish();
        }
    }
	
    @Override
    public void onBackPressed(){
    	Toast.makeText(getApplicationContext(), REFRESH_MSG, Toast.LENGTH_SHORT).show();
    	spinner.setVisibility(View.VISIBLE);
    	super.onBackPressed();
    }
	
	@Override
    protected void onDestroy() {        
        super.onDestroy();
    }
    
    BroadcastReceiver updateActivityBroadcastReceiver = new BroadcastReceiver(){
    	
        @Override
        public void onReceive(Context context, Intent intent) {
			try {
	        	fixtureDao = new FixtureDaoImpl();
	    		Intent fixturesIntent = getIntent();
	    		Fixture selectedFixture = fixturesIntent.getParcelableExtra("fixture");
	    		Fixture updatedFixture;
				updatedFixture = fixtureDao.retrieveFixtureByTeamName(selectedFixture.getTeams().get(0).getId(), selectedFixture.getTeams().get(1).getId(), new Date());
	    		Intent coverPageActivityIntent = new Intent(getBaseContext(), CoverPageActivity.class);
	    		coverPageActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    		coverPageActivityIntent.putExtra("fixture", updatedFixture);
	    		startActivity(coverPageActivityIntent);
	    		isRefreshed = true;
			} catch (Exception e) {
				Log.d("Exception Thrown!", e.getMessage());
				Intent errorIntent = new Intent(getApplicationContext(), ErrorActivity.class);
				startActivity(errorIntent);
			}
        }
    };
    
	private void setFixtureInfo(Fixture fixture){
		
		Button button1 = (Button)findViewById(R.id.teamButton1);
		Button button2 = (Button)findViewById(R.id.teamButton2);
		TextView throwInTextView = (TextView)findViewById(R.id.throwInTextView);
		TextView venueTextView = (TextView)findViewById(R.id.venueTextView);
		TextView competitionTextView = (TextView)findViewById(R.id.competitionTextView);
		TextView refereeTextView = (TextView)findViewById(R.id.refereeTextView);
		TextView dateTextView = (TextView)findViewById(R.id.dateTextView);
		CircularImageView homeTeamImageView = (CircularImageView)findViewById(R.id.homeTeamImageView);
		createCircularImageProperties(homeTeamImageView);
		CircularImageView awayTeamImageView = (CircularImageView)findViewById(R.id.awayTeamImageView);
		createCircularImageProperties(awayTeamImageView);
		
		button1.setTag(fixture.getTeams().get(0).getName());
		button2.setTag(fixture.getTeams().get(1).getName());
		
		String teamName1 = fixture.getTeams().get(0).getName();
		String teamName2 = fixture.getTeams().get(1).getName();
		String teamScore1 = CalculationUtilities.calculatePrettyPrintScore(fixture.getTeams().get(0).getLiveScoreInfo().getGoals(), fixture.getTeams().get(0).getLiveScoreInfo().getPoints());
		String teamScore2 = CalculationUtilities.calculatePrettyPrintScore(fixture.getTeams().get(1).getLiveScoreInfo().getGoals(), fixture.getTeams().get(1).getLiveScoreInfo().getPoints());
		
		button1.setText(teamName1+" "+teamScore1);
		button2.setText(teamScore2+" "+teamName2);
		throwInTextView.setText(fixture.getThrowIn());
		venueTextView.setText(fixture.getVenue());
		competitionTextView.setText(fixture.getCompetition());
		refereeTextView.setText(fixture.getReferee());
		dateTextView.setText(StringUtilities.convertDateToString(fixture.getDate(), "dd/MM/yyyy"));
		homeTeamImageView.setImageBitmap(ImageUtilities.createBitmapFromString(fixture.getTeams().get(0).getImage()));
		awayTeamImageView.setImageBitmap(ImageUtilities.createBitmapFromString(fixture.getTeams().get(1).getImage()));
	}
	
	private void createCircularImageProperties(CircularImageView circularImageView){
	    circularImageView.setBorderWidth(10);
	    circularImageView.addShadow();
	}
}
