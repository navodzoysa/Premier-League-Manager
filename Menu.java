import java.util.*;

public class Menu {
	private static PremierLeagueManager premierLeague = new PremierLeagueManager();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
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
				System.out.println("Input Mismatch!!! Please enter a number");
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
					break menuLoop; 
				default:
					System.out.println("Invalid Input!!! Please select a choice from the menu");
			}
		}
	}

	public static void addClub() {
		try{
			System.out.println("Enter the name of the club");
			String clubName = scanner.nextLine();
			System.out.println("Enter the location of the club");
			String clubLocation = scanner.nextLine();
			System.out.println("Enter the wins of the club");
			int clubWins = scanner.nextInt();
			System.out.println("Enter the draws of the club");
			int clubDraws = scanner.nextInt();
			System.out.println("Enter the defeats of the club");
			int clubDefeats = scanner.nextInt();
			System.out.println("Enter the goals received of the club");
			int clubGoalsReceived = scanner.nextInt();
			System.out.println("Enter the goals scored of the club");
			int clubScored = scanner.nextInt();
			System.out.println("Enter the points of the club");
			int clubPoints = scanner.nextInt();
			System.out.println("Enter the matches played of the club");
			int clubMatches = scanner.nextInt();
			SportsClub club = new FootballClub(clubName, clubLocation, clubWins, clubDraws, clubDefeats, clubGoalsReceived, clubScored, clubPoints, clubMatches);
			premierLeague.addClub(club);
		}
		catch(InputMismatchException e) {
			System.out.println("Input Mismatch");
		}
	}

	public static void deleteClub() {
		System.out.println("Enter the name of the club");
		String clubNameDelete = scanner.nextLine();
		premierLeague.deleteClub(clubNameDelete);
	}	

	public static void displayClub() {
		System.out.println("Enter the name of the club");
		String clubNameFind = scanner.nextLine();
		premierLeague.displayStatistics(clubNameFind);
	}

	public static void displayTable() {
		premierLeague.displayTable();
	}

	public static void addMatch() {
		premierLeague.displayClubNames();
		System.out.println("Enter the home team from the above club list");
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
		System.out.println("Enter the goals scored by " + teamA);
		int teamAScore = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter the goals scored by " + teamB);
		int teamBScore = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter the day, month, year(DD/MM/YYYY)");
		String date = scanner.nextLine();
		String[] dateArray = date.split("/", 3);
		System.out.println("Enter the time of the match(HH:MM)");
		String time = scanner.nextLine();
		String[] timeArray = time.split(":", 2);
		DateTime matchDate = new DateTime(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]), Integer.parseInt(timeArray[0]), Integer.parseInt(timeArray[1]));
		Match match = new Match(clubA, clubB, teamAScore, teamBScore, matchDate);
		premierLeague.addMatch(match);
		System.out.println();
	}
}