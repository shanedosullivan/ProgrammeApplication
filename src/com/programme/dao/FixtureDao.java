package com.programme.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.programme.domain.Fixture;

public interface FixtureDao {

	List<Fixture> retrieveAllFixtures(Date date) throws ClientProtocolException, IOException;
	
	Fixture retrieveFixtureByTeamName(int homeTeamId, int awayTeamId, Date date) throws ClientProtocolException, IOException;
}
