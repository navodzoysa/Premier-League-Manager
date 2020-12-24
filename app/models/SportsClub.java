package models;

import java.io.Serializable;

public abstract class SportsClub implements Serializable {
	private String clubName;
	private String clubLocation;
	private String clubStatistics;
	private int wins;
	private int draws;
	private int defeats;
	private int clubPoints;
	private int matchesPlayed;

	public SportsClub() {}
	
	public SportsClub(String clubName, String clubLocation) {
		this.clubName = clubName;
		this.clubLocation = clubLocation;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubLocation(String clubLocation) {
		this.clubLocation = clubLocation;
	}

	public String getClubLocation() {
		return clubLocation;
	}

	public void setClubStatistics(String clubStatistics) {
		this.clubStatistics = clubStatistics;
	}

	public String getClubStatistics() {
		return clubStatistics;
	}

	public void setWins(int wins) {
		this.wins += wins;
	}

	public int getWins() {
		return wins;
	}

	public void setDraws(int draws) {
		this.draws += draws;
	}

	public int getDraws() {
		return draws;
	}

	public void setDefeats(int defeats) {
		this.defeats += defeats;
	}

	public int getDefeats() {
		return defeats;
	}

	public void setClubPoints(int clubPoints) {
		this.clubPoints += clubPoints;
	}

	public int getClubPoints() {
		return clubPoints;
	}

	public void setMatchesPlayed(int matchesPlayed) {
		this.matchesPlayed += matchesPlayed;
	}

	public int getMatchesPlayed() {
		return matchesPlayed;
	}

	@Override
	public String toString() {
		return "\nClub name: " + clubName +
			"\nClub location: " + clubLocation +
			"\nWins: " + wins +
			"\nDraws: " + draws + 
			"\nDefeats: " + defeats +
			"\nClub Points: " + clubPoints +
			"\nMatches Played: " + matchesPlayed;
	}

	@Override
	public boolean equals(Object o) {
		return this.getClubName().toLowerCase().equals(((SportsClub)o).getClubName().toLowerCase());
	}
}
