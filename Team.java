package com.example.pc.myapplication;

/**
 * Created by PC on 8/12/2018.
 */

public class Team
{
    private String coach;
    private String ageGroup;
    private String teamName;

    public Team(String coach, String ageGroup, String teamName) {
        this.coach = coach;
        this.ageGroup = ageGroup;
        this.teamName = teamName;
    }


    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }


    public Team() {
    }
}
