public class SchoolFootballClub extends FootballClub {
	private String schoolName;

	public SchoolFootballClub() {}

	public SchoolFootballClub(String clubName, String clubLocation, int wins, int draws, int defeats, int goalsReceived, int goalsScored, int clubPoints, int matchesPlayed, String schoolName) {
		super(clubName, clubLocation, wins, draws, defeats, goalsReceived, goalsScored, clubPoints, matchesPlayed);
		this.schoolName = schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolName() {
		return schoolName;
	}
}