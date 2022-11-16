package com.example.peppergames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.peppergames.dto.Event;
import com.example.peppergames.dto.PositionEnum;
import com.example.peppergames.dto.TeamEnum;
import com.example.peppergames.dto.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PickPosition extends AppCompatActivity {

    private Event current_event;
    private ImageButton current_button;
    private Map<PositionEnum, String> position_dict = new HashMap<PositionEnum, String>(){{
        put(PositionEnum.CM, "centralMidfielder");
        put(PositionEnum.GK, "goalkeeper");
        put(PositionEnum.LW, "leftWing");
        put(PositionEnum.RW, "rightWing");
        put(PositionEnum.LST, "leftStriker");
        put(PositionEnum.RST, "rightStriker");
    }};

    private Map<String, PositionEnum> position_dict_rev = new HashMap<String, PositionEnum>(){{
        put("centralMidfielder1", PositionEnum.CM);
        put("goalkeeper1", PositionEnum.GK);
        put("leftWing1", PositionEnum.LW);
        put("rightWing1", PositionEnum.RW);
        put("leftStriker1", PositionEnum.LST);
        put("rightStriker1", PositionEnum.RST);
        put("centralMidfielder2", PositionEnum.CM);
        put("goalkeeper2", PositionEnum.GK);
        put("leftWing2", PositionEnum.LW);
        put("rightWing2", PositionEnum.RW);
        put("leftStriker2", PositionEnum.LST);
        put("rightStriker2", PositionEnum.RST);
    }};
//    private List<Integer> position_id_int = new ArrayList<>(){{
//        add(R.id.goalkeeper1);
//        add(R.id.goalkeeper2);
//        add(R.id.leftStriker1);
//        add(R.id.leftStriker2);
//        add(R.id.rightStriker1);
//        add(R.id.rightStriker2);
//        add(R.id.leftWing1);
//        add(R.id.leftWing2);
//        add(R.id.centralMidfielder1);
//        add(R.id.centralMidfielder2);
//        add(R.id.rightWing1);
//        add(R.id.rightWing2);
//    }};

    private Map<String, Integer> position_title_to_id = new HashMap<String, Integer>(){{
        put("goalkeeper1", R.id.goalkeeper1);
        put("goalkeeper2", R.id.goalkeeper2);
        put("leftStriker1", R.id.leftStriker1);
        put("leftStriker2", R.id.leftStriker2);
        put("rightStriker1", R.id.rightStriker1);
        put("rightStriker2", R.id.rightStriker2);
        put("leftWing1", R.id.leftWing1);
        put("leftWing2", R.id.leftWing2);
        put("centralMidfielder1", R.id.centralMidfielder1);
        put("centralMidfielder2", R.id.centralMidfielder2);
        put("rightWing1", R.id.rightWing1);
        put("rightWing2", R.id.rightWing2);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_position);
        Intent intent = getIntent();
        int eventIndex = intent.getIntExtra("event_index", 0);

        current_event = Database.getEvents().get(eventIndex);
        EditText text = findViewById(R.id.game_type);
        text.setText(current_event.getGame());

        text = findViewById(R.id.game_date);
        text.setText(current_event.getDate());

        text = findViewById(R.id.position_game_skill_rating);
        text.setText(String.format("Skills: %d", current_event.getSkillRating()));

        text = findViewById(R.id.position_game_conduct_rating);
        text.setText(String.format("Conduct: %d", current_event.getConductRating()));

        text = findViewById(R.id.position_game_location);
        text.setText(current_event.getLocation());

        // update occupied positions with a different image: ic_round_person
        Map<PositionEnum, User> homeTeam = current_event.getTeamPositions().get(TeamEnum.HOME);
        List<String> homeTeamTakens = new ArrayList<>();
        for (Map.Entry<PositionEnum, User> entry : homeTeam.entrySet()){
            PositionEnum pos = entry.getKey();
            User user = entry.getValue();
            String id_str = position_dict.get(pos) + "1";
            homeTeamTakens.add(id_str);
            int id = getResources().getIdentifier(id_str, "id", getPackageName());
            current_button = (ImageButton) findViewById(id);
            current_button.setImageResource(R.drawable.ic_round_person);
        }

        Map<PositionEnum, User> awayTeam = current_event.getTeamPositions().get(TeamEnum.AWAY);
        List<String> awayTeamTakens = new ArrayList<>();
        for (Map.Entry<PositionEnum, User> entry : awayTeam.entrySet()){
            PositionEnum pos = entry.getKey();
            User user = entry.getValue();
            String id_str = position_dict.get(pos) + "2";
            awayTeamTakens.add(id_str);
            int id = getResources().getIdentifier(id_str, "id", getPackageName());
            current_button = (ImageButton) findViewById(id);
            current_button.setImageResource(R.drawable.ic_round_person);
        }

        // Pop-up windows
        View pickPosition = getLayoutInflater().inflate(R.layout.position_pick_confirm_popup, null);
        View otherPlayerInfo = getLayoutInflater().inflate(R.layout.position_taken_player_info, null);
        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;
        //Create a window with our parameters
        PopupWindow popupWindow_position_confirm = new PopupWindow(pickPosition, width, height, focusable);
        PopupWindow popupWindow_other_player_info = new PopupWindow(otherPlayerInfo, width, height, focusable);

        ImageButton button_click;
        for (Map.Entry<String, Integer> entry : position_title_to_id.entrySet()){
            String position_title = entry.getKey();
            int position_id = entry.getValue();
            button_click = findViewById(position_id);
            // 逻辑错了 不应该hometeam找title_enum 因为home和away可能共用同样的enum
            if (homeTeamTakens.contains(position_title) || awayTeamTakens.contains(position_title)) {
                button_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow_other_player_info.showAtLocation(otherPlayerInfo, Gravity.BOTTOM, 0, 0);
                    }
                });
            } else {
                button_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow_position_confirm.showAtLocation(pickPosition, Gravity.BOTTOM, 0, 0);
                    }
                });
            }

//            // the position is not taken by others
//            if (!homeTeam.containsKey(position_title_enum) && !awayTeam.containsKey(position_title_enum)){
////                button_click = findViewById(position_id);
//                button_click.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        popupWindow_position_confirm.showAtLocation(pickPosition, Gravity.BOTTOM, 0, 0);
//                    }
//                });
//            } else {    // the position is taken by the other player
//                button_click.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        popupWindow_other_player_info.showAtLocation(otherPlayerInfo, Gravity.BOTTOM, 0, 0);
//                    }
//                });
//            }

        }



//        ImageButton gkButton = findViewById(R.id.leftStriker2);
//        gkButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.showAtLocation(pickPosition, Gravity.BOTTOM, 0, 0);
//            }
//        });
    }
}