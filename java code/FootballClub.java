import java.io.Serializable;

public class FootballClub extends SportsClub implements Comparable<FootballClub>, Serializable {
	private int goalsReceived;
	private int goalsScored;

	public FootballClub() {}

	public FootballClub(String clubName, String clubLocation) {
		super(clubName, clubLocation);
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

	public int getGoalDifference() {
		return this.goalsScored - this.goalsReceived;
	}

	@Override
	public String toString() {
		return super.toString() + 
			"\nGoals Received: " + goalsReceived +
			"\nGoals Scored: " + goalsScored;
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