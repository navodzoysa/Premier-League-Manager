import java.util.*;

public class PremierLeagueManager implements LeagueManager {
	private List<SportsClub> clubList = new ArrayList<>();

	@Override
	public void addClub(SportsClub newClub) {
		Boolean clubExists = false;
		for(SportsClub club : clubList) {
			if(club.equals(newClub)) {
				System.out.println("Club already exists!!!");
				clubExists = true;
				break;
			}
		}
		if(!clubExists) {
			clubList.add(newClub);
			System.out.println("Successfully added club");
		}
	}

	@Override
	public void deleteClub(String clubName) {
		for(SportsClub club : clubList) {
			if(clubName.toLowerCase().equals(club.getClubName().toLowerCase())) {
				clubList.remove(club);
				System.out.println("Successfully removed club");
				break;
			}
		}
	}

	@Override
	public void displayStatistics(String clubName) {
		Boolean clubExists = false;
		if(!clubList.isEmpty()){
			for(SportsClub club : clubList) {
				if(clubName.toLowerCase().equals(club.getClubName().toLowerCase())) {
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
		if(!clubList.isEmpty()){
			Collections.sort(clubList, Collections.reverseOrder());
			System.out.println("-------------------------------------------------------------------------");
			System.out.printf("%-20s %-7s %-7s %-7s %-7s %-7s %-7s %-7s", "Club Name", "W", "L", "D", "GS", "GR", "GD", "P");
			System.out.println();
			System.out.println("-------------------------------------------------------------------------");
			for(SportsClub club : clubList) {
				System.out.format("%-20s %-7s %-7s %-7s %-7s %-7s %-7s %-7s", 
							((FootballClub)club).getClubName(), 
							((FootballClub)club).getWins(), 
							((FootballClub)club).getDefeats(), 
							((FootballClub)club).getDraws(),
							((FootballClub)club).getGoalsScored(),
							((FootballClub)club).getGoalsReceived(),
							((FootballClub)club).getGoalDifference(), 
							((FootballClub)club).getClubPoints());
				System.out.println();
			}
			System.out.println("-------------------------------------------------------------------------");
		}
		else {
			System.out.println("No clubs found!!!");
		}
	}

	@Override
	public void addMatch(Match match) {
		System.out.println(match);
		System.out.println("Successfully added match");
	}

	@Override
	public void saveToFile() {}

	@Override
	public void loadFromFile() {}

	public boolean isClubListEmpty() {
		return clubList.isEmpty();
	}

	public int clubListLength() {
		return clubList.size();
	}

	public boolean checkForClub(String clubName) {
		for(SportsClub club : clubList) {
			if(club.getClubName().toLowerCase().equals(clubName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public SportsClub getClubFromList(String clubName) {
		if(checkForClub(clubName)) {
			for(SportsClub club : clubList) {
				if(club.getClubName().toLowerCase().equals(clubName.toLowerCase())) {
					return club;
				}
			}
		}
		return null;
	}

	public void displayClubNames() {
		for(SportsClub club : clubList) {
			System.out.println(club.getClubName());
		}
	}
}