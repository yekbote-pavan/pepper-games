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
//        Map<PositionEnum, User> amateurPositions = new HashMap<>();
//        Map<TeamEnum, Map<PositionEnum, User>> positions = new HashMap<>();
//        positions.put(TeamEnum.HOME, )
//        toDo: positions
        Event amateurGame = new Event(
                "Football", 2, 3, "11th Nov 2022", 6,
                12, "ARC", false, null);

        Event proGame = new Event(
                "Football", 5, 4, "12th Nov 2022", 3,
                12, "ARC", true, null);

        events.add(amateurGame);
        events.add(proGame);
    }
}