package com.example.peppergames;

import com.example.peppergames.dto.Event;
import com.example.peppergames.dto.PositionEnum;
import com.example.peppergames.dto.TeamEnum;
import com.example.peppergames.dto.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    private static final List<Event> events = new ArrayList<>();

    private static final User appUser = new User("Pavan Yekbote", 2, 3,
            "I'm a freshman. " + "I have played football for about 3-4 years now on a regular basis. I'm looking to make some friends and have fun!",
            22, 22, "Football", "22/01/2202");

    static {
        Map<PositionEnum, User> homePositions = new HashMap<>();
        Map<PositionEnum, User> awayPositions = new HashMap<>();
        Map<TeamEnum, Map<PositionEnum, User>> positions = new HashMap<>();

        User Pavan = new User("Pavan Yekbote", 0, 3, "I'm a freshman. " +
                "I have played football for about 3-4 years now on a regular basis. I'm looking to make some friends and have fun!",
                22, 22, "Football", "22/01/2202");
        User Avram = new User("Avram", 5, 2, "I'm a freshman. " +
                "I have played football for about 3-4 years now on a regular basis. I'm looking to make some friends and have fun!",
                22, 22, "Football", "22/01/2202");
        User Pratt = new User("Pratt", 2, 1, "I'm a freshman. " +
                "I have played football for about 3-4 years now on a regular basis. I'm looking to make some friends and have fun!",
                22, 22, "Football", "22/01/2202");
        User Nora = new User("Nora", 2, 4, "I'm a freshman. " +
                "I have played football for about 3-4 years now on a regular basis. I'm looking to make some friends and have fun!",
                22, 22, "Football", "22/01/2202");
        User Smith = new User("Smith", 4, 3, "I'm a freshman. " +
                "I have played football for about 3-4 years now on a regular basis. I'm looking to make some friends and have fun!",
                22, 22, "Football", "22/01/2202");

        homePositions.put(PositionEnum.GK, Avram);
        homePositions.put(PositionEnum.RST, Pratt);
        homePositions.put(PositionEnum.CM, Nora);

        awayPositions.put(PositionEnum.LST, Pavan);
        awayPositions.put(PositionEnum.CM, Smith);

        positions.put(TeamEnum.HOME, homePositions);
        positions.put(TeamEnum.AWAY, awayPositions);

        Event amateurGame = new Event(
                "Basketball", 2, 3, "06 AM, 11 Nov 2022", 12,
                "ARC", false, positions);

        Event proGame = new Event(
                "Football", 5, 4, "07 AM, 12 Nov 2022", 12
                , "ARC", true, positions);

        events.add(amateurGame);
        events.add(proGame);
    }

    public static List<Event> getEvents() {
        return events;
    }

    public static int getCurrentPlayers(int eventIndex) {
        int currentPlayers = 0;
        Event event = getEvents().get(eventIndex);
        for (TeamEnum key: event.getTeamPositions().keySet()) {
            currentPlayers += event.getTeamPositions().get(key).keySet().size();
        }

        return currentPlayers;
    }

    public static int getEventIndex(Event event) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).equals(event)) {
                return i;
            }
        }

        return -1;
    }

    public static User getAppUser() {
        return appUser;
    }

    public static void addEvent(Event event) {
        events.add(event);
    }
}
