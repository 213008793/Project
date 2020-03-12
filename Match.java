package com.example.pc.myapplication;

/**
 * Created by PC on 10/16/2018.
 */

public class Match
{

    private String matchName;
    private String homeTeam;
    private String awayTeam;
    private String left_Forward;
    private String right_Forward;
    private String Center_Forward;
    private String Left_Link;
    private String Right_Link;
    private String Center_Link;
    private String Left_Back;
    private String Right_Back;
    private String Center_Back;
    private String Sweeper;
    private String Goalie;
    private String Bench1;
    private String Bench2;
    private String Bench3;
    private String Bench4;
    private String Bench5;


    private double left_Forward_rating;
    private double right_Forward_rating;
    private double Center_Forward_rating;
    private double Left_Link_rating;
    private double Right_Link_rating;
    private double Center_Link_rating;
    private double Left_Back_rating;
    private double Right_Back_rating;
    private double Center_Back_rating;
    private double Sweeper_rating;
    private double Goalie_rating;
    private double Bench1_rating;
    private double Bench2_rating;
    private double Bench3_rating;
    private double Bench4_rating;
    private double Bench5_rating;

    private String left_Forward_goals;
    private String right_Forward_goals;
    private String Center_Forward_goals;
    private String Left_Link_goals;
    private String Right_Link_goals;
    private String Center_Link_goals;
    private String Left_Back_goals;
    private String Right_Back_goals;
    private String Center_Back_goals;
    private String Sweeper_goals;
    private String Goalie_goals;
    private String Bench1_goals;
    private String Bench2_goals;
    private String Bench3_goals;
    private String Bench4_goals;
    private String Bench5_goals;


    public Match(String homeTeam, String matchName ,String awayTeam, String left_Forward,
                 String right_Forward, String center_Forward, String left_Link,
                 String right_Link, String center_Link, String left_Back, String right_Back,
                 String center_Back, String sweeper, String goalie, String bench1, String bench2,
                 String bench3, String bench4, String bench5, double left_Forward_rating,
                 double right_Forward_rating, double center_Forward_rating, double left_Link_rating,
                 double right_Link_rating, double center_Link_rating, double left_Back_rating,
                 double right_Back_rating, double center_Back_rating, double sweeper_rating,
                 double goalie_rating, double bench1_rating, double bench2_rating, double bench3_rating, double bench4_rating, double bench5_rating, String left_Forward_goals, String right_Forward_goals, String center_Forward_goals, String left_Link_goals, String right_Link_goals, String center_Link_goals, String left_Back_goals, String right_Back_goals, String center_Back_goals, String sweeper_goals, String goalie_goals, String bench1_goals,
                 String bench2_goals,
                 String bench3_goals,
                 String bench4_goals, String bench5_goals) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchName = matchName;
        this.left_Forward = left_Forward;
        this.right_Forward = right_Forward;
        Center_Forward = center_Forward;
        Left_Link = left_Link;
        Right_Link = right_Link;
        Center_Link = center_Link;
        Left_Back = left_Back;
        Right_Back = right_Back;
        Center_Back = center_Back;
        Sweeper = sweeper;
        Goalie = goalie;
        Bench1 = bench1;
        Bench2 = bench2;
        Bench3 = bench3;
        Bench4 = bench4;
        Bench5 = bench5;
        this.left_Forward_rating = left_Forward_rating;
        this.right_Forward_rating = right_Forward_rating;
        Center_Forward_rating = center_Forward_rating;
        Left_Link_rating = left_Link_rating;
        Right_Link_rating = right_Link_rating;
        Center_Link_rating = center_Link_rating;
        Left_Back_rating = left_Back_rating;
        Right_Back_rating = right_Back_rating;
        Center_Back_rating = center_Back_rating;
        Sweeper_rating = sweeper_rating;
        Goalie_rating = goalie_rating;
        Bench1_rating = bench1_rating;
        Bench2_rating = bench2_rating;
        Bench3_rating = bench3_rating;
        Bench4_rating = bench4_rating;
        Bench5_rating = bench5_rating;
        this.left_Forward_goals = left_Forward_goals;
        this.right_Forward_goals = right_Forward_goals;
        Center_Forward_goals = center_Forward_goals;
        Left_Link_goals = left_Link_goals;
        Right_Link_goals = right_Link_goals;
        Center_Link_goals = center_Link_goals;
        Left_Back_goals = left_Back_goals;
        Right_Back_goals = right_Back_goals;
        Center_Back_goals = center_Back_goals;
        Sweeper_goals = sweeper_goals;
        Goalie_goals = goalie_goals;
        Bench1_goals = bench1_goals;
        Bench2_goals = bench2_goals;
        Bench3_goals = bench3_goals;
        Bench4_goals = bench4_goals;
        Bench5_goals = bench5_goals;
    }

    public Match()
    {
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getLeft_Forward() {
        return left_Forward;
    }

    public void setLeft_Forward(String left_Forward) {
        this.left_Forward = left_Forward;
    }

    public String getRight_Forward() {
        return right_Forward;
    }

    public void setRight_Forward(String right_Forward) {
        this.right_Forward = right_Forward;
    }

    public String getCenter_Forward() {
        return Center_Forward;
    }

    public void setCenter_Forward(String center_Forward) {
        Center_Forward = center_Forward;
    }

    public String getLeft_Link() {
        return Left_Link;
    }

    public void setLeft_Link(String left_Link) {
        Left_Link = left_Link;
    }

    public String getRight_Link() {
        return Right_Link;
    }

    public void setRight_Link(String right_Link) {
        Right_Link = right_Link;
    }

    public String getCenter_Link() {
        return Center_Link;
    }

    public void setCenter_Link(String center_Link) {
        Center_Link = center_Link;
    }

    public String getLeft_Back() {
        return Left_Back;
    }

    public void setLeft_Back(String left_Back) {
        Left_Back = left_Back;
    }

    public String getRight_Back() {
        return Right_Back;
    }

    public void setRight_Back(String right_Back) {
        Right_Back = right_Back;
    }

    public String getCenter_Back() {
        return Center_Back;
    }

    public void setCenter_Back(String center_Back) {
        Center_Back = center_Back;
    }

    public String getSweeper() {
        return Sweeper;
    }

    public void setSweeper(String sweeper) {
        Sweeper = sweeper;
    }

    public String getGoalie() {
        return Goalie;
    }

    public void setGoalie(String goalie) {
        Goalie = goalie;
    }

    public String getBench1() {
        return Bench1;
    }

    public void setBench1(String bench1) {
        Bench1 = bench1;
    }

    public String getBench2() {
        return Bench2;
    }

    public void setBench2(String bench2) {
        Bench2 = bench2;
    }

    public String getBench3() {
        return Bench3;
    }

    public void setBench3(String bench3) {
        Bench3 = bench3;
    }

    public String getBench4() {
        return Bench4;
    }

    public void setBench4(String bench4) {
        Bench4 = bench4;
    }

    public String getBench5() {
        return Bench5;
    }

    public void setBench5(String bench5) {
        Bench5 = bench5;
    }

    public double getLeft_Forward_rating() {
        return left_Forward_rating;
    }

    public void setLeft_Forward_rating(double left_Forward_rating) {
        this.left_Forward_rating = left_Forward_rating;
    }

    public double getRight_Forward_rating() {
        return right_Forward_rating;
    }

    public void setRight_Forward_rating(double right_Forward_rating) {
        this.right_Forward_rating = right_Forward_rating;
    }

    public double getCenter_Forward_rating() {
        return Center_Forward_rating;
    }

    public void setCenter_Forward_rating(double center_Forward_rating) {
        Center_Forward_rating = center_Forward_rating;
    }

    public double getLeft_Link_rating() {
        return Left_Link_rating;
    }

    public void setLeft_Link_rating(double left_Link_rating) {
        Left_Link_rating = left_Link_rating;
    }

    public double getRight_Link_rating() {
        return Right_Link_rating;
    }

    public void setRight_Link_rating(double right_Link_rating) {
        Right_Link_rating = right_Link_rating;
    }

    public double getCenter_Link_rating() {
        return Center_Link_rating;
    }

    public void setCenter_Link_rating(double center_Link_rating) {
        Center_Link_rating = center_Link_rating;
    }

    public double getLeft_Back_rating() {
        return Left_Back_rating;
    }

    public void setLeft_Back_rating(double left_Back_rating) {
        Left_Back_rating = left_Back_rating;
    }

    public double getRight_Back_rating() {
        return Right_Back_rating;
    }

    public void setRight_Back_rating(double right_Back_rating) {
        Right_Back_rating = right_Back_rating;
    }

    public double getCenter_Back_rating() {
        return Center_Back_rating;
    }

    public void setCenter_Back_rating(double center_Back_rating) {
        Center_Back_rating = center_Back_rating;
    }

    public double getSweeper_rating() {
        return Sweeper_rating;
    }

    public void setSweeper_rating(double sweeper_rating) {
        Sweeper_rating = sweeper_rating;
    }

    public double getGoalie_rating() {
        return Goalie_rating;
    }

    public void setGoalie_rating(double goalie_rating) {
        Goalie_rating = goalie_rating;
    }

    public double getBench1_rating() {
        return Bench1_rating;
    }

    public void setBench1_rating(double bench1_rating) {
        Bench1_rating = bench1_rating;
    }

    public double getBench2_rating() {
        return Bench2_rating;
    }

    public void setBench2_rating(double bench2_rating) {
        Bench2_rating = bench2_rating;
    }

    public double getBench3_rating() {
        return Bench3_rating;
    }

    public void setBench3_rating(double bench3_rating) {
        Bench3_rating = bench3_rating;
    }

    public double getBench4_rating() {
        return Bench4_rating;
    }

    public void setBench4_rating(double bench4_rating) {
        Bench4_rating = bench4_rating;
    }

    public double getBench5_rating() {
        return Bench5_rating;
    }

    public void setBench5_rating(double bench5_rating) {
        Bench5_rating = bench5_rating;
    }

    public String getLeft_Forward_goals() {
        return left_Forward_goals;
    }

    public void setLeft_Forward_goals(String left_Forward_goals) {
        this.left_Forward_goals = left_Forward_goals;
    }

    public String getRight_Forward_goals() {
        return right_Forward_goals;
    }

    public void setRight_Forward_goals(String right_Forward_goals) {
        this.right_Forward_goals = right_Forward_goals;
    }

    public String getCenter_Forward_goals() {
        return Center_Forward_goals;
    }

    public void setCenter_Forward_goals(String center_Forward_goals) {
        Center_Forward_goals = center_Forward_goals;
    }

    public String getLeft_Link_goals() {
        return Left_Link_goals;
    }

    public void setLeft_Link_goals(String left_Link_goals) {
        Left_Link_goals = left_Link_goals;
    }

    public String getRight_Link_goals() {
        return Right_Link_goals;
    }

    public void setRight_Link_goals(String right_Link_goals) {
        Right_Link_goals = right_Link_goals;
    }

    public String getCenter_Link_goals() {
        return Center_Link_goals;
    }

    public void setCenter_Link_goals(String center_Link_goals) {
        Center_Link_goals = center_Link_goals;
    }

    public String getLeft_Back_goals() {
        return Left_Back_goals;
    }

    public void setLeft_Back_goals(String left_Back_goals) {
        Left_Back_goals = left_Back_goals;
    }

    public String getRight_Back_goals() {
        return Right_Back_goals;
    }

    public void setRight_Back_goals(String right_Back_goals) {
        Right_Back_goals = right_Back_goals;
    }

    public String getCenter_Back_goals() {
        return Center_Back_goals;
    }

    public void setCenter_Back_goals(String center_Back_goals) {
        Center_Back_goals = center_Back_goals;
    }

    public String getSweeper_goals() {
        return Sweeper_goals;
    }

    public void setSweeper_goals(String sweeper_goals) {
        Sweeper_goals = sweeper_goals;
    }

    public String getGoalie_goals() {
        return Goalie_goals;
    }

    public void setGoalie_goals(String goalie_goals) {
        Goalie_goals = goalie_goals;
    }

    public String getBench1_goals() {
        return Bench1_goals;
    }

    public void setBench1_goals(String bench1_goals) {
        Bench1_goals = bench1_goals;
    }

    public String getBench2_goals() {
        return Bench2_goals;
    }

    public void setBench2_goals(String bench2_goals) {
        Bench2_goals = bench2_goals;
    }

    public String getBench3_goals() {
        return Bench3_goals;
    }

    public void setBench3_goals(String bench3_goals) {
        Bench3_goals = bench3_goals;
    }

    public String getBench4_goals() {
        return Bench4_goals;
    }

    public void setBench4_goals(String bench4_goals) {
        Bench4_goals = bench4_goals;
    }

    public String getBench5_goals() {
        return Bench5_goals;
    }

    public void setBench5_goals(String bench5_goals) {
        Bench5_goals = bench5_goals;
    }
}
