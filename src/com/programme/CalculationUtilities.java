package com.programme;

public class CalculationUtilities {

	public static int calculateScoresFromPlay(int totalScores, int deadBallScores){
		return totalScores - deadBallScores;
	}
	
	public static String calculatePrettyPrintScore(int goals, int points){
		String prettyPrintScore = "";
		if(points<10){
			prettyPrintScore = goals+"-0"+points;
		}
		else{
			prettyPrintScore = goals+"-"+points;
		}
		return prettyPrintScore;
	}
}
