package com.programme.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.programme.ImageUtilities;
import com.programme.R;
import com.programme.domain.LiveScoreInfo;
import com.programme.domain.Player;
import com.programme.services.PlayerSelectionService;

public class PlayerActivity extends Activity{
	
	private static final String FINISH_ACTIVITY = "finish activity";
	private ProgressBar spinner;
	
	private boolean isRefreshed;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent playerIntent = getIntent();
		Player playerOnIntent = (Player)playerIntent.getParcelableExtra("player"); 
		setContentView(R.layout.player_page_activity);
		spinner = (ProgressBar)findViewById(R.id.refreshSpinner);
		spinner.setVisibility(View.GONE);
		registerReceiver(updateActivityBroadcastReceiver, new IntentFilter(FINISH_ACTIVITY));	
		this.setTextViewInfo(playerOnIntent);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		spinner = (ProgressBar)findViewById(R.id.refreshSpinner);
		spinner.setVisibility(View.GONE);
		registerReceiver(updateActivityBroadcastReceiver, new IntentFilter(FINISH_ACTIVITY));	
	}
	
	@Override
	protected void onRestart(){
		super.onRestart();
		Intent playerIntent = getIntent();
		Player playerOnIntent = (Player)playerIntent.getParcelableExtra("player"); 
		Intent playerSelectionServiceIntent = new Intent(getBaseContext(), PlayerSelectionService.class);
		playerSelectionServiceIntent.putExtra("playerText", playerOnIntent.getFirstName()+" "+playerOnIntent.getLastName());
		playerSelectionServiceIntent.putExtra("teamName", playerOnIntent.getCounty());
		playerSelectionServiceIntent.getStringExtra("playerText");
		startService(playerSelectionServiceIntent);
        finish();
	}
	
    protected void onPause() {
        super.onPause();
        unregisterReceiver(updateActivityBroadcastReceiver);
        if(isRefreshed){
        	finish();
        }
    }
    
    @Override
    public void onBackPressed(){
    	super.onBackPressed();
    	spinner.setVisibility(View.VISIBLE);
    }
    
    BroadcastReceiver updateActivityBroadcastReceiver = new BroadcastReceiver(){
    	
        @Override
        public void onReceive(Context context, Intent intent) {
			Intent playerSelectionServiceIntent = new Intent(getBaseContext(), PlayerSelectionService.class);
			playerSelectionServiceIntent.putExtra("playerText", intent.getStringExtra("playerName"));
			playerSelectionServiceIntent.putExtra("teamName", intent.getStringExtra("teamName"));
			playerSelectionServiceIntent.getStringExtra("playerText");
			startService(playerSelectionServiceIntent);
			isRefreshed = true;
        }
    };

	private void setTextViewInfo(Player playerOnIntent){
		
		TextView nameInfoTextView = (TextView) findViewById(R.id.nameTextView);
		TextView clubInfoTextView = (TextView) findViewById(R.id.clubTextView);
		TextView countyInfoTextView = (TextView) findViewById(R.id.countyTextView);
		TextView ageInfoTextView = (TextView) findViewById(R.id.ageTextView);
		TextView pointsInfoTextView = (TextView) findViewById(R.id.pointsTextView);
		TextView goalsInfoTextView = (TextView) findViewById(R.id.goalsTextView);
		TextView widesInfoTextView = (TextView) findViewById(R.id.widesTextView);
		TextView yellowCardInfoTextView = (TextView) findViewById(R.id.yellowCardTextView);
		TextView redCardInfoTextView = (TextView) findViewById(R.id.redCardTextView);
		TextView blackCardInfoTextView = (TextView) findViewById(R.id.blackCardTextView);
		ImageView imageView = (ImageView) findViewById(R.id.profileImageView);
		
		nameInfoTextView.setText(playerOnIntent.getNumber()+".\n"+playerOnIntent.getFirstName()+"\n"+playerOnIntent.getLastName());
		clubInfoTextView.setText(playerOnIntent.getClub());
		countyInfoTextView.setText(playerOnIntent.getCounty());
		ageInfoTextView.setText(String.valueOf(playerOnIntent.getAge()));
		yellowCardInfoTextView.setText(String.valueOf(playerOnIntent.getYellowCard()));
		redCardInfoTextView.setText(String.valueOf(playerOnIntent.getRedCard()));
		blackCardInfoTextView.setText(String.valueOf(playerOnIntent.getBlackCard()));
		
		LiveScoreInfo playerLiveInfoOnIntent = (LiveScoreInfo) playerOnIntent.getLiveScoreInfo();
		
		pointsInfoTextView.setText(String.valueOf(playerLiveInfoOnIntent.getPoints())+"("+String.valueOf(playerLiveInfoOnIntent.getPointsDeadBall())+")");
		goalsInfoTextView.setText(String.valueOf(playerLiveInfoOnIntent.getGoals())+"("+String.valueOf(playerLiveInfoOnIntent.getGoalsDeadBall())+")");
		widesInfoTextView.setText(String.valueOf(playerLiveInfoOnIntent.getWides()));
		imageView.setImageBitmap(ImageUtilities.createBitmapFromString(playerOnIntent.getImage()));
	}
}
