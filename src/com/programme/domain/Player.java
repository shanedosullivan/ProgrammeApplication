package com.programme.domain;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {
	
	private static final String KEY_ID = "id";
	private static final String KEY_FIRST_NAME = "firstName";
	private static final String KEY_LAST_NAME="lastName";
	private static final String KEY_CLUB = "club";
	private static final String KEY_COUNTY = "county";
	private static final String KEY_AGE = "age";
	private static final String KEY_PLAYER_LIVE_INFO = "player_live_info";
	private static final String KEY_NUMBER = "number";
	private static final String KEY_POSITION = "position";
	private static final String KEY_IMAGE = "image";
	private static final String KEY_YELLOWCARD = "yellowCard";
	private static final String KEY_REDCARD = "redCard";
	private static final String KEY_BLACKCARD = "blackCard";
	
	private int id;

	private String firstName;
	
	private String lastName;
	
	private String club;
	
	private String county;
	
	private int age;
	
	private int number;
	
	private int position;
	
	private int redCard;
	
	private int yellowCard;
	
	private int blackCard;
	
	private LiveScoreInfo liveScoreInfo;
	
	private String image;

	public Player(){
		
	}

	public Player(int id, String firstName, String lastName, String club,
			String county, int age, int number, int position, LiveScoreInfo liveScoreInfo, String image, int yellowCard, int redCard, int blackCard) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.club = club;
		this.county = county;
		this.age = age;
		this.number = number;
		this.position = position;
		this.liveScoreInfo = liveScoreInfo;
		this.image = image;
		this.yellowCard = yellowCard;
		this.redCard = redCard;
		this.blackCard = blackCard;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getClub() {
		return club;
	}



	public void setClub(String club) {
		this.club = club;
	}



	public String getCounty() {
		return county;
	}



	public void setCounty(String county) {
		this.county = county;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public int getNumber() {
		return number;
	}



	public void setNumber(int number) {
		this.number = number;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getRedCard() {
		return redCard;
	}

	public void setRedCard(int redCard) {
		this.redCard = redCard;
	}

	public int getYellowCard() {
		return yellowCard;
	}

	public void setYellowCard(int yellowCard) {
		this.yellowCard = yellowCard;
	}

	public int getBlackCard() {
		return blackCard;
	}

	public void setBlackCard(int blackCard) {
		this.blackCard = blackCard;
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
	public int describeContents() {
		return 0;
	}

	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		Bundle playerBundle = new Bundle();
		playerBundle.putInt(KEY_ID, id);
		playerBundle.putString(KEY_FIRST_NAME, firstName);
		playerBundle.putString(KEY_LAST_NAME, lastName);
		playerBundle.putString(KEY_COUNTY, county);
		playerBundle.putString(KEY_CLUB, club);
		playerBundle.putInt(KEY_AGE, age);
		playerBundle.putInt(KEY_NUMBER, number);
		playerBundle.putParcelable(KEY_PLAYER_LIVE_INFO, liveScoreInfo);
		playerBundle.putString(KEY_IMAGE, image);
		playerBundle.putInt(KEY_POSITION, position);
		dest.writeBundle(playerBundle);
		
	}
	
	public static final Parcelable.Creator<Player> CREATOR = new Creator<Player>(){
		
		@Override
		public Player createFromParcel(Parcel source){
			Bundle bundle = source.readBundle();
			bundle.setClassLoader(Player.class.getClassLoader());
			return new Player(bundle.getInt(KEY_ID),bundle.getString(KEY_FIRST_NAME), bundle.getString(KEY_LAST_NAME),bundle.getString(KEY_CLUB),bundle.getString(KEY_COUNTY),bundle.getInt(KEY_AGE), bundle.getInt(KEY_NUMBER), bundle.getInt(KEY_POSITION), (LiveScoreInfo)bundle.getParcelable(KEY_PLAYER_LIVE_INFO), bundle.getString(KEY_IMAGE), bundle.getInt(KEY_YELLOWCARD), bundle.getInt(KEY_REDCARD), bundle.getInt(KEY_BLACKCARD));
		}

		@Override
		public Player[] newArray(int size) {
			return new Player[size];
		}
	};
		
}
