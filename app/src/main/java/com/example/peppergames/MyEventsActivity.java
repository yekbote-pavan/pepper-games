package com.example.peppergames;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.peppergames.dto.Event;
import com.example.peppergames.dto.PositionEnum;
import com.example.peppergames.dto.TeamEnum;
import com.example.peppergames.dto.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Map;

public class MyEventsActivity extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events);
        LinearLayout layout = findViewById(R.id.my_event_outer_layout);
        layout.removeAllViews();

        int myEventsCount = 0;
        for (Event event: Database.getEvents()) {
            if (event.isPlaying()) {
                myEventsCount++;
            }
        }

        Button myEventsButton = findViewById(R.id.my_events_counter);
        myEventsButton.setText(String.format("MY EVENTS (%d)", myEventsCount));

        View confirmLeave = getLayoutInflater().inflate(R.layout.leave_confirm_popup, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow confirmLeavePopup = new PopupWindow(confirmLeave, width, height, focusable);

        CardView cardView;
//        toDo: generate events based on sorted value
        for (Event event : Database.getEvents()) {
            if (!event.isPlaying()) {
                continue;
            }

            cardView = (CardView) getLayoutInflater().inflate(R.layout.my_event_card, null);

            TextView textView = cardView.findViewById(R.id.my_event_game);
            textView.setText(event.getGame());

            textView = cardView.findViewById(R.id.my_event_skill);
            textView.setText(String.format("Skills: %d", event.getSkillRating()));

            textView = cardView.findViewById(R.id.my_event_conduct);
            textView.setText(String.format("Conduct: %d", event.getConductRating()));

            textView = cardView.findViewById(R.id.my_event_date_time);
            textView.setText(event.getDate());

            textView = cardView.findViewById(R.id.my_event_players);
            textView.setText(String.format("%d/%d", Database.getCurrentPlayers(Database.getEventIndex(event)), event.getMaxPlayers()));

            textView = cardView.findViewById(R.id.my_event_location);
            textView.setText(event.getLocation());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MyEventsActivity.this, GameOverview.class);
                    intent.putExtra("event_index", Database.getEventIndex(event));
                    intent.putExtra("is_join_allowed", false);
                    startActivity(intent);
                }
            });

            Button button = cardView.findViewById(R.id.my_event_leave_button);
            Button confirmButton = confirmLeave.findViewById(R.id.leave_confirm_popup_yes);
            confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    event.setPlaying(false);
                    for (TeamEnum key: event.getTeamPositions().keySet()) {
                        Map<PositionEnum, User> positions = event.getTeamPositions().get(key);
                        PositionEnum appUserKey = null;
                        for (PositionEnum positionKey: positions.keySet()) {
                            if (positions.get(positionKey).equals(Database.getAppUser())) {
                                appUserKey = positionKey;
                                break;
                            }
                        }

                        positions.remove(appUserKey);
                    }

                    finish();
                    startActivity(getIntent());
                }
            });

            Button cancelButton = confirmLeave.findViewById(R.id.leave_confirm_popup_cancel);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmLeavePopup.dismiss();
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmLeavePopup.showAtLocation(confirmLeave, Gravity.CENTER_VERTICAL, 0, 0);
                }
            });

            layout.addView(cardView);
        }

        ImageButton button = findViewById(R.id.my_event_user_profile_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyEventsActivity.this, ProfileActivity.class);
                startActivity(intent);
                Database.setRatingToFalse();
            }
        });

        FloatingActionButton fab = findViewById(R.id.my_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyEventsActivity.this, CreateEventActivity.class);
                startActivity(intent);
            }
        });

        Button allEventsButton = findViewById(R.id.all_events_button);
        allEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyEventsActivity.this, AllEventsActivity.class);
                startActivity(intent);
            }
        });
    }
}