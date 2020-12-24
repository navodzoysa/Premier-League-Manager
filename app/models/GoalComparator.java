package models;

import java.util.Comparator;

public class GoalComparator implements Comparator<SportsClub> {
    @Override
    public int compare(SportsClub clubA, SportsClub clubB) {
        return ((FootballClub) clubA).getGoalsScored() - ((FootballClub) clubB).getGoalsScored();
    }
}
