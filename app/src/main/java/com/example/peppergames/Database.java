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
    static final User Pavan = new User("Pavan Yekbote", 0, 3, "I'm a freshman. " +
            "I have played football for about 3-4 years now on a regular basis. I'm looking to make some friends and have fun!",
            22, 22, "Football", "22/01/2202");
    static final User Avram = new User("Avram", 5, 2, "I'm a freshman. " +
            "I have played football for about 3-4 years now on a regular basis. I'm looking to make some friends and have fun!",
            22, 22, "Football", "22/01/2202");
    static final User Pratt = new User("Pratt", 2, 1, "I'm a freshman. " +
            "I have played football for about 3-4 years now on a regular basis. I'm looking to make some friends and have fun!",
            22, 22, "Football", "22/01/2202");
    static final User Nora = new User("Nora", 2, 4, "I'm a freshman. " +
            "I have played football for about 3-4 years now on a regular basis. I'm looking to make some friends and have fun!",
            22, 22, "Football", "22/01/2202");
    static final User Smith = new User("Smith", 4, 3, "I'm a freshman. " +
            "I have played football for about 3-4 years now on a regular basis. I'm looking to make some friends and have fun!",
            22, 22, "Football", "22/01/2202");
    static final Map<PositionEnum, User> homePositions = new HashMap<>();
    static final Map<PositionEnum, User> awayPositions = new HashMap<>();
    static final Map<TeamEnum, Map<PositionEnum, User>> positions = new HashMap<>();
    static final List<Event> events = new ArrayList<>();
    static final Event amateurGame = new Event(
            "Basketball", 2, 3, "11th Nov 2022", 6,
            "ARC", false, positions);

    static final Event proGame = new Event(
            "Football", 5, 4, "12th Nov 2022", 3
            , "ARC", true, positions);

    static {
        homePositions.put(PositionEnum.GK, Avram);
        homePositions.put(PositionEnum.RST, Pratt);
        homePositions.put(PositionEnum.CM, Nora);
        awayPositions.put(PositionEnum.LST, Pavan);
        awayPositions.put(PositionEnum.CM, Smith);
        positions.put(TeamEnum.HOME, homePositions);
        positions.put(TeamEnum.AWAY, awayPositions);
        events.add(amateurGame);
        events.add(proGame);
    }

}
