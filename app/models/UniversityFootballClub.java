package models;

public class UniversityFootballClub extends FootballClub {
	private String universityName;

	public UniversityFootballClub() {}	

	public UniversityFootballClub(String clubName, String clubLocation, String universityName) {
		super(clubName, clubLocation);
		this.universityName = universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getUniversityName() {
		return universityName;
	}
}
