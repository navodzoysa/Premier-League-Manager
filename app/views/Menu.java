package views;

import services.PremierLeagueManager;
import models.*;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	private static PremierLeagueManager premierLeague = new PremierLeagueManager();
	private static Scanner scanner = new Scanner(System.in);
	private static Process process = null;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		premierLeague.loadFromFile();
		System.out.println("Welcome to the Premier League Manager");
		
		menuLoop:
		while(true){
			System.out.println("\nPlease select and option from the following");
			System.out.println("Press 1 to create a football club and add to the premier league");
			System.out.println("Press 2 to delete a football club from the premier league");
			System.out.println("Press 3 to display statistics of a football club");
			System.out.println("Press 4 to display the premier league table");
			System.out.println("Press 5 to add a match played between two football clubs");
			System.out.println("Press 6 to open the premier league table in GUI");
			System.out.println("Press 7 to save and exit");

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
					ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "sbt run");
					process = processBuilder.start();
					BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
					String line = "";
					while ((line = reader.readLine()) != null) {
						if(reader.readLine().isEmpty()) {
							break;
						}
						System.out.println(line);
					}
					reader.close();
					break;
				case 7:
					premierLeague.saveToFile();
					if(process != null) {
						BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
						writer.write("Ctrl+C");
						writer.newLine();
						writer.flush();
						writer.close();
						process.destroyForcibly();
					}
					break menuLoop;
				default:
					System.out.println("Invalid Input!!! Please select a valid number from the menu");
			}
		}
	}

	public static void addClub() {
			try{
				System.out.println("\nEnter the name of the club");
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
			System.out.println("No clubs found in the premier league!!! Please add atleast one club to continue.");
			return;
		}
		premierLeague.displayClubNames();
		System.out.println("Enter the name of the club to delete from the above list");
		String clubNameDelete = scanner.nextLine();
		if(!premierLeague.checkForClub(clubNameDelete)) {
			System.out.println(clubNameDelete +" does not exist in the premier league.");
			return;
		}
		System.out.println("Warning! You are about to delete " + clubNameDelete + ". Please confirm(y/n).");
		String userChoice = scanner.nextLine().toLowerCase();
		switch(userChoice) {
			case "y":
				premierLeague.deleteClub(clubNameDelete);
				System.out.println("Successfully removed " + clubNameDelete + " from the premier league.");
				break;
			default:
				System.out.println("Deletion Aborted! "+ clubNameDelete + " was not removed from the premier league.");
		}
	}	

	public static void displayClub() {
		if(premierLeague.isClubListEmpty()) {
			System.out.println("No clubs found in the premier league!!! Please add atleast one club to continue.");
			return;
		}
		premierLeague.displayClubNames();
		System.out.println("Enter the name of the club from the above list to see statistics");
		String clubNameFind = scanner.nextLine();
		if(!premierLeague.checkForClub(clubNameFind)) {
			System.out.println(clubNameFind +" does not exist in the premier league.");
			return;
		}
		premierLeague.displayStatistics(clubNameFind);
	}

	public static void displayTable() {
		if(premierLeague.isClubListEmpty()) {
			System.out.println("No clubs found in the premier league!!! Please add atleast one club to continue.");
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
			System.out.println(teamA + " does not exist in the premier league.");
			return;
		}
		System.out.println("Enter the away team from the above club list");
		String teamB = scanner.nextLine();
		if(!premierLeague.checkForClub(teamB)) {
			System.out.println(teamB + " does not exist in the premier league.");
			return;
		}
		SportsClub clubA = premierLeague.getClubFromList(teamA);
		SportsClub clubB = premierLeague.getClubFromList(teamB);
		if(clubA.equals(clubB)) {
			System.out.println("Cannot play against the same team!!! Please select two separate teams.");
			return;
		}

		System.out.println("Enter the goals scored by " + teamA);
		int teamAScore = scanner.nextInt();
		System.out.println("Enter the goals scored by " + teamB);
		int teamBScore = scanner.nextInt();
		while(true) {
			System.out.println("Enter the day(DD)");
			int day = scanner.nextInt();
			if(day <= 0 || day > 31) {
				System.out.println("Invalid day! Please enter a valid day");
				continue;
			}
			System.out.println("Enter the month(MM)");
			int month = scanner.nextInt();
			if(month <= 0 || month > 12) {
				System.out.println("Invalid month! Please enter a valid month");
				continue;
			}
			System.out.println("Enter the year(YYYY)");
			int year = scanner.nextInt();
			if(year < 1990) {
				System.out.println("Invalid year! Please enter a valid year");
				continue;
			}
			System.out.println("Enter the hour of the match in 24 hour format(HH)");
			int hour = scanner.nextInt();
			if(hour < 0 || hour > 23) {
				System.out.println("Invalid hour! Please enter a valid hour");
				continue;
			}
			System.out.println("Enter the minutes of the match(MM)");
			int minute = scanner.nextInt();
			if(minute < 0 || minute > 59) {
				System.out.println("Invalid minute! Please enter a valid minute");
				continue;
			}
			if(validateDate(day, month, year)) {
				DateTime matchDate = new DateTime(day, month, year, hour, minute);
				Match match = new Match(clubA, clubB, teamAScore, teamBScore, matchDate);
				premierLeague.addMatch(match);
			}
			break;
		}
	}

	public static boolean validateDate(int day ,int month ,int year) {
		Boolean leapYear = false;
		Boolean isValidDate = false;
		if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			leapYear = true;
		}
		if(leapYear && month == 2) {
			if(day > 0 && day <= 29) {
				return isValidDate = true;
			}
			else{
				System.out.println("Invalid day! Please enter a valid day.");
				return isValidDate = false;
			}
		}
		else if(!leapYear && month == 2) {
			if(day > 0 && day <= 28) {
				return isValidDate = true;
			}
			else{
				System.out.println("Invalid day! Please enter a valid day.");
				return isValidDate = false;
			}
		}
		else if(month == 4 || month == 6 || month == 9 || month == 11) {
			if(day > 0 && day <= 30) {
				return isValidDate = true;
			}
			else{
				System.out.println("Invalid day! Please enter a valid day.");
				return isValidDate = false;
			}
		}
		else{
			if(day > 0 && day <= 31) {
				return isValidDate = true;
			}
			else{
				System.out.println("Invalid day! Please enter a valid day.");
				return isValidDate = false;
			}
		}
	}
}
