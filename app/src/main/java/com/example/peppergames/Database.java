package com.example.peppergames;

import android.util.Log;

import com.example.peppergames.dto.Event;
import com.example.peppergames.dto.PositionEnum;
import com.example.peppergames.dto.TeamEnum;
import com.example.peppergames.dto.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    public static List<Event> events = new ArrayList<>();

    private static final User appUser = new User("Pavan Yekbote", 2, 2,
            "I'm a freshman. " + "I have played football for about 3-4 years now on a regular basis. I'm looking to make some friends and have fun!",
            22, 22, "Football", "22/01/2012", "profile_image_2");

    private static boolean showRating = true;

    static {
        Map<PositionEnum, User> homePositions = new HashMap<>();
        Map<PositionEnum, User> awayPositions = new HashMap<>();
        Map<TeamEnum, Map<PositionEnum, User>> positions = new HashMap<>();

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
        awayPositions.put(PositionEnum.CM, Smith);
        awayPositions.put(PositionEnum.GK, getAppUser());

        positions.put(TeamEnum.HOME, homePositions);
        positions.put(TeamEnum.AWAY, awayPositions);

        Event playingAmateurGame = new Event(
                "Football", 1, 1, "09 AM, 19 Nov", 12,
                "ARC", true, positions);


        Map<PositionEnum, User> homePositions2 = new HashMap<>();
        homePositions2.put(PositionEnum.GK, Avram);
        homePositions2.put(PositionEnum.RST, Pratt);
        homePositions2.put(PositionEnum.CM, Nora);

        Map<PositionEnum, User> awayPositions2 = new HashMap<>();
        awayPositions2.put(PositionEnum.CM, Smith);

        Map<TeamEnum, Map<PositionEnum, User>> positions2 = new HashMap<>();
        positions2.put(TeamEnum.HOME, homePositions2);
        positions2.put(TeamEnum.AWAY, awayPositions2);

        Event proGameNA = new Event(
                "Football", 5, 4, "11 AM, 19 Nov", 12
                , "ARC", false, positions2);


        Map<PositionEnum, User> homePositions3 = new HashMap<>();
        homePositions3.put(PositionEnum.GK, Avram);
        homePositions3.put(PositionEnum.RST, Pratt);
        homePositions3.put(PositionEnum.CM, Nora);

        Map<PositionEnum, User> awayPositions3 = new HashMap<>();
        awayPositions3.put(PositionEnum.CM, Smith);

        Map<TeamEnum, Map<PositionEnum, User>> positions3 = new HashMap<>();
        positions3.put(TeamEnum.HOME, homePositions3);
        positions3.put(TeamEnum.AWAY, awayPositions3);

        Event joinAbleGame = new Event("Football", 2, 2, "06 AM, 18 Nov", 12, "CRCE", false, positions3);

        Map<PositionEnum, User> homePositions4 = new HashMap<>();
        homePositions4.put(PositionEnum.GK, Avram);
        homePositions4.put(PositionEnum.RST, Pratt);
        homePositions4.put(PositionEnum.CM, Nora);

        Map<PositionEnum, User> awayPositions4 = new HashMap<>();
        awayPositions4.put(PositionEnum.CM, Smith);

        Map<TeamEnum, Map<PositionEnum, User>> positions4 = new HashMap<>();
        positions4.put(TeamEnum.HOME, homePositions4);
        positions4.put(TeamEnum.AWAY, awayPositions4);

        Event basketball = new Event("Basketball", 3, 3, "05 AM, 18 Nov", 12, "CRCE", false, positions4);

        Map<PositionEnum, User> homePositions5 = new HashMap<>();
        Map<PositionEnum, User> awayPositions5 = new HashMap<>();
        Map<TeamEnum, Map<PositionEnum, User>> positions5 = new HashMap<>();

        homePositions5.put(PositionEnum.GK, Avram);
        homePositions5.put(PositionEnum.RST, Pratt);
        homePositions5.put(PositionEnum.CM, Nora);
        awayPositions5.put(PositionEnum.CM, Smith);
        awayPositions5.put(PositionEnum.GK, getAppUser());

        positions5.put(TeamEnum.HOME, homePositions5);
        positions5.put(TeamEnum.AWAY, awayPositions5);

        Event basketballPlaying = new Event(
                "Basketball", 2, 2, "07 AM, 18 Nov", 12,
                "ARC", true, positions5);

        events.add(playingAmateurGame);
        events.add(proGameNA);
        events.add(joinAbleGame);
        events.add(basketball);
        events.add(basketballPlaying);
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
        Log.println(Log.ASSERT, "event", String.valueOf(event.getDate()));
        return event.getTeamPositions().get(TeamEnum.HOME);
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
