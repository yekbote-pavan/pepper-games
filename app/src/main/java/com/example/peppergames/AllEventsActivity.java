package com.example.peppergames;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AllEventsActivity extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);
        LinearLayout layout = findViewById(R.id.event_outer_layout);
        layout.removeAllViews();

        CardView cardView;
        if(Database.isShowRating()){
            Intent intent = new Intent(AllEventsActivity.this, RatingPopUp.class);
            startActivity(intent);
            Database.setRatingToFalse();
        }

        Intent intent = getIntent();
        boolean[] filters = intent.getBooleanArrayExtra("filter");
        if (filters == null) {
            filters = new boolean[4];
            filters[0] = true;
            filters[1] = true;
            filters[2] = true;
            filters[3] = true;
        }

        String sort = intent.getStringExtra("sort");
        boolean arrowDown = intent.getBooleanExtra("sorting_down", sort != null);
        if (sort == null) {
            sort = "Date";
        }

        boolean gamesICanJoin = intent.getBooleanExtra("games_i_can_join", true);

        int myEventsCount = 0;
        for (Event event: Database.getEvents()) {
            if (event.isPlaying()) {
                myEventsCount++;
            }
        }

        Button myEventsButton = findViewById(R.id.my_events_button);
        myEventsButton.setText(String.format("MY EVENTS (%d)", myEventsCount));

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            if (sort.equals("Conduct")) {
                Database.events = Database.getEvents().stream().sorted(new Comparator<Event>() {
                    @Override
                    public int compare(Event o1, Event o2) {
                        int multiplier = arrowDown ? -1 : 1;
                        return multiplier * Integer.compare(o1.getSkillRating(), o2.getSkillRating());
                    }
                }).collect(Collectors.toList());
            } else if (sort.equals("Skills")) {
                Database.events = Database.getEvents().stream().sorted(new Comparator<Event>() {
                    @Override
                    public int compare(Event o1, Event o2) {
                        int multiplier = arrowDown ? -1 : 1;
                        return multiplier * Integer.compare(o1.getConductRating(), o2.getConductRating());
                    }
                }).collect(Collectors.toList());
            } else {
                Database.events = Database.getEvents().stream().sorted(new Comparator<Event>() {
                    @Override
                    public int compare(Event o1, Event o2) {
                        int multiplier = arrowDown ? -1 : 1;
                        return multiplier * String.CASE_INSENSITIVE_ORDER.compare(o1.getDate(), o2.getDate());
                    }
                }).collect(Collectors.toList());
            }
        }

        for (Event event : Database.getEvents()) {
            if (event.isPlaying()) {
                continue;
            }

            switch (event.getGame()) {
                case "Basketball":
                    if (!filters[1]) {
                        continue;
                    }
                    break;
                case "Tennis":
                    if (!filters[2]) {
                        continue;
                    }
                    break;
                case "Football":
                    if (!filters[3]) {
                        continue;
                    }
                    break;
            }

            if (gamesICanJoin) {
                if (Database.getAppUser().getConductRating() < event.getConductRating()) {
                    continue;
                }

                if (Database.getAppUser().getSkillRating() < event.getSkillRating()) {
                    continue;
                }
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

        myEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllEventsActivity.this, MyEventsActivity.class);
                startActivity(intent);
            }
        });

        View filter = getLayoutInflater().inflate(R.layout.multiselect, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow filterPopup = new PopupWindow(filter, width, height, focusable);

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
        if (gamesICanJoin) {
            gamesICanJoinSwitch.setChecked(true);
        }

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
                    intent.putExtra("games_i_can_join", gamesICanJoinSwitch.isChecked());
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

        Button filterButton = findViewById(R.id.filter_button);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterPopup.showAtLocation(filter, Gravity.BOTTOM, 0, 0);
            }
        });

        ImageButton sortingButton = findViewById(R.id.sorting_arrow);
        if (!arrowDown) {
            sortingButton.setImageResource(R.drawable.up_arrow);
        }

        sortingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Event> events = Database.getEvents();
                List<Event> newEvents = new ArrayList<>();
                for (int i = events.size() - 1; i > -1; i--) {
                    newEvents.add(events.get(i));
                }

                Database.events = newEvents;
                finish();
                intent.putExtra("sorting_down", !arrowDown);
                startActivity(getIntent());
            }
        });

        View sortView = getLayoutInflater().inflate(R.layout.sort, null);
        int sortWidth = LinearLayout.LayoutParams.MATCH_PARENT;
        int sortHeight = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean sortFocusable = true;
        PopupWindow sortPopup = new PopupWindow(sortView, sortWidth, sortHeight, sortFocusable);

        Button dateButton = sortView.findViewById(R.id.date_sort);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortPopup.dismiss();
                finish();
                intent.putExtra("sort", "Date");
                intent.putExtra("sorting_down", false);
                startActivity(getIntent());
            }
        });

        Button conductButton = sortView.findViewById(R.id.conduct_sort);
        conductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortPopup.dismiss();
                finish();
                intent.putExtra("sort", "Conduct");
                intent.putExtra("sorting_down", true);
                startActivity(getIntent());
            }
        });

        Button skillButton = sortView.findViewById(R.id.skill_sort);
        skillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortPopup.dismiss();
                finish();
                intent.putExtra("sort", "Skills");
                intent.putExtra("sorting_down", true);
                startActivity(getIntent());
            }
        });

        Button sortButton = findViewById(R.id.sort_button);
        sortButton.setText(sort);
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortPopup.showAtLocation(sortView, Gravity.BOTTOM, 0, 0);
            }
        });
    }
}
