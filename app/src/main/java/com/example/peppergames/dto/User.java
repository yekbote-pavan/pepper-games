package com.example.peppergames.dto;

public class User {

    private String name;
    private int skillRating;
    private int conductRating;
    private String bio;
    private int gamesPlayed;
    private int gamesHosted;
    private String favoriteSport;
    private String accountCreatedDate;

    public User(String name, int skillRating, int conductRating, String bio, int gamesPlayed, int gamesHosted, String favoriteSport, String accountCreatedDate) {
        this.name = name;
        this.skillRating = skillRating;
        this.conductRating = conductRating;
        this.bio = bio;
        this.gamesPlayed = gamesPlayed;
        this.gamesHosted = gamesHosted;
        this.favoriteSport = favoriteSport;
        this.accountCreatedDate = accountCreatedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesHosted() {
        return gamesHosted;
    }

    public void setGamesHosted(int gamesHosted) {
        this.gamesHosted = gamesHosted;
    }

    public String getFavoriteSport() {
        return favoriteSport;
    }

    public void setFavoriteSport(String favoriteSport) {
        this.favoriteSport = favoriteSport;
    }

    public String getAccountCreatedDate() {
        return accountCreatedDate;
    }

    public void setAccountCreatedDate(String accountCreatedDate) {
        this.accountCreatedDate = accountCreatedDate;
    }
}
