package com.example.pc.myapplication;

/**
 * Created by PC on 8/12/2018.
 */

public class Player
{
    private String playerName;
    private String teamName;
    private String surname;
    private String  medicalNumber;
    private String medicalPlan;
    private String firstParentCell;
    private String secondParentCell;
    private String allegies;
    private String objectId;
    private String medicalName;
    private int goalsScored;

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public Player(String playerName, String medicalName, String teamName, String surname, String medicalNumber, String medicalPlan, String firstParentCell,
                  String secondParentCell, String allegies, String objectId,int goalsScored) {
        this.playerName = playerName;
        this.medicalName = medicalName;
        this.teamName = teamName;
        this.surname = surname;
        this.medicalNumber = medicalNumber;
        this.medicalPlan = medicalPlan;
        this.firstParentCell = firstParentCell;
        this.secondParentCell = secondParentCell;
        this.allegies = allegies;
        this.objectId = objectId;
        this.goalsScored = goalsScored;
    }

    public String getMedicalName() {
        return medicalName;
    }

    public void setMedicalName(String medicalName) {
        this.medicalName = medicalName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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

    public String getMedicalNumber() {
        return medicalNumber;
    }

    public void setMedicalNumber(String medicalNumber) {
        this.medicalNumber = medicalNumber;
    }

    public String getMedicalPlan() {
        return medicalPlan;
    }

    public void setMedicalPlan(String medicalPlan) {
        this.medicalPlan = medicalPlan;
    }

    public String getFirstParentCell() {
        return firstParentCell;
    }

    public void setFirstParentCell(String firstParentCell) {
        this.firstParentCell = firstParentCell;
    }

    public String getSecondParentCell() {
        return secondParentCell;
    }

    public void setSecondParentCell(String secondParentCell) {
        this.secondParentCell = secondParentCell;
    }

    public String getAllegies() {
        return allegies;
    }

    public void setAllegies(String allegies) {
        this.allegies = allegies;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Player() {
    }
}
