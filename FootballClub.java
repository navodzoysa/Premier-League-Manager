import java.io.Serializable;

public class FootballClub extends SportsClub implements Comparable<FootballClub>, Serializable {
	private int wins;
	private int draws;
	private int defeats;
	private int goalsReceived;
	private int goalsScored;
	private int clubPoints;
	private int matchesPlayed;

	public FootballClub() {}

	public FootballClub(String clubName, String clubLocation) {
		super(clubName, clubLocation);
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

	public void setGoalsReceived(int goalsReceived) {
		this.goalsReceived += goalsReceived;
	}

	public int getGoalsReceived() {
		return goalsReceived;
	}

	public void setGoalsScored(int goalsScored) {
		this.goalsScored += goalsScored;
	}

	public int getGoalsScored() {
		return goalsScored;
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

	public int getGoalDifference() {
		return this.goalsScored - this.goalsReceived;
	}

	@Override
	public String toString() {
		return super.toString() + 
			"\nWins: " + wins +
			"\nDraws: " + draws + 
			"\nDefeats: " + defeats +
			"\nGoals Received: " + goalsReceived +
			"\nGoals Scored: " + goalsScored +
			"\nClub Points: " + clubPoints +
			"\nMatches Played: " + matchesPlayed;
	}

	@Override
	public int compareTo(FootballClub club) {
		if(this.getClubPoints() == club.getClubPoints()) {
			if(this.getGoalDifference() == club.getGoalDifference()) {
				return this.getGoalsScored() - club.getGoalsScored();
			}
			return this.getGoalDifference() - club.getGoalDifference();
		}
		return this.getClubPoints() - club.getClubPoints();
	}
}