package com.example.peppergames;

public class GO_Player {
    String name;
    int conduct;
    int skill;
//    String player_image;

    public GO_Player(String name, int conduct, int skill) {
        this.name = name;
        this.conduct = conduct;
        this.skill = skill;
//        this.player_image = player_image;
    }

//    public String getPlayer_image() { return player_image; }
//
//    public void setPlayer_image(String player_image) { this.player_image = player_image; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConduct() {
        return conduct;
    }

    public void setConduct(int conduct) {
        this.conduct = conduct;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

}
