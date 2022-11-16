package com.example.peppergames;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.peppergames.dto.User;

public class ProfileActivity extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        User user = Database.getAppUser();
        TextView textView = findViewById(R.id.user_name);
        textView.setText(user.getName());

        textView = findViewById(R.id.user_skill_rating);
        textView.setText(String.format("Skill: %d", user.getSkillRating()));

        textView = findViewById(R.id.user_conduct_rating);
        textView.setText(String.format("Conduct: %d", user.getConductRating()));

        textView = findViewById(R.id.user_bio);
        textView.setText(user.getBio());

        textView = findViewById(R.id.user_games_played);
        textView.setText(String.format("Games played: %d", user.getGamesPlayed()));

        textView = findViewById(R.id.user_games_hosted);
        textView.setText(String.format("Games hosted: %d", user.getGamesHosted()));

        textView = findViewById(R.id.user_favorite_sport);
        textView.setText(String.format("Most played sport: %s", user.getFavoriteSport()));

        textView = findViewById(R.id.user_account_created);
        textView.setText(String.format("Account created: %s", user.getAccountCreatedDate()));
    }
}
