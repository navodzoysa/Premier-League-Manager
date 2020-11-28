import java.util.*;
import java.io.IOException;

public class Menu {
	private static PremierLeagueManager premierLeague = new PremierLeagueManager();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		premierLeague.loadFromFile("clubObjects.txt");
		System.out.println("Welcome to the Premier League Manager");
		
		menuLoop:
		while(true){
			System.out.println("\nPlease select and option from the following");
			System.out.println("Press 1 to create a football club and add to the premier league");
			System.out.println("Press 2 to delete a football club from the premier league");
			System.out.println("Press 3 to display statistics of a football club");
			System.out.println("Press 4 to display the premier league table");
			System.out.println("Press 5 to add a match played between two football clubs");
			System.out.println("Press 6 to save and exit");

			int userChoice = 0;
			try {
				userChoice = scanner.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.print("");
			}
			scanner.nextLine();
			switch(userChoice) {
				case 1:
					addClub();
					break;
				case 2:
					deleteClub();
					break;
				case 3:
					displayClub();
					break;
				case 4:
					displayTable();
					break;
				case 5:
					addMatch();
					break;
				case 6:
					premierLeague.saveToFile("clubObjects.txt");
					break menuLoop; 
				default:
					System.out.println("Invalid Input!!! Please select a valid number from the menu");
			}
		}
	}

	public static void addClub() {
			try{
				System.out.println("Enter the name of the club");
				String clubName = scanner.nextLine();
				if(premierLeague.checkForClub(clubName)) {
					System.out.println("\n" + clubName + " is already in the premier league");
					return;
				}
				System.out.println("Enter the location of the club");
				String clubLocation = scanner.nextLine();
				SportsClub club = new FootballClub(clubName, clubLocation);
				premierLeague.addClub(club);
			}
			catch(InputMismatchException e) {
				System.out.println("\nInput Invalid!!! Please enter valid characters");
				scanner.nextLine();
			}
	}

	public static void deleteClub() {
		if(premierLeague.isClubListEmpty()) {
			System.out.println("No clubs found in the premier league!!! Please add atleast one club to continue");
			return;
		}
		premierLeague.displayClubNames();
		System.out.println("Enter the name of the club to delete from the above list");
		String clubNameDelete = scanner.nextLine();
		if(!premierLeague.checkForClub(clubNameDelete)) {
			System.out.println(clubNameDelete +" does not exist in the premier league");
			return;
		}
		premierLeague.deleteClub(clubNameDelete);
	}	

	public static void displayClub() {
		if(premierLeague.isClubListEmpty()) {
			System.out.println("No clubs found in the premier league!!! Please add atleast one club to continue");
			return;
		}
		premierLeague.displayClubNames();
		System.out.println("Enter the name of the club from the above list to see statistics");
		String clubNameFind = scanner.nextLine();
		if(!premierLeague.checkForClub(clubNameFind)) {
			System.out.println(clubNameFind +" does not exist in the premier league");
			return;
		}
		premierLeague.displayStatistics(clubNameFind);
	}

	public static void displayTable() {
		if(premierLeague.isClubListEmpty()) {
			System.out.println("No clubs found in the premier league!!! Please add atleast one club to continue");
			return;
		}
		premierLeague.displayTable();
	}

	public static void addMatch() {
		if(premierLeague.clubListLength() < 2) {
			System.out.println("Please add atleast two clubs to add a match!!");
			return;
		}
		premierLeague.displayClubNames();
		System.out.println("\nEnter the home team from the above club list");
		String teamA = scanner.nextLine();
		if(!premierLeague.checkForClub(teamA)) {
			System.out.println(teamA + " does not exist in the premier league");
			return;
		}
		System.out.println("Enter the away team from the above club list");
		String teamB = scanner.nextLine();
		if(!premierLeague.checkForClub(teamB)) {
			System.out.println(teamB + " does not exist in the premier league");
			return;
		}
		SportsClub clubA = premierLeague.getClubFromList(teamA);
		SportsClub clubB = premierLeague.getClubFromList(teamB);
		if(clubA.equals(clubB)) {
			System.out.println("Cannot play against the same team!!! Please select two separate teams");
			return;
		}

		System.out.println("Enter the goals scored by " + teamA);
		int teamAScore = scanner.nextInt();
		System.out.println("Enter the goals scored by " + teamB);
		int teamBScore = scanner.nextInt();
		System.out.println("Enter the day(DD)");
		int day = scanner.nextInt();
		System.out.println("Enter the month(MM)");
		int month = scanner.nextInt();
		System.out.println("Enter the year(YYYY)");
		int year = scanner.nextInt();
		System.out.println("Enter the hour of the match(HH)");
		int hour = scanner.nextInt();
		System.out.println("Enter the minutes of the match(MM)");
		int minute = scanner.nextInt();
		DateTime matchDate = new DateTime(day, month, year, hour, minute);
		Match match = new Match(clubA, clubB, teamAScore, teamBScore, matchDate);
		premierLeague.addMatch(match);
	}
}