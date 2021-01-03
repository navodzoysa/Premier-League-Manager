package services;

import models.DateTime;
import models.FootballClub;
import models.Match;
import models.SportsClub;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class PremierLeagueManagerTest {
    @Test
    public void testAddClub() {
        PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
        SportsClub club = new FootballClub("Liverpool", "Anfield");
        premierLeagueManager.addClub(club);
        premierLeagueManager.displayClubNames();
        assertEquals("Liverpool", premierLeagueManager.getClubFromList("Liverpool").getClubName());
    }

    @Test
    public void testDeleteClub() {
        PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
        SportsClub club = new FootballClub("Liverpool", "Anfield");
        premierLeagueManager.addClub(club);
        premierLeagueManager.displayClubNames();
        premierLeagueManager.deleteClub("Liverpool");
        premierLeagueManager.displayClubNames();
        assertFalse(premierLeagueManager.checkForClub("Liverpool"));
    }

    @Test
    public void testDisplayClub() {
        PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
        SportsClub club = new FootballClub("Liverpool", "Anfield");
        club.setWins(2);
        club.setDraws(0);
        club.setDefeats(1);
        club.setClubPoints(6);
        club.setMatchesPlayed(3);
        ((FootballClub)club).setGoalsReceived(4);
        ((FootballClub)club).setGoalsScored(5);
        premierLeagueManager.addClub(club);
        premierLeagueManager.displayStatistics("Liverpool");
    }

    @Test
    public void testDisplayTable() {
        PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
        SportsClub club = new FootballClub("Liverpool", "Anfield");
        club.setWins(2);
        club.setDraws(0);
        club.setDefeats(1);
        club.setClubPoints(6);
        club.setMatchesPlayed(3);
        ((FootballClub)club).setGoalsReceived(4);
        ((FootballClub)club).setGoalsScored(5);
        premierLeagueManager.addClub(club);
        premierLeagueManager.displayTable();
    }

    @Test
    public void testAddMatch() {
        PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
        SportsClub club1 = new FootballClub("Liverpool", "Anfield");
        club1.setWins(2);
        club1.setDraws(0);
        club1.setDefeats(1);
        club1.setClubPoints(6);
        club1.setMatchesPlayed(3);
        ((FootballClub)club1).setGoalsReceived(4);
        ((FootballClub)club1).setGoalsScored(5);
        premierLeagueManager.addClub(club1);

        SportsClub club2 = new FootballClub("Chelsea", "Stamford Bridge");
        club2.setWins(1);
        club2.setDraws(0);
        club2.setDefeats(2);
        club2.setClubPoints(3);
        club2.setMatchesPlayed(3);
        ((FootballClub)club2).setGoalsReceived(5);
        ((FootballClub)club2).setGoalsScored(2);
        premierLeagueManager.addClub(club2);
        Match match = new Match(club1, club2, 4, 2, new DateTime(4, 1, 2021, 1, 30));
        premierLeagueManager.addMatch(match);
    }

    @Test
    public void testSaveToFile() throws IOException, ClassNotFoundException {
        PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
        SportsClub club = new FootballClub("Liverpool", "Anfield");
        premierLeagueManager.displayClubNames();
        premierLeagueManager.addClub(club);
        premierLeagueManager.saveToFile();
        premierLeagueManager.displayClubNames();
        premierLeagueManager.loadFromFile();
        premierLeagueManager.displayClubNames();
    }

    @Test
    public void testLoadFromFile() throws IOException, ClassNotFoundException {
        PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
        premierLeagueManager.loadFromFile();
        premierLeagueManager.displayClubNames();
        SportsClub club = new FootballClub("Liverpool", "Anfield");
        premierLeagueManager.addClub(club);
        premierLeagueManager.saveToFile();
        premierLeagueManager.displayClubNames();
        premierLeagueManager.loadFromFile();
        premierLeagueManager.displayClubNames();
    }
}
