package com.programme.domain;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class LiveScoreInfo implements Parcelable {
	
	private static final String KEY_GOALS = "goals";
	private static final String KEY_POINTS = "points";
	private static final String KEY_POINTS_DEAD_BALL = "points_dead_ball";
	private static final String KEY_GOALS_DEAD_BALL = "goals_dead_ball";
	private static final String KEY_WIDES = "wides";

	private int goals;
	
	private int points;
	
	private int pointsDeadBall;
	
	private int goalsDeadBall;
	
	private int wides;

	public LiveScoreInfo(){
		
	}
	
	public LiveScoreInfo(int goals, int points, int pointsDeadBall,
			int goalsDeadBall, int wides) {
		super();
		this.goals = goals;
		this.points = points;
		this.pointsDeadBall = pointsDeadBall;
		this.goalsDeadBall = goalsDeadBall;
		this.wides = wides;
	}

	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPointsDeadBall() {
		return pointsDeadBall;
	}

	public void setPointsDeadBall(int pointsDeadBall) {
		this.pointsDeadBall = pointsDeadBall;
	}

	public int getGoalsDeadBall() {
		return goalsDeadBall;
	}

	public void setGoalsDeadBall(int goalsDeadBall) {
		this.goalsDeadBall = goalsDeadBall;
	}

	public int getWides() {
		return wides;
	}

	public void setWides(int wides) {
		this.wides = wides;
	}

	@Override
	public String toString() {
		return "PlayerLiveInfo [goals=" + goals + ", points=" + points
				+ ", pointsDeadBall=" + pointsDeadBall + ", goalsDeadBall="
				+ goalsDeadBall + ", wides=" + wides + "]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		Bundle playerLiveInfoBundle = new Bundle();
		playerLiveInfoBundle.putInt(KEY_GOALS, goals);
		playerLiveInfoBundle.putInt(KEY_GOALS_DEAD_BALL, goalsDeadBall);
		playerLiveInfoBundle.putInt(KEY_POINTS, points);
		playerLiveInfoBundle.putInt(KEY_POINTS_DEAD_BALL, pointsDeadBall);
		playerLiveInfoBundle.putInt(KEY_WIDES, wides);
		
		dest.writeBundle(playerLiveInfoBundle);
	}
	
	public static final Parcelable.Creator<LiveScoreInfo> CREATOR = new Creator<LiveScoreInfo>(){
		
		@Override
		public LiveScoreInfo createFromParcel(Parcel source){
			Bundle bundle = source.readBundle();
			bundle.setClassLoader(LiveScoreInfo.class.getClassLoader());
			return new LiveScoreInfo(bundle.getInt(KEY_GOALS),bundle.getInt(KEY_POINTS),bundle.getInt(KEY_POINTS_DEAD_BALL),bundle.getInt(KEY_GOALS_DEAD_BALL), bundle.getInt(KEY_WIDES));
		}

		@Override
		public LiveScoreInfo[] newArray(int size) {
			return new LiveScoreInfo[size];
		}
	};
}
