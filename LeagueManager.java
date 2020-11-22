import java.io.IOException;

interface LeagueManager {
	void addClub(SportsClub club);
	void deleteClub(String clubName);
	void displayStatistics(String clubName);
	void displayTable();
	void addMatch(Match match);
	void saveToFile(String fileName) throws IOException;
	void loadFromFile(String fileName) throws IOException, ClassNotFoundException;
}