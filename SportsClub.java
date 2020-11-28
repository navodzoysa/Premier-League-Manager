import java.io.Serializable;

public abstract class SportsClub implements Serializable {
	private String clubName;
	private String clubLocation;
	private String clubStatistics;

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

	@Override
	public String toString() {
		return "\nClub name: " + clubName +
			"\nClub location: " + clubLocation;
	}

	@Override
	public boolean equals(Object o) {
		return this.getClubName().toLowerCase().equals(((SportsClub)o).getClubName().toLowerCase());
	}
}