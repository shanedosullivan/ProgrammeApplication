package com.programme.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.programme.R;
import com.programme.domain.Player;
import com.programme.services.PlayerSelectionService;
import com.programme.services.TeamSelectionService;

public class TeamPageActivity extends Activity{

	private static final String FINISH_ACTIVITY = "finish activity";
	
	private boolean isRefreshed;
	private ProgressBar spinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.team_page_activity);
		spinner = (ProgressBar)findViewById(R.id.refreshSpinner);
		spinner.setVisibility(View.GONE);
		Bundle playersBundle = getIntent().getBundleExtra("playersBundle"); 
		ArrayList<Player> players = playersBundle.getParcelableArrayList("playersOnTeam");
		Map<Integer, Player> playersNoMap = this.buildPlayersMap(players);
		this.setUpPlayerNames(playersNoMap);
	}
	
	public void startPlayerActivity(View view){
		spinner.setVisibility(View.VISIBLE);
		Intent playerSelectionServiceIntent = new Intent(getBaseContext(), PlayerSelectionService.class);
		Button button = (Button) view;
		Bundle playersBundle = getIntent().getBundleExtra("playersBundle");
		ArrayList<Player> players = playersBundle.getParcelableArrayList("playersOnTeam");
		Player player = (Player)button.getTag();
		playerSelectionServiceIntent.putExtra("playerText", player.getFirstName()+" "+player.getLastName());
		playerSelectionServiceIntent.putExtra("teamName", players.get(0).getCounty());
		registerReceiver(updateActivityBroadcastReceiver, new IntentFilter(FINISH_ACTIVITY));
		startService(playerSelectionServiceIntent);
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
		Bundle playersBundle = getIntent().getBundleExtra("playersBundle"); 
		ArrayList<Player> players = playersBundle.getParcelableArrayList("playersOnTeam");
		Intent teamSelectionServiceIntent = new Intent(getBaseContext(), TeamSelectionService.class);
		teamSelectionServiceIntent.putExtra("teamName", players.get(0).getCounty());
		startService(teamSelectionServiceIntent);
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
    public void onBackPressed(){
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
			Intent teamSelectionServiceIntent = new Intent(getBaseContext(), TeamSelectionService.class);
			teamSelectionServiceIntent.putExtra("teamName", intent.getStringExtra("teamName"));
			startService(teamSelectionServiceIntent);
            isRefreshed = true;
        }
    };
	
	private Map<Integer, Player> buildPlayersMap(List<Player> players){
		Map<Integer, Player> playersPositionMap = new HashMap<Integer, Player>();
		for(Player player : players){
			playersPositionMap.put(player.getPosition(), player);
		}
		return playersPositionMap;
	}
	
	private void setUpPlayerNames(Map<Integer, Player> playersPositionMap){
		Button button1 = (Button)findViewById(R.id.playerButton1);
		button1.setText(playersPositionMap.get(1).getNumber()+"\n"+playersPositionMap.get(1).getFirstName()+"\n"+playersPositionMap.get(1).getLastName());
		button1.setTag(playersPositionMap.get(1));
		Button button2 = (Button)findViewById(R.id.playerButton2);
		button2.setText(playersPositionMap.get(2).getNumber()+"\n"+playersPositionMap.get(2).getFirstName()+"\n"+playersPositionMap.get(2).getLastName());
		button2.setTag(playersPositionMap.get(2));
		Button button3 = (Button)findViewById(R.id.playerButton3);
		button3.setText(playersPositionMap.get(3).getNumber()+"\n"+playersPositionMap.get(3).getFirstName()+"\n"+playersPositionMap.get(3).getLastName());
		button3.setTag(playersPositionMap.get(3));
		Button button4 = (Button)findViewById(R.id.playerButton4);
		button4.setText(playersPositionMap.get(4).getNumber()+"\n"+playersPositionMap.get(4).getFirstName()+"\n"+playersPositionMap.get(4).getLastName());
		button4.setTag(playersPositionMap.get(4));
		Button button5 = (Button)findViewById(R.id.playerButton5);
		button5.setText(playersPositionMap.get(5).getNumber()+"\n"+playersPositionMap.get(5).getFirstName()+"\n"+playersPositionMap.get(5).getLastName());
		button5.setTag(playersPositionMap.get(5));
		Button button6 = (Button)findViewById(R.id.playerButton6);
		button6.setText(playersPositionMap.get(6).getNumber()+"\n"+playersPositionMap.get(6).getFirstName()+"\n"+playersPositionMap.get(6).getLastName());
		button6.setTag(playersPositionMap.get(6));
		Button button7 = (Button)findViewById(R.id.playerButton7);
		button7.setText(playersPositionMap.get(7).getNumber()+"\n"+playersPositionMap.get(7).getFirstName()+"\n"+playersPositionMap.get(7).getLastName());
		button7.setTag(playersPositionMap.get(7));
		Button button8 = (Button)findViewById(R.id.playerButton8);
		button8.setText(playersPositionMap.get(8).getNumber()+"\n"+playersPositionMap.get(8).getFirstName()+"\n"+playersPositionMap.get(8).getLastName());
		button8.setTag(playersPositionMap.get(8));
		Button button9 = (Button)findViewById(R.id.playerButton9);
		button9.setText(playersPositionMap.get(9).getNumber()+"\n"+playersPositionMap.get(9).getFirstName()+"\n"+playersPositionMap.get(9).getLastName());
		button9.setTag(playersPositionMap.get(9));
		Button button10 = (Button)findViewById(R.id.playerButton10);
		button10.setText(playersPositionMap.get(10).getNumber()+"\n"+playersPositionMap.get(10).getFirstName()+"\n"+playersPositionMap.get(10).getLastName());
		button10.setTag(playersPositionMap.get(10));
		Button button11 = (Button)findViewById(R.id.playerButton11);
		button11.setText(playersPositionMap.get(11).getNumber()+"\n"+playersPositionMap.get(11).getFirstName()+"\n"+playersPositionMap.get(11).getLastName());
		button11.setTag(playersPositionMap.get(11));
		Button button12 = (Button)findViewById(R.id.playerButton12);
		button12.setText(playersPositionMap.get(12).getNumber()+"\n"+playersPositionMap.get(12).getFirstName()+"\n"+playersPositionMap.get(12).getLastName());
		button12.setTag(playersPositionMap.get(12));
		Button button13 = (Button)findViewById(R.id.playerButton13);
		button13.setText(playersPositionMap.get(13).getNumber()+"\n"+playersPositionMap.get(13).getFirstName()+"\n"+playersPositionMap.get(13).getLastName());
		button13.setTag(playersPositionMap.get(13));
		Button button14 = (Button)findViewById(R.id.playerButton14);
		button14.setText(playersPositionMap.get(14).getNumber()+"\n"+playersPositionMap.get(14).getFirstName()+"\n"+playersPositionMap.get(14).getLastName());
		button14.setTag(playersPositionMap.get(14));
		Button button15 = (Button)findViewById(R.id.playerButton15);
		button15.setText(playersPositionMap.get(15).getNumber()+"\n"+playersPositionMap.get(15).getFirstName()+"\n"+playersPositionMap.get(15).getLastName());
		button15.setTag(playersPositionMap.get(15));
		Button button16 = (Button)findViewById(R.id.playerButton16);
		button16.setText(playersPositionMap.get(16).getNumber()+"\n"+playersPositionMap.get(16).getFirstName()+"\n"+playersPositionMap.get(16).getLastName());
		button16.setTag(playersPositionMap.get(16));
		Button button17 = (Button)findViewById(R.id.playerButton17);
		button17.setText(playersPositionMap.get(17).getNumber()+"\n"+playersPositionMap.get(17).getFirstName()+"\n"+playersPositionMap.get(17).getLastName());
		button17.setTag(playersPositionMap.get(17));
		Button button18 = (Button)findViewById(R.id.playerButton18);
		button18.setText(playersPositionMap.get(18).getNumber()+"\n"+playersPositionMap.get(18).getFirstName()+"\n"+playersPositionMap.get(18).getLastName());
		button18.setTag(playersPositionMap.get(18));
		Button button19 = (Button)findViewById(R.id.playerButton19);
		button19.setText(playersPositionMap.get(19).getNumber()+"\n"+playersPositionMap.get(19).getFirstName()+"\n"+playersPositionMap.get(19).getLastName());
		button19.setTag(playersPositionMap.get(19));
		Button button20 = (Button)findViewById(R.id.playerButton20);
		button20.setText(playersPositionMap.get(20).getNumber()+"\n"+playersPositionMap.get(20).getFirstName()+"\n"+playersPositionMap.get(20).getLastName());
		button20.setTag(playersPositionMap.get(20));
		Button button21 = (Button)findViewById(R.id.playerButton21);
		button21.setText(playersPositionMap.get(21).getNumber()+"\n"+playersPositionMap.get(21).getFirstName()+"\n"+playersPositionMap.get(21).getLastName());
		button21.setTag(playersPositionMap.get(21));
		Button button22 = (Button)findViewById(R.id.playerButton22);
		button22.setText(playersPositionMap.get(22).getNumber()+"\n"+playersPositionMap.get(22).getFirstName()+"\n"+playersPositionMap.get(22).getLastName());
		button22.setTag(playersPositionMap.get(22));
		Button button23 = (Button)findViewById(R.id.playerButton23);
		button23.setText(playersPositionMap.get(23).getNumber()+"\n"+playersPositionMap.get(23).getFirstName()+"\n"+playersPositionMap.get(23).getLastName());
		button23.setTag(playersPositionMap.get(23));
		Button button24 = (Button)findViewById(R.id.playerButton24);
		button24.setText(playersPositionMap.get(24).getNumber()+"\n"+playersPositionMap.get(24).getFirstName()+"\n"+playersPositionMap.get(24).getLastName());
		button24.setTag(playersPositionMap.get(24));
	}
	
	
}
