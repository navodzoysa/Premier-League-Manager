package models;

import java.io.IOException;

public interface LeagueManager {
	void addClub(SportsClub club);
	void deleteClub(String clubName);
	void displayStatistics(String clubName);
	void displayTable();
	void addMatch(Match match);
	void saveToFile() throws IOException;
	void loadFromFile() throws IOException, ClassNotFoundException;
}
