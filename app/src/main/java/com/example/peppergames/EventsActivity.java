package com.example.peppergames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.peppergames.dto.Event;
import com.example.peppergames.dto.PositionEnum;
import com.example.peppergames.dto.TeamEnum;
import com.example.peppergames.dto.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventsActivity extends AppCompatActivity {

    protected List<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User pavan = new User("Pavan Yekbote", 2, 3, "I'm a freshman. " +
                "I have played football for about 3-4 years now on a regular basis. I'm looking to make some friends and have fun!",
                22, 22, "Football", "22/01/2202");
        User avram = new User("Avram", 2, 3, "I'm a freshman. " +
                "I have played football for about 3-4 years now on a regular basis. I'm looking to make some friends and have fun!",
                22, 22, "Football", "22/01/2202");

        Map<PositionEnum, User> homePositions = new HashMap<>();
        homePositions.put(PositionEnum.GK, avram);

        Map<PositionEnum, User> awayPositions = new HashMap<>();
        awayPositions.put(PositionEnum.LST, pavan);

        Map<TeamEnum, Map<PositionEnum, User>> positions = new HashMap<>();
        positions.put(TeamEnum.HOME, homePositions);
        positions.put(TeamEnum.AWAY, awayPositions);
//        toDo: positions
        Event amateurGame = new Event(
                "Football", 2, 3, "11th Nov 2022", 6,
                 "ARC", false, positions);

        Event proGame = new Event(
                "Football", 5, 4, "12th Nov 2022", 3
                ,"ARC", true, positions);

        events.add(amateurGame);
        events.add(proGame);
    }
}