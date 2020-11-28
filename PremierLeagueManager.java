import java.util.*;
import java.io.*;

public class PremierLeagueManager implements Serializable, LeagueManager {
	private List<SportsClub> clubList = new ArrayList<>();
	private List<Match> matchList = new ArrayList<>();

	@Override
	public void addClub(SportsClub newClub) {
		clubList.add(newClub);
		System.out.println("Successfully added club");
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
		System.out.print("-------------------------------------");
		for(SportsClub club : clubList) {
			if(clubName.toLowerCase().equals(club.getClubName().toLowerCase())) {
				System.out.println(club);
				break;
			}
		}
		System.out.println("-------------------------------------");
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
	}

	@Override
	public void addMatch(Match match) {
		FootballClub teamA = ((FootballClub)match.getTeamA());
		FootballClub teamB = ((FootballClub)match.getTeamB());

		teamA.setGoalsScored(match.getTeamAScore());
		teamB.setGoalsScored(match.getTeamBScore());
		teamA.setGoalsReceived(match.getTeamBScore());
		teamB.setGoalsReceived(match.getTeamAScore());

		if(match.getTeamAScore() > match.getTeamBScore()) {
			teamA.setWins(1);
			teamB.setDefeats(1);
			teamA.setClubPoints(3);
		}
		else if(match.getTeamAScore() < match.getTeamBScore()) {
			teamB.setWins(1);
			teamA.setDefeats(1);
			teamB.setClubPoints(3);
		}
		else if (match.getTeamAScore() == match.getTeamBScore()) {
			teamA.setDraws(1);
			teamB.setDraws(1);
			teamA.setClubPoints(1);
			teamB.setClubPoints(1);
		}

		matchList.add(match);
		System.out.println(match);
		System.out.println("Successfully added match");
	}

	@Override
	public void saveToFile(String fileName) throws IOException {
		FileOutputStream fileOutput = new FileOutputStream(fileName);
		ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
		
		try{
			for(SportsClub club : clubList) {
				objectOutput.writeObject(club);
			}
			System.out.println("All clubs have been saved successfully");
		}
		catch(FileNotFoundException e) {
			System.out.println("File Not found");
		}
		finally {
			objectOutput.close();
			fileOutput.close();
		}
		
	}

	@Override
	public void loadFromFile(String fileName) throws IOException, ClassNotFoundException {
		
		try{
			File file = new File(fileName);
			file.createNewFile();
			FileInputStream fileInput = new FileInputStream(fileName);
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);
			for(;;) {
				clubList.add((SportsClub) objectInput.readObject());
			}
		}
		catch(FileNotFoundException e) {
			return;
		}
		catch(EOFException e) {
			System.out.println("All clubs have been loaded successfully\n");
			return;
		}
	}

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
		System.out.println("\nList of clubs in the premier league");
		System.out.println("-------------------------------------");
		for(SportsClub club : clubList) {
			System.out.println(club.getClubName());
		}
		System.out.println("-------------------------------------\n");
	}
}