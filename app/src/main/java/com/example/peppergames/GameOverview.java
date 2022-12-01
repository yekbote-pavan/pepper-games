package com.example.peppergames;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peppergames.dto.PositionEnum;
import com.example.peppergames.dto.TeamEnum;
import com.example.peppergames.dto.User;

public class GameOverview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_overview);
        int game_idx = getIntent().getIntExtra("event_index", -1);

        // Get the activity which was selected before this in the last screen somehow
        // for now we'll take the game at index 0
        int cur_players = 0;
        //set game info attributes based on game_idx
        TextView textView = findViewById(R.id.textView_sport_name);
        textView.setText(Database.getEvents().get(game_idx).getGame());

        textView = findViewById(R.id.textView_skills_value);
        textView.setText(String.valueOf(Database.getEvents().get(game_idx).getSkillRating()));

        textView = findViewById(R.id.textView_conduct_value);
        textView.setText(String.valueOf(Database.getEvents().get(game_idx).getConductRating()));

        textView = findViewById(R.id.textView_timendate_value);
        textView.setText(String.valueOf(Database.getEvents().get(game_idx).getDate()));

        textView = findViewById(R.id.textView_location_value);
        textView.setText(String.valueOf(Database.getEvents().get(game_idx).getLocation()));

        textView = findViewById(R.id.game_overview_player_count);
        //home + away players
        cur_players = Database.getCurrentPlayers(game_idx);
        textView.setText(String.format("%d/%d", cur_players, Database.getEvents().get(game_idx).getMaxPlayers()));

        Button joinButton = findViewById(R.id.game_overview_join_button);
        if (Database.getAppUser().getSkillRating() < Database.getEvents().get(game_idx).getSkillRating()
                || Database.getAppUser().getConductRating() < Database.getEvents().get(game_idx).getConductRating()) {
            joinButton.setClickable(false);
            joinButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Rating Too Low", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            joinButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(GameOverview.this, PickPosition.class);
                    intent.putExtra("event_index", game_idx);
                    startActivity(intent);
                }
            });
        }

        ImageButton shareButton = findViewById(R.id.game_overview_share_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Link copied to Clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        // Dynamic scroll view
        RecyclerView rv = findViewById(R.id.game_overview);
        StaggeredGridLayoutManager gs = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(gs);

        List<GO_Player> mn = new ArrayList<>();
        for (Map.Entry<TeamEnum, Map<PositionEnum, User>> gamePositions: Database.getEvents().get(game_idx).getTeamPositions().entrySet()) {
            for (Map.Entry<PositionEnum, User> teamPosition: gamePositions.getValue().entrySet()) {
                String cur_name = teamPosition.getValue().getName();
                int cur_conduct = teamPosition.getValue().getConductRating();
                int cur_skill = teamPosition.getValue().getSkillRating();
                GO_Player player_prof = new GO_Player(cur_name, cur_conduct, cur_skill);

//                String image = teamPosition.getValue().getProfileImage();
//                ImageView player_profile = findViewById(R.id.player_profile_image_0);
////                String current_player_profile_image_link = user.getProfileImage();
//                int current_player_profile_image = getResId(image, R.drawable.class);
//                player_profile.setImageResource(current_player_profile_image);

                mn.add(player_prof);
            }
        }

        GO_PlayerListAdapter adapter = new GO_PlayerListAdapter(mn, this);
        adapter.updateData(mn);
        rv.setAdapter(adapter);
    }

//    private static int getResId(String resName, Class<?> c) {
//
//        try {
//            Field idField = c.getDeclaredField(resName);
//            return idField.getInt(idField);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -1;
//        }
//    }

}