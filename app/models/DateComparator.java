package models;

import java.util.Comparator;

public class DateComparator implements Comparator<Match> {
    @Override
    public int compare(Match matchA, Match matchB) {
        if(matchA.getMatchDate().getYear() == matchB.getMatchDate().getYear()) {
            if(matchA.getMatchDate().getMonth() == matchB.getMatchDate().getMonth()) {
                if(matchA.getMatchDate().getDay() == matchB.getMatchDate().getDay()) {
                    if(matchA.getMatchDate().getHour() == matchB.getMatchDate().getHour()) {
                        return matchA.getMatchDate().getMinute() - matchB.getMatchDate().getMinute();
                    }
                    return matchA.getMatchDate().getHour() - matchB.getMatchDate().getHour();
                }
                return matchA.getMatchDate().getDay() - matchB.getMatchDate().getDay();
            }
            return matchA.getMatchDate().getMonth() - matchB.getMatchDate().getMonth();
        }
        return matchA.getMatchDate().getYear() - matchB.getMatchDate().getYear();
    }
}
