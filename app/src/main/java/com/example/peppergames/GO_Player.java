package com.example.peppergames;

public class GO_Player {
    String name;
    int conduct;
    int skill;

    public GO_Player(String name, int conduct, int skill) {
        this.name = name;
        this.conduct = conduct;
        this.skill = skill;
    }

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
