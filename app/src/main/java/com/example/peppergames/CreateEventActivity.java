package com.example.peppergames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import com.example.peppergames.dto.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        Spinner sportSpinner = findViewById(R.id.sport_spinner);
        String[] sportArray = new String[] {
                "Football", "Basketball"
        };
        ArrayAdapter<String> sportAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sportArray);
        sportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportSpinner.setAdapter(sportAdapter);

        Spinner venueSpinner = findViewById(R.id.venue_spinner);
        String[] venueArray = new String[] {
                "ARC", "CRCE, Flag"
        };
        ArrayAdapter<String> venueAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, venueArray);
        venueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        venueSpinner.setAdapter(venueAdapter);

        CalendarView calendar = findViewById(R.id.date_selector);
        TimePicker timePicker = findViewById(R.id.time_picker);

        RatingBar skillsRating = findViewById(R.id.skills_rating_bar);
        RatingBar conductRating = findViewById(R.id.conduct_rating_bar);

        Button createEventButton = findViewById(R.id.create_event_button);
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sport = sportSpinner.getSelectedItem().toString();
                String venue = venueSpinner.getSelectedItem().toString();
                //toDo: change to pick position

                long date = calendar.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                long time = timePicker.getCurrentHour();
                Event event = new Event(sport, Math.round(skillsRating.getRating()), Math.round(conductRating.getRating()), String.format("%d, %s", time, sdf.format(new Date(date))), 1,
                        venue, true, new HashMap<>());
                Database.addEvent(event);
                Intent intent = new Intent(CreateEventActivity.this, MyEventsActivity.class);
                intent.putExtra("event_index", Database.getEvents().size() - 1);
                startActivity(intent);
            }
        });
    }
}