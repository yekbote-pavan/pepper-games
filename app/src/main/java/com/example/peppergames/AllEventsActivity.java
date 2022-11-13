package com.example.peppergames;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.peppergames.dto.Event;

public class AllEventsActivity extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);
        LinearLayout layout = findViewById(R.id.event_outer_layout);
        layout.removeAllViews();

        CardView cardView;
        int eventIndex = 0;
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

            textView = cardView.findViewById(R.id.event_skill);
            textView.setText(String.format("Conduct: %d", event.getConductRating()));

            textView = cardView.findViewById(R.id.event_date_time);
            textView.setText(event.getDate());

            textView = cardView.findViewById(R.id.event_players);
            textView.setText(String.format("%d/%d", Database.getCurrentPlayers(eventIndex), event.getMaxPlayers()));

            textView = cardView.findViewById(R.id.event_location);
            textView.setText(event.getLocation());

            int finalEventIndex = eventIndex;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AllEventsActivity.this, GameOverview.class);
                    intent.putExtra("event_index", finalEventIndex);
                    intent.putExtra("is_join_allowed", true);
                    startActivity(intent);
                }
            });

            layout.addView(cardView);
            eventIndex++;
        }
    }
}