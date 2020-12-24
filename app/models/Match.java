package models;

import java.io.Serializable;

public class Match implements Serializable {
	private SportsClub teamA;
	private String teamAName;
	private SportsClub teamB;
	private String teamBName;
	private int teamAScore;
	private int teamBScore;
	private DateTime matchDate;
	private boolean isMatchRandom;

	public Match() {}
	
	public Match(SportsClub teamA, SportsClub teamB,int teamAScore, int teamBScore, DateTime matchDate) {
		this.teamA = teamA;
		this.teamAName = teamA.getClubName();
		this.teamB = teamB;
		this.teamBName = teamB.getClubName();
		this.teamAScore = teamAScore;
		this.teamBScore = teamBScore;
		this.matchDate = matchDate;
	}

	public SportsClub getTeamA() {
		return teamA;
	}

	public String getTeamAName() {
		return teamAName;
	}

	public SportsClub getTeamB() {
		return teamB;
	}

	public String getTeamBName() {
		return teamBName;
	}

	public void setTeamAScore(int teamAScore) {
		this.teamAScore = teamAScore;
	}

	public int getTeamAScore() {
		return teamAScore;
	}

	public void setTeamBScore(int teamBScore) {
		this.teamBScore = teamBScore;
	}

	public int getTeamBScore() {
		return teamBScore;
	}

	public void setMatchDate(DateTime matchDate) {
		this.matchDate = matchDate;
	}

	public DateTime getMatchDate() {
		return matchDate;
	}

	public void setIsMatchRandom(Boolean isMatchRandom) {
		this.isMatchRandom = isMatchRandom;
	}

	public boolean getIsMatchRandom() {
		return isMatchRandom;
	}

	@Override
	public String toString() {
		return "Home team: " + teamA.getClubName() + 
			"\nAway team: " + teamB.getClubName() +
			"\nHome team score: " + teamAScore +
			"\nAway team score: " + teamBScore +
			"\nMatch date: " + matchDate;
	}
}
