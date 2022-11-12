package com.example.peppergames;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import android.widget.TextView;

import com.example.peppergames.dto.PositionEnum;
import com.example.peppergames.dto.TeamEnum;
import com.example.peppergames.dto.User;

public class gameOverview extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_overview);

        // Get the activity which was selected before this in the last screen somehow
        // for now we'll take the game at index 0
        int game_idx = 0;
        int cur_players = 0;
        //set game info attributes based on game_idx
        TextView textView = findViewById(R.id.textView_sport_name);
        textView.setText(Database.events.get(game_idx).getGame());

        textView = findViewById(R.id.textView_skills_value);
        textView.setText(String.valueOf(Database.events.get(game_idx).getSkillRating()));

        textView = findViewById(R.id.textView_conduct_value);
        textView.setText(String.valueOf(Database.events.get(game_idx).getConductRating()));

        textView = findViewById(R.id.textView_timendate_value);
        textView.setText(String.valueOf(Database.events.get(game_idx).getDate()));

        textView = findViewById(R.id.textView_location_value);
        textView.setText(String.valueOf(Database.events.get(game_idx).getLocation()));

        textView = findViewById(R.id.game_overview_player_count);
        //home + away players
        cur_players = Objects.requireNonNull(Database.positions.get(TeamEnum.HOME)).size() + Objects.requireNonNull(Database.positions.get(TeamEnum.AWAY)).size();
        textView.setText(String.format("%d/%d", cur_players, Database.events.get(game_idx).getMaxPlayers()));

        // Dynamic scroll view
        RecyclerView rv = findViewById(R.id.game_overview);
        StaggeredGridLayoutManager gs = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(gs);

        List<GO_Player> mn = new ArrayList<>();
        for (Map.Entry<PositionEnum, User> set :
                Database.positions.get(TeamEnum.HOME).entrySet()){
            String cur_name = set.getValue().getName();
            int cur_conduct = set.getValue().getConductRating();
            int cur_skill = set.getValue().getSkillRating();
            GO_Player player_prof = new GO_Player(cur_name, cur_conduct, cur_skill);
            mn.add(player_prof);
        }
        for (Map.Entry<PositionEnum, User> set :
                Database.positions.get(TeamEnum.AWAY).entrySet()){
            String cur_name = set.getValue().getName();
            int cur_conduct = set.getValue().getConductRating();
            int cur_skill = set.getValue().getSkillRating();
            GO_Player player_prof = new GO_Player(cur_name, cur_conduct, cur_skill);
            mn.add(player_prof);
        }
        GO_PlayerListAdapter adapter = new GO_PlayerListAdapter(mn, this);
        adapter.updateData(mn);
        rv.setAdapter(adapter);
    }

}