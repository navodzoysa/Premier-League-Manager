package models;

import java.util.Comparator;

public class WinComparator implements Comparator<SportsClub> {
    @Override
    public int compare(SportsClub clubA, SportsClub clubB) {
        return clubA.getWins() - clubB.getWins();
    }
}
