package com.programme.dao;

import java.util.Date;
import java.util.List;

import com.programme.domain.Fixture;

public interface FixtureDao {

	List<Fixture> retrieveAllFixtures(Date date);
	
	Fixture retrieveFixtureByTeamName(int homeTeamId, int awayTeamId, Date date);
}
