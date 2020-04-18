/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RankingSystem;

/**
 *
 * @author betterjing
 */
public class CompetitionInfo {
    private int competiNum;
    private int homeTeamId;
    private int awayTeamId;
    private int homeTeamGoals;
    private int awayTeamGoals;
    private int goalDifference;
    private int competiYear;
    private static int count = 0;
    
    public CompetitionInfo(int homeTeamId, int awayTeamId, int homeTeamGoals, int awayTeamGoals, int goalDifference, int competiYear){
        this.competiNum = count;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.goalDifference = goalDifference;
        this.competiYear = competiYear;
        count = count + 1;
    }

    public int getCompetiNum() {
        return competiNum;
    }

    public int getHomeTeamId() {
        return homeTeamId;
    }

    public int getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(int awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public int getCompetiYear() {
        return competiYear;
    }

    public void setCompetiYear(int competiYear) {
        this.competiYear = competiYear;
    }
    
    
}
