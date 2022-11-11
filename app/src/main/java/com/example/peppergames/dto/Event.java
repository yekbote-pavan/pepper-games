package com.example.peppergames.dto;

import java.util.List;
import java.util.Map;

public class Event {

    private String game;
    private int skillRating;
    private int conductRating;
    private String date;
    private int maxPlayers;
    private String location;
    private boolean isPlaying;
    private List<User> players;
    private Map<TeamEnum, Map<PositionEnum, User>> teamPositions;

    public List<User> getPlayers() {
        return players;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
    }

    public Map<TeamEnum, Map<PositionEnum, User>> getTeamPositions() {
        return teamPositions;
    }

    public void setTeamPositions(Map<TeamEnum, Map<PositionEnum, User>> teamPositions) {
        this.teamPositions = teamPositions;
    }

    public Event(String game, int skillRating, int conductRating, String date, List<User> players,
                 int maxPlayers, String location, boolean isPlaying,
                 Map<TeamEnum, Map<PositionEnum, User>> teamPositions) {
        this.game = game;
        this.skillRating = skillRating;
        this.conductRating = conductRating;
        this.date = date;
        this.players = players;
        this.maxPlayers = maxPlayers;
        this.location = location;
        this.isPlaying = isPlaying;
        this.teamPositions = teamPositions;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getSkillRating() {
        return skillRating;
    }

    public void setSkillRating(int skillRating) {
        this.skillRating = skillRating;
    }

    public int getConductRating() {
        return conductRating;
    }

    public void setConductRating(int conductRating) {
        this.conductRating = conductRating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
