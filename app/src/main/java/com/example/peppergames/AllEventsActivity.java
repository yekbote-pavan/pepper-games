package com.example.peppergames;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.peppergames.dto.Event;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AllEventsActivity extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);
        LinearLayout layout = findViewById(R.id.event_outer_layout);
        layout.removeAllViews();

        CardView cardView;
//        toDo: generate events based on sorted value
        for (Event event : Database.getEvents()) {
            if (event.isPlaying()) {
                continue;
            }

            cardView = (CardView) getLayoutInflater().inflate(R.layout.event_card, null);

            TextView textView = cardView.findViewById(R.id.event_game);
            textView.setText(event.getGame());

            textView = cardView.findViewById(R.id.event_skill);
            textView.setText(String.format("Skills: %d", event.getSkillRating()));

            textView = cardView.findViewById(R.id.event_conduct);
            textView.setText(String.format("Conduct: %d", event.getConductRating()));

            textView = cardView.findViewById(R.id.event_date_time);
            textView.setText(event.getDate());

            textView = cardView.findViewById(R.id.event_players);
            textView.setText(String.format("%d/%d", Database.getCurrentPlayers(Database.getEventIndex(event)), event.getMaxPlayers()));

            textView = cardView.findViewById(R.id.event_location);
            textView.setText(event.getLocation());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AllEventsActivity.this, GameOverview.class);
                    intent.putExtra("event_index", Database.getEventIndex(event));
                    intent.putExtra("is_join_allowed", true);
                    startActivity(intent);
                }
            });

            layout.addView(cardView);
        }

        ImageButton button = findViewById(R.id.event_user_profile_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllEventsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllEventsActivity.this, CreateEventActivity.class);
                startActivity(intent);
            }
        });
    }
}