package com.programme.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class Fixture implements Parcelable {

	private static final String KEY_ID = "id";
	private static final String KEY_TEAMS = "teams";
	private static final String KEY_VENUE="venue";
	private static final String KEY_THROW_IN = "throwIn";
	private static final String KEY_COMPETITION = "competition";
	private static final String KEY_REFEREE = "referee";
	private static final String KEY_DATE = "date";
	
	private int id;
	
	//Android API blows up here when programming to List interface - review!
	private ArrayList<Team> teams;
	
	private String venue;
	
	private String throwIn;
	
	private String competition;
	
	private String referee;
	
	private Date date;
	
	public Fixture(){
		
	}
	
	public Fixture(int id, ArrayList<Team> teams, String venue, String throwIn,
			String competition, String referee, Date date) {
		super();
		this.id = id;
		this.teams = teams;
		this.venue = venue;
		this.throwIn = throwIn;
		this.competition = competition;
		this.referee = referee;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getThrowIn() {
		return throwIn;
	}

	public void setThrowIn(String throwIn) {
		this.throwIn = throwIn;
	}

	public String getCompetition() {
		return competition;
	}

	public void setCompetition(String competition) {
		this.competition = competition;
	}

	public String getReferee() {
		return referee;
	}

	public void setReferee(String referee) {
		this.referee = referee;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		Bundle fixtureBundle = new Bundle();
		fixtureBundle.putInt(KEY_ID, id);
		fixtureBundle.putParcelableArrayList(KEY_TEAMS, (ArrayList<? extends Parcelable>) teams);
		fixtureBundle.putString(KEY_VENUE, venue);
		fixtureBundle.putString(KEY_THROW_IN, throwIn);
		fixtureBundle.putString(KEY_COMPETITION, competition);
		fixtureBundle.putString(KEY_REFEREE, referee);
		fixtureBundle.putLong(KEY_DATE, date.getTime());
		dest.writeBundle(fixtureBundle);
		
	}
	
	public static final Parcelable.Creator<Fixture> CREATOR = new Creator<Fixture>(){
		
		@Override
		public Fixture createFromParcel(Parcel source){
			Bundle bundle = source.readBundle();
			bundle.setClassLoader(Player.class.getClassLoader());
			ArrayList<Team> teamArray = bundle.getParcelableArrayList(KEY_TEAMS);
			return new Fixture(bundle.getInt(KEY_ID), teamArray, bundle.getString(KEY_VENUE),bundle.getString(KEY_THROW_IN),bundle.getString(KEY_COMPETITION), bundle.getString(KEY_REFEREE), new Date(bundle.getLong(KEY_DATE)));
		}

		@Override
		public Fixture[] newArray(int size) {
			return new Fixture[size];
		}
	};

	
}
