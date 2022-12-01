package com.example.peppergames;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.google.android.material.switchmaterial.SwitchMaterial;
import java.util.Map;

public class MyEventsActivity extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events);
        LinearLayout layout = findViewById(R.id.my_event_outer_layout);
        layout.removeAllViews();

        Intent intent = getIntent();
        boolean[] filters = intent.getBooleanArrayExtra("filter");
        if (filters == null) {
            filters = new boolean[4];
            filters[0] = true;
            filters[1] = true;
            filters[2] = true;
            filters[3] = true;
        }

        int myEventsCount = 0;


        View confirmLeave = getLayoutInflater().inflate(R.layout.leave_confirm_popup, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow confirmLeavePopup = new PopupWindow(confirmLeave, width, height, focusable);

        CardView cardView;
        for (Event event : Database.getEvents()) {
            if (!event.isPlaying()) {
                continue;
            }


            switch (event.getGame()) {
                case "Basketball":
                    if (!filters[1]) {
                        continue;
                    }
                case "Tennis":
                    if (!filters[2]) {
                        continue;
                    }
                case "Football":
                    if (!filters[3]) {
                        continue;
                    }
            }

            myEventsCount++;
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

            Button myEventsButton = findViewById(R.id.my_events_counter);
            myEventsButton.setText(String.format("MY EVENTS (%d)", myEventsCount));

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


        View filter = getLayoutInflater().inflate(R.layout.multiselect, null);
        int filterWidth = LinearLayout.LayoutParams.MATCH_PARENT;
        int filterHeight = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean filterFocusable = true;
        PopupWindow filterPopup = new PopupWindow(filter, filterWidth, filterHeight, filterFocusable);

        CheckBox checkBox1 = filter.findViewById(R.id.checkBox);
        CheckBox checkBox2 = filter.findViewById(R.id.checkBox2);
        CheckBox checkBox3 = filter.findViewById(R.id.checkBox3);
        CheckBox checkBox4 = filter.findViewById(R.id.checkBox4);
        if (filters[0]) {
            checkBox1.setChecked(true);
        }

        if (filters[1]) {
            checkBox2.setChecked(true);
        }

        if (filters[2]) {
            checkBox3.setChecked(true);
        }

        if (filters[3]) {
            checkBox4.setChecked(true);
        }

        SwitchMaterial gamesICanJoinSwitch = filter.findViewById(R.id.games_i_can_join_switch);
        ((ViewGroup) gamesICanJoinSwitch.getParent()).removeView(gamesICanJoinSwitch);

        TextView gamesICanJoinText = filter.findViewById(R.id.games_i_can_join_text);
        ((ViewGroup) gamesICanJoinText.getParent()).removeView(gamesICanJoinText);

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBox2.setChecked(true);
                    checkBox3.setChecked(true);
                    checkBox4.setChecked(true);
                } else {
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                }
            }
        });


        Button confirmFilter = filter.findViewById(R.id.filter_confirm_button);
        Button cancelFilter = filter.findViewById(R.id.filter_cancel_button);

        confirmFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean[] filter = new boolean[4];
                boolean isAnySelected = false;
                Intent intent = getIntent();
                if (checkBox1.isChecked()) {
                    filter[0] = true;
                    isAnySelected = true;
                }

                if (checkBox2.isChecked()) {
                    filter[1] = true;
                    isAnySelected = true;
                }

                if (checkBox3.isChecked()) {
                    filter[2] = true;
                    isAnySelected = true;
                }

                if (checkBox4.isChecked()) {
                    filter[3] = true;
                    isAnySelected = true;
                }

                if (isAnySelected) {
                    finish();
                    intent.putExtra("filter", filter);
                    startActivity(intent);
                }
            }
        });

        cancelFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterPopup.dismiss();
            }
        });

        Button filterButton = findViewById(R.id.my_events_filter_button);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterPopup.showAtLocation(filter, Gravity.BOTTOM, 0, 0);
            }
        });
    }
}
