package com.example.pc.myapplication;

/**
 * Created by PC on 8/12/2018.
 */

public class Opponent
{
    private String coachName;

    private String teamName;

    private String surname;

    private String agegroup;

    public Opponent(String coachName, String teamName, String surname, String agegroup) {
        this.coachName = coachName;
        this.teamName = teamName;
        this.surname = surname;
        this.agegroup = agegroup;
    }


    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAgegroup() {
        return agegroup;
    }

    public void setAgegroup(String agegroup) {
        this.agegroup = agegroup;
    }

    public Opponent()
    {

    }
}
