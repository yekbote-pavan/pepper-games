package com.example.peppergames;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.peppergames.databinding.ProfileBinding;
import com.example.peppergames.dto.User;

public class ProfileActivity extends AppCompatActivity {

    private ProfileBinding binding;
    private User user;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        user = new User("Pavan Yekbote", 2, 3, "I'm a freshman. " +
                "I have played football for about 3-4 years now on a regular basis. I'm looking to make some friends and have fun!",
                22, 22, "Football", "22/01/2202");
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
