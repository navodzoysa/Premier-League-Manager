package models;

public class SchoolFootballClub extends FootballClub {
	private String schoolName;

	public SchoolFootballClub() {}

	public SchoolFootballClub(String clubName, String clubLocation, String schoolName) {
		super(clubName, clubLocation);
		this.schoolName = schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolName() {
		return schoolName;
	}
}
