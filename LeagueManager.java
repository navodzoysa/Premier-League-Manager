interface LeagueManager {
	void addClub(SportsClub club);
	void deleteClub(String clubName);
	void displayStatistics(String clubName);
	void displayTable();
	void addMatch(Match match);
	void saveToFile();
	void loadFromFile();
}