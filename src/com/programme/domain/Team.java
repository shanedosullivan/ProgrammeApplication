package com.programme.domain;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable{
	
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_LIVE_SCORE_INFO="liveScoreInfo";
	private static final String KEY_PLAYERS= "players";
	private static final String KEY_IMAGE = "image";

	private int id;
	
	private String name;
	
	private List<Player> players;
	
	private LiveScoreInfo liveScoreInfo;
	
	private String image;
	
	public Team(){
		
	}
	
	public Team(int id, String name, List<Player> players,
			LiveScoreInfo liveScoreInfo, String image) {
		super();
		this.id = id;
		this.name = name;
		this.players = players;
		this.liveScoreInfo = liveScoreInfo;
		this.image = image;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public LiveScoreInfo getLiveScoreInfo() {
		return liveScoreInfo;
	}

	public void setLiveScoreInfo(LiveScoreInfo liveScoreInfo) {
		this.liveScoreInfo = liveScoreInfo;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", players=" + players
				+ ", liveScoreInfo=" + liveScoreInfo + "]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		Bundle teamBundle = new Bundle();
		teamBundle.putInt(KEY_ID, id);
		teamBundle.putString(KEY_NAME, name);
		teamBundle.putParcelable(KEY_LIVE_SCORE_INFO, liveScoreInfo);
		teamBundle.putParcelableArrayList(KEY_PLAYERS, (ArrayList<? extends Parcelable>) players);
		teamBundle.putString(KEY_IMAGE, image);
		dest.writeBundle(teamBundle);
		
	}

	public static final Parcelable.Creator<Team> CREATOR = new Creator<Team>(){
		
		@Override
		public Team createFromParcel(Parcel source){
			Bundle bundle = source.readBundle();
			bundle.setClassLoader(Team.class.getClassLoader());
			List<Player> players = bundle.getParcelableArrayList(KEY_PLAYERS);
			return new Team(bundle.getInt(KEY_ID), bundle.getString(KEY_NAME), players, (LiveScoreInfo)bundle.getParcelable(KEY_LIVE_SCORE_INFO), bundle.getString(KEY_IMAGE));
		}

		@Override
		public Team[] newArray(int size) {
			return new Team[size];
		}
	};
	
}
