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

    private static final User appUser = new User("Pavan Yekbote", 3, 3,
            "I'm a freshman. " + "I have played football for about 3-4 years now on a regular basis. I'm looking to make some friends and have fun!",
            22, 22, "Football", "22/01/2012", "profile_image_2");

    private static boolean showRating = true;

    static {
        Map<PositionEnum, User> homePositions = new HashMap<>();
        Map<PositionEnum, User> awayPositions = new HashMap<>();
        Map<TeamEnum, Map<PositionEnum, User>> positions = new HashMap<>();

//        User Pavan = new User("Pavan Yekbote", 3, 5, "I'm a freshman. " +
//                "I have played football for about 3-4 years now on a regular basis. I'm looking to make some friends and have fun!",
//                22, 22, "Football", "22/01/2012");
        User Avram = new User("Avram", 5, 4, "I celebrate a victory when I start walking off the field. " +
                "By the time I get to the locker room, I’m done.",
                232, 2, "Football", "12/11/2021", "profile_image_1");
        User Pratt = new User("Pratt", 2, 1, "I figure practice puts your brains in your muscles.",
                45, 37, "Football", "03/05/2011", "profile_image_3");
        User Nora = new User("Nora", 2, 5, "hiii",
                5, 0, "Football", "22/01/2202", "profile_image_5");
        User Smith = new User("Smit", 4, 3, "In football, everything is complicated by the presence of the opposite team.",
                52, 9, "Football", "22/01/2202", "profile_image_4");

        homePositions.put(PositionEnum.GK, Avram);
        homePositions.put(PositionEnum.RST, Pratt);
        homePositions.put(PositionEnum.CM, Nora);

//        awayPositions.put(PositionEnum.LST, Pavan);
        awayPositions.put(PositionEnum.CM, Smith);

        positions.put(TeamEnum.HOME, homePositions);
        positions.put(TeamEnum.AWAY, awayPositions);

        Event amateurGame = new Event(
                "Football", 1, 1, "11 AM, 19 Nov", 12,
                "ARC", false, positions);


        Map<PositionEnum, User> homePositions2 = new HashMap<>();
        homePositions2.put(PositionEnum.GK, Avram);
        homePositions2.put(PositionEnum.RST, Pratt);
        homePositions2.put(PositionEnum.CM, Nora);

        Map<PositionEnum, User> awayPositions2 = new HashMap<>();
        awayPositions2.put(PositionEnum.CM, Smith);
        awayPositions2.put(PositionEnum.GK, getAppUser());

        Map<TeamEnum, Map<PositionEnum, User>> positions2 = new HashMap<>();
        positions2.put(TeamEnum.HOME, homePositions2);
        positions2.put(TeamEnum.AWAY, awayPositions2);
        Event proGame = new Event(
                "Football", 5, 4, "07 PM, 19 Nov", 12
                , "ARC", true, positions2);

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
    public static Map<PositionEnum, User> getHomeTeam(int eventIndex){
        Event event = getEvents().get(eventIndex);
        Map<PositionEnum, User> positionsMap = event.getTeamPositions().get(TeamEnum.HOME);
        return positionsMap;
    }
    public static User getAppUser() {
        return appUser;
    }

    public static void addEvent(Event event) {
        events.add(event);
    }

    public static boolean isShowRating() {
        return showRating;
    }
    public static void setRatingToFalse() {
        showRating = false;
    }
}
