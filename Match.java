public class Match {
	private SportsClub teamA;
	private SportsClub teamB;
	private int teamAScore;
	private int teamBScore;
	private DateTime matchDate;

	public Match() {}
	
	public Match(SportsClub teamA, SportsClub teamB,int teamAScore, int teamBScore, DateTime matchDate) {
		this.teamA = teamA;
		this.teamB = teamB;
		this.teamAScore = teamAScore;
		this.teamBScore = teamBScore;
		this.matchDate = matchDate;
	}

	public SportsClub getTeamA() {
		return teamA;
	}

	public SportsClub getTeamB() {
		return teamB;
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

	public void setDate(DateTime matchDate) {
		this.matchDate = matchDate;
	}

	public DateTime getDate() {
		return matchDate;
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