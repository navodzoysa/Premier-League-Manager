package services;

import models.*;
import java.util.*;
import java.io.*;

public class PremierLeagueManager implements Serializable, LeagueManager {
	private static List<SportsClub> clubList = new ArrayList<>();
	private static List<Match> matchList = new ArrayList<>();
	private Random random = new Random();
	private static PremierLeagueManager instance;

	public static PremierLeagueManager getInstance() throws IOException, ClassNotFoundException {
		if(instance == null) {
			instance = new PremierLeagueManager();
		}
		instance.loadFromFile();
		return instance;
	}

	@Override
	public void addClub(SportsClub newClub) {
		clubList.add(newClub);
		try {
			saveToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Successfully added club");
	}

	@Override
	public void deleteClub(String clubName) {
		for(SportsClub club : clubList) {
			if(clubName.toLowerCase().equals(club.getClubName().toLowerCase())) {
				clubList.remove(club);
				break;
			}
		}
		try {
			saveToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void displayStatistics(String clubName) {
		System.out.print("\n-------------------------------------");
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
			sortPoints();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.printf("%-20s %-7s %-7s %-7s %-7s %-7s %-7s %-7s %-7s", "Club Name", "MP", "W", "L", "D", "GS", "GR", "GD", "P");
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			for(SportsClub club : clubList) {
				System.out.format("%-20s %-7s %-7s %-7s %-7s %-7s %-7s %-7s %-7s", 
							club.getClubName(),
							club.getMatchesPlayed(),
							club.getWins(), 
							club.getDefeats(), 
							club.getDraws(),
							((FootballClub)club).getGoalsScored(),
							((FootballClub)club).getGoalsReceived(),
							((FootballClub)club).getGoalDifference(), 
							club.getClubPoints());
				System.out.println();
			}
			System.out.println("--------------------------------------------------------------------------------");
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
			System.out.println("\n" + teamA.getClubName() + " won against " + teamB.getClubName());
		}
		else if(match.getTeamAScore() < match.getTeamBScore()) {
			teamB.setWins(1);
			teamA.setDefeats(1);
			teamB.setClubPoints(3);
			System.out.println("\n" + teamB.getClubName() + " won against " + teamA.getClubName());
		}
		else if (match.getTeamAScore() == match.getTeamBScore()) {
			teamA.setDraws(1);
			teamB.setDraws(1);
			teamA.setClubPoints(1);
			teamB.setClubPoints(1);
			System.out.println("\n" + teamA.getClubName() + " and " + teamB.getClubName() + " ended in a tie");
		}

		teamA.setMatchesPlayed(1);
		teamB.setMatchesPlayed(1);
		matchList.add(match);
		try {
			saveToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("-------------------------------------");
		System.out.println(match);
		System.out.println("-------------------------------------");
		System.out.println("Successfully added match");
	}

	@Override
	public void saveToFile() throws IOException {
		FileOutputStream fileOutput = new FileOutputStream("app/database/data.ser");
		ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
		try{
			List<Object> objectList = new ArrayList<>();
			objectList.add(clubList);
			objectList.add(matchList);
			objectOutput.writeObject(objectList);
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
	public void loadFromFile() throws IOException, ClassNotFoundException {
		File file = new File("app/database/data.ser");
		if(file.length() != 0) {
			FileInputStream fileInput = new FileInputStream(file);
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);
			List<Object> objectList = new ArrayList<>();
			while (true) {
				try {
					objectList = (List<Object>) objectInput.readObject();
					clubList = (List<SportsClub>) objectList.get(0);
					matchList = (List<Match>) objectList.get(1);
					objectList.clear();
				} catch (FileNotFoundException e) {
					return;
				} catch (EOFException e) {
					System.out.println("All clubs have been loaded successfully\n");
					break;
				}
			}
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
		System.out.println("-------------------------------------");
	}

	public List<SportsClub> getClubList() {
		return Collections.unmodifiableList(clubList);
	}

	public List<Match> getMatchList() {
		return Collections.unmodifiableList(matchList);
	}

	public void sortPoints() {
		Collections.sort(clubList, Collections.reverseOrder());
	}

	public void sortGoals() {
		Collections.sort(clubList, new GoalComparator().reversed());
	}

	public void sortWins() {
		Collections.sort(clubList, new WinComparator().reversed());
	}

	public Match addRandomMatch() {
		int day = random.nextInt(30) + 1;
		int month = random.nextInt(12) + 1;
		int year = random.nextInt(2020-1990) + 1990;
		int hour = random.nextInt(22) + 1;
		int minute = random.nextInt(58) + 1;
		DateTime dateTime = new DateTime(day, month, year, hour, minute);

		while(true) {
			int teamAIndex = random.nextInt(clubListLength());
			int teamBIndex = random.nextInt(clubListLength());
			if(teamAIndex == teamBIndex) {
				continue;
			}
			else{
				SportsClub teamA = clubList.get(teamAIndex);
				SportsClub teamB = clubList.get(teamBIndex);
				Match match = new Match(teamA, teamB, generateScore(), generateScore(), dateTime);
				match.setIsMatchRandom(true);
				addMatch(match);
				return match;
			}
		}
	}

	public int generateScore() {
		int score;
		int chance = random.nextInt(1002);
		int rareChance = 1000;
		int averageChance = 500;

		if(chance > rareChance) {
			score = random.nextInt(8) + 5;
		}
		else if(chance > averageChance) {
			score = random.nextInt(3) + 1;
		}
		else{
			score = random.nextInt(2);
		}
		return score;
	}

	public void sortMatchDates() {
		Collections.sort(matchList, new DateComparator());
	}
}