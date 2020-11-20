import java.util.*;

public class PremierLeagueManager implements LeagueManager {
	private List<FootballClub> footballClubList = new ArrayList<>();

	public boolean checkForClub(String clubName) {
		for(FootballClub club : footballClubList) {
			if(club.getClubName().toLowerCase().equals(clubName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public FootballClub getClubFromList(String clubName) {
		if(checkForClub(clubName)) {
			for(FootballClub club : footballClubList) {
				if(club.getClubName().toLowerCase().equals(clubName.toLowerCase())) {
					return club;
				}
			}
		}
		return null;
	}

	public void displayClubNames() {
		if(!footballClubList.isEmpty()) {
			for(FootballClub club : footballClubList) {
				System.out.println(club.getClubName());
			}
		}
		else {
			System.out.println("No clubs found!!!");
		}
	}

	@Override
	public void addClub(FootballClub newClub) {
		Boolean clubExists = false;
		for(FootballClub club : footballClubList) {
			if(club.equals(newClub)) {
				System.out.println("Club already exists!!!");
				clubExists = true;
				break;
			}
		}
		if(!clubExists) {
			footballClubList.add(newClub);
			System.out.println("Successfully added club");
		}
	}

	@Override
	public void deleteClub(String clubName) {
		Boolean clubExists = false;
		for(FootballClub club : footballClubList) {
			if(clubName.equals(club.getClubName())) {
				footballClubList.remove(club);
				System.out.println("Successfully removed club");
				clubExists = true;
				break;
			}
		}
		if(!clubExists) {
			System.out.println("Club does not exist!!!");
		}
	}

	@Override
	public void displayStatistics(String clubName) {
		Boolean clubExists = false;
		if(!footballClubList.isEmpty()){
			for(FootballClub club : footballClubList) {
				if(clubName.equals(club.getClubName())) {
					System.out.println(club);
					clubExists = true;
					break;
				}
			}
			if(!clubExists) {
				System.out.println("Club does not exist!!!");
			}
		}
		else {
			System.out.println("No clubs found!!!");
		}
	}

	@Override
	public void displayTable() {
		if(!footballClubList.isEmpty()){
			Collections.sort(footballClubList, Collections.reverseOrder());
			System.out.println("-------------------------------------------------------------");
			System.out.printf("%-20s %-10s %-10s %-10s %-10s", "Club Name", "Wins", "Losses", "Draws", "Points");
			System.out.println();
			System.out.println("-------------------------------------------------------------");
			for(FootballClub club : footballClubList) {
				System.out.format("%-20s %-10s %-10s %-10s %-10s", club.getClubName(), club.getWins(), club.getDefeats(), club.getDraws(), club.getClubPoints());
				System.out.println();
			}
			System.out.println("-------------------------------------------------------------");
		}
		else {
			System.out.println("No clubs found!!!");
		}
	}

	@Override
	public void addMatch(Match match) {
		System.out.println(match);
	}

	@Override
	public void saveToFile() {}

	@Override
	public void loadFromFile() {}
}