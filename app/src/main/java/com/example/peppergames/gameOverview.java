package com.example.peppergames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.peppergames.dto.TeamEnum;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.view.Gravity;
import android.view.ViewGroup;


public class gameOverview extends EventsActivity {

    LinearLayout get_player_card(String name, int skills, int conduct){

        LinearLayout linearLayout_card = new LinearLayout(this);
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout_card.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_card.setBackgroundResource(R.drawable.square_border);
        linearLayout_card.setPadding(4,4,4,4);
        linearLayout_card.setLayoutParams(linearParams);

        // incomplete - get the whole relative layout and replicate this can be done instead.

    }
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
        textView.setText(events.get(game_idx).getGame());

        textView = findViewById(R.id.textView_skills_value);
        textView.setText(events.get(game_idx).getSkillRating());

        textView = findViewById(R.id.textView_conduct_value);
        textView.setText(events.get(game_idx).getConductRating());

        textView = findViewById(R.id.textView_timendate_value);
        textView.setText(events.get(game_idx).getDate());

        textView = findViewById(R.id.textView_location_value);
        textView.setText(events.get(game_idx).getLocation());

        textView = findViewById(R.id.game_overview_player_count);
        //home + away players
        cur_players = events.get(game_idx).getTeamPositions().get(TeamEnum.HOME).size() + events.get(game_idx).getTeamPositions().get(TeamEnum.AWAY).size();
        textView.setText(String.format("%d/%d", cur_players, events.get(game_idx).getMaxPlayers()));

        // Dynamic scroll view
        ScrollView scrollView = findViewById(R.id.game_overview_scroll_view);

        String name  = "pratt";
        int conduct = 4, skills = 3;
        LinearLayout temp_linear_layout = get_player_card(name, skills, conduct);


    }

}