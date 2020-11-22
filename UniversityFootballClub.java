public class UniversityFootballClub extends FootballClub {
	private String universityName;

	public UniversityFootballClub() {}	

	public UniversityFootballClub(String clubName, String clubLocation, int wins, int draws, int defeats, int goalsReceived, int goalsScored, int clubPoints, int matchesPlayed, String universityName) {
		super(clubName, clubLocation, wins, draws, defeats, goalsReceived, goalsScored, clubPoints, matchesPlayed);
		this.universityName = universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getUniversityName() {
		return universityName;
	}
}