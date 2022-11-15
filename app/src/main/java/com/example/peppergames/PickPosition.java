package com.example.peppergames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.peppergames.dto.Event;
import com.example.peppergames.dto.PositionEnum;
import com.example.peppergames.dto.TeamEnum;
import com.example.peppergames.dto.User;

import java.util.HashMap;
import java.util.Map;


public class PickPosition extends AppCompatActivity {

    private Event current_event;
    private ImageButton current_button;
    private Map<PositionEnum, String> position_dict = new HashMap<PositionEnum, String>(){{
        put(PositionEnum.CM, "centralMidfielder");
        put(PositionEnum.GK, "goalkeeper");
        put(PositionEnum.LW, "leftWing");
        put(PositionEnum.RW, "rightWing");
        put(PositionEnum.LST, "leftStriker");
        put(PositionEnum.RST, "rightStriker");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_position);
        Intent intent = getIntent();
        int eventIndex = intent.getIntExtra("event_index", -1);

        current_event = Database.getEvents().get(eventIndex);
        EditText text = findViewById(R.id.game_type);
        text.setText(current_event.getGame());

        text = findViewById(R.id.game_date);
        text.setText(current_event.getDate());

        text = findViewById(R.id.position_game_skill_rating);
        text.setText(String.format("Skills: %d", current_event.getSkillRating()));

        text = findViewById(R.id.position_game_conduct_rating);
        text.setText(String.format("Conduct: %d", current_event.getConductRating()));

        text = findViewById(R.id.position_game_location);
        text.setText(current_event.getLocation());

        // update occupied positions with a different image: ic_round_person
        Map<PositionEnum, User> homeTeam = current_event.getTeamPositions().get(TeamEnum.HOME);
        for (Map.Entry<PositionEnum, User> entry : homeTeam.entrySet()){
            PositionEnum pos = entry.getKey();
            User user = entry.getValue();
            String id_str = position_dict.get(pos) + "1";
            int id = getResources().getIdentifier(id_str, "id", getPackageName());
            current_button = (ImageButton) findViewById(id);
            current_button.setImageResource(R.drawable.ic_round_person);
        }

        Map<PositionEnum, User> awayTeam = current_event.getTeamPositions().get(TeamEnum.AWAY);
        for (Map.Entry<PositionEnum, User> entry : awayTeam.entrySet()){
            PositionEnum pos = entry.getKey();
            User user = entry.getValue();
            String id_str = position_dict.get(pos) + "2";
            int id = getResources().getIdentifier(id_str, "id", getPackageName());
            current_button = (ImageButton) findViewById(id);
            current_button.setImageResource(R.drawable.ic_round_person);
        }
    }
}