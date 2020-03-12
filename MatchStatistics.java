package com.example.pc.myapplication;

public class MatchStatistics
{
    private String Game_Name;
    private String turnOverHome1st;
    private String turnOverhome2nd;

    private String turnOverAway1st;
    private String turnOverAway2nd;

    private String GoalsHome1st;
    private String GoalsHome2nd;

    private String GoalsAway1st;
    private String GoalsAway2nd;

    private String CirclePenatrations1st;

    private String CirclePenatrations2nd;

    private String Penalty_Coners1st;
    private String Penalty_Coners2nd;

    private String Goal_shots1st;
    private String Goal_shots2nd;


    public MatchStatistics(String game_Name, String turnOverHome1st, String turnOverhome2nd, String turnOverAway1st, String turnOverAway2nd, String goalsHome1st, String goalsHome2nd, String goalsAway1st, String goalsAway2nd, String circlePenatrations1st, String circlePenatrations2nd, String penalty_Coners1st,
                           String penalty_Coners2nd, String goal_shots1st, String goal_shots2nd)
    {
        Game_Name = game_Name;
        this.turnOverHome1st = turnOverHome1st;
        this.turnOverhome2nd = turnOverhome2nd;
        this.turnOverAway1st = turnOverAway1st;
        this.turnOverAway2nd = turnOverAway2nd;
        GoalsHome1st = goalsHome1st;
        GoalsHome2nd = goalsHome2nd;
        GoalsAway1st = goalsAway1st;
        GoalsAway2nd = goalsAway2nd;
        CirclePenatrations1st = circlePenatrations1st;
        CirclePenatrations2nd = circlePenatrations2nd;
        Penalty_Coners1st = penalty_Coners1st;
        Penalty_Coners2nd = penalty_Coners2nd;
        Goal_shots1st = goal_shots1st;
        Goal_shots2nd = goal_shots2nd;
    }

    public String getGame_Name() {
        return Game_Name;
    }

    public void setGame_Name(String game_Name) {
        Game_Name = game_Name;
    }

    public String getTurnOverHome1st() {
        return turnOverHome1st;
    }

    public void setTurnOverHome1st(String turnOverHome1st) {
        this.turnOverHome1st = turnOverHome1st;
    }

    public String getTurnOverhome2nd() {
        return turnOverhome2nd;
    }

    public void setTurnOverhome2nd(String turnOverhome2nd) {
        this.turnOverhome2nd = turnOverhome2nd;
    }

    public String getTurnOverAway1st() {
        return turnOverAway1st;
    }

    public void setTurnOverAway1st(String turnOverAway1st) {
        this.turnOverAway1st = turnOverAway1st;
    }

    public String getTurnOverAway2nd() {
        return turnOverAway2nd;
    }

    public void setTurnOverAway2nd(String turnOverAway2nd) {
        this.turnOverAway2nd = turnOverAway2nd;
    }

    public String getGoalsHome1st() {
        return GoalsHome1st;
    }

    public void setGoalsHome1st(String goalsHome1st) {
        GoalsHome1st = goalsHome1st;
    }

    public String getGoalsHome2nd() {
        return GoalsHome2nd;
    }

    public void setGoalsHome2nd(String goalsHome2nd) {
        GoalsHome2nd = goalsHome2nd;
    }

    public String getGoalsAway1st() {
        return GoalsAway1st;
    }

    public void setGoalsAway1st(String goalsAway1st) {
        GoalsAway1st = goalsAway1st;
    }

    public String getGoalsAway2nd() {
        return GoalsAway2nd;
    }

    public void setGoalsAway2nd(String goalsAway2nd) {
        GoalsAway2nd = goalsAway2nd;
    }

    public String getCirclePenatrations1st() {
        return CirclePenatrations1st;
    }

    public void setCirclePenatrations1st(String circlePenatrations1st) {
        CirclePenatrations1st = circlePenatrations1st;
    }

    public String getCirclePenatrations2nd() {
        return CirclePenatrations2nd;
    }

    public void setCirclePenatrations2nd(String circlePenatrations2nd) {
        CirclePenatrations2nd = circlePenatrations2nd;
    }

    public String getPenalty_Coners1st() {
        return Penalty_Coners1st;
    }

    public void setPenalty_Coners1st(String penalty_Coners1st) {
        Penalty_Coners1st = penalty_Coners1st;
    }

    public String getPenalty_Coners2nd() {
        return Penalty_Coners2nd;
    }

    public void setPenalty_Coners2nd(String penalty_Coners2nd) {
        Penalty_Coners2nd = penalty_Coners2nd;
    }

    public String getGoal_shots1st() {
        return Goal_shots1st;
    }

    public void setGoal_shots1st(String goal_shots1st) {
        Goal_shots1st = goal_shots1st;
    }

    public String getGoal_shots2nd() {
        return Goal_shots2nd;
    }

    public void setGoal_shots2nd(String goal_shots2nd) {
        Goal_shots2nd = goal_shots2nd;
    }


    public MatchStatistics()
    {
    }
}
