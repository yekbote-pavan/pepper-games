package com.example.peppergames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.peppergames.dto.PositionEnum;
import com.example.peppergames.dto.User;

import java.util.HashMap;
import java.util.Map;

public class RatingPopUp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_pop_up);
        LinearLayout layout = findViewById(R.id.rate_your_teammates_lr);
        CardView cardView;
        Map<PositionEnum, User> positionsMap = new HashMap<>();

        User Avram = new User("Avram", 5, 4, "I celebrate a victory when I start walking off the field. " +
                "By the time I get to the locker room, I’m done.",
                232, 2, "Football", "12/11/2021", "profile_image_1");
        User Pratt = new User("Pratt", 2, 1, "I figure practice puts your brains in your muscles.",
                45, 37, "Football", "03/05/2011", "profile_image_3");
        User Nora = new User("Nora", 2, 5, "hiii",
                5, 0, "Football", "22/01/2202", "profile_image_5");

        positionsMap.put(PositionEnum.GK, Avram);
        positionsMap.put(PositionEnum.RST, Pratt);
        positionsMap.put(PositionEnum.CM, Nora);

        for (Map.Entry<PositionEnum, User> set : positionsMap.entrySet()){
            cardView = (CardView) getLayoutInflater().inflate(R.layout.rating_card, null);

            TextView textView = cardView.findViewById(R.id.rating_name);
            textView.setText(set.getValue().getName());

            RatingBar rating_skills = cardView.findViewById(R.id.rating_skills);
            rating_skills.setRating(5);

            RatingBar rating_conduct = cardView.findViewById(R.id.rating_conduct);
            rating_conduct.setRating(5);

            TextView textView1 = cardView.findViewById(R.id.player_position_id);
            textView1.setText(set.getKey().name());

            layout.addView(cardView);
        }

        cardView = (CardView) getLayoutInflater().inflate(R.layout.rating_card, null);
        TextView textView = cardView.findViewById(R.id.rating_name);
        textView.setText("Smit");

        RatingBar rating_skills = cardView.findViewById(R.id.rating_skills);
        rating_skills.setRating(5);

        RatingBar rating_conduct = cardView.findViewById(R.id.rating_conduct);
        rating_conduct.setRating(5);

        TextView textView1 = cardView.findViewById(R.id.player_position_id);
        textView1.setText("LST");

        layout.addView(cardView);

        Button button_confirm = findViewById(R.id.confirm);
        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RatingPopUp.this, MyEventsActivity.class);
                startActivity(intent);
            }
        });

        Button button_cancel = findViewById(R.id.cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RatingPopUp.this, MyEventsActivity.class);
                startActivity(intent);
            }
        });

    }

}