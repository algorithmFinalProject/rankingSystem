/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RankingSystem;

import java.io.IOException;
import java.text.DecimalFormat;

import static java.lang.Math.sqrt;
import java.util.ArrayList;

/**
 *
 * @author betterjing
 */
public class TeamCompetitionResult {
    private int homeTeamId;
    private int awayTeamId;
    private String homeTeamName;
    private String awayTeamName;
    private int goalsMean;
    private double goalsStd;
    private double expectedValue;
    private int count = 0;
    private int totalGoals = 0;
    private double variance = 0;
    private double PointsExpectation = 0;
    private double PointsExpectationForAwayTeam = 0;
    private String teamName;
    
    public TeamCompetitionResult(int homeTeamId, int awayTeamId)throws IOException{
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.homeTeamName = CheckName(this.homeTeamId);
        this.awayTeamName = CheckName(this.awayTeamId);
        CompetitionDirectory competitionTeamRecords = new CompetitionDirectory();
        ArrayList<CompetitionInfo> competitionResultRecords = new ArrayList<CompetitionInfo>();
        competitionResultRecords = competitionTeamRecords.getCompetitionTeamRecords(homeTeamId, awayTeamId);
        
        for(CompetitionInfo competitionInfo: competitionResultRecords){
            totalGoals = totalGoals + competitionInfo.getGoalDifference();
            count = count + 1;
        }
        
        if(count != 0){
            this.goalsMean = totalGoals / count;
        }
        else{
            this.goalsMean = 0;
        }
        this.expectedValue = goalsMean;
        
        for(CompetitionInfo competitionInfo: competitionResultRecords){
            variance = variance + (competitionInfo.getGoalDifference() - this.goalsMean) * (competitionInfo.getGoalDifference() - this.goalsMean);
        }
        
        if(count != 0){
            this.goalsStd = sqrt(variance / count);
        }
        else{
            this.goalsStd = 0;
        }
        
        if(count !=0) {
        	if(this.goalsMean-2*this.goalsStd>0) 
        		this.PointsExpectation = 0.0215*1 + 0.9785*3;
        	else if(this.goalsMean - 2*this.goalsStd <= 0 && this.goalsMean - this.goalsStd > 0)
        		this.PointsExpectation = 0.136 + 0.8425*3 ; 
        	else if(this.goalsMean - this.goalsStd <= 0 && this.goalsMean > 0)
        		this.PointsExpectation = 0.341 + 0.5*3 ;
        	else if(this.goalsMean <=0 && this.goalsMean + this.goalsStd > 0)
        		this.PointsExpectation = 0.341 + 0.1575*3 ;
        	else if(this.goalsMean + this.goalsStd <= 0 && this.goalsMean + 2*this.goalsStd > 0)
        		this.PointsExpectation = 0.136 + 0.0215*3 ;
        	else 
        		this.PointsExpectation = 0.0215 ;
        }
        else {
        	this.PointsExpectation = 0;
        }
        //because we consider 'HomeTeam A vs AwayTeam B' as a distinct set of data 
        //(not relate to 'HomeTeam B vs AwayTeam A' at all)
        //we can also calculate the points expectation for away team for this particular game.
        //the goalsMean for Away Team is -goalsMean for HomeTeam
        if(count !=0) {
        	if(-this.goalsMean-2*this.goalsStd>0) 
        		this.PointsExpectationForAwayTeam = 0.0215*1 + 0.9785*3;
        	else if(-this.goalsMean - 2*this.goalsStd <= 0 && -this.goalsMean - this.goalsStd > 0)
        		this.PointsExpectationForAwayTeam = 0.136 + 0.8425*3 ; 
        	else if(-this.goalsMean - this.goalsStd <= 0 && -this.goalsMean > 0)
        		this.PointsExpectationForAwayTeam = 0.341 + 0.5*3 ;
        	else if(-this.goalsMean <=0 && -this.goalsMean + this.goalsStd > 0)
        		this.PointsExpectationForAwayTeam = 0.341 + 0.1575*3 ;
        	else if(-this.goalsMean + this.goalsStd <= 0 && -this.goalsMean + 2*this.goalsStd > 0)
        		this.PointsExpectationForAwayTeam = 0.136 + 0.0215*3 ;
        	else 
        		this.PointsExpectation = 0.0215 ;
        }
        else {
        	this.PointsExpectation = 0;
        }
    }

    public int getHomeTeamId() {
        return homeTeamId;
    }

    public int getAwayTeamId() {
        return awayTeamId;
    }

    public double getExpectedValue() {
        return expectedValue;
    }
    
    public int getGoalsMean() {
        return goalsMean;
    }

    public double getGoalsStd() {
        return goalsStd;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }
    
    public double getPointsExpectation() {
    	return PointsExpectation;
    }
    
    public double getPointsExpectationForAwayTeam() {
    	return PointsExpectationForAwayTeam;
    }
    @Override
    public String toString() {
    	DecimalFormat df = new DecimalFormat("0.0000");   
    	
        return "Competition{" + "NO." + homeTeamId + " " + homeTeamName 
        		+ " VS No." + awayTeamId + " " + awayTeamName 
        		+ ", Expected Goal Difference = " + expectedValue 
        		+ ", Std = " + df.format(goalsStd) 
        		+ ", Points Expectation = " + df.format(PointsExpectation)
        		+ '}';
    }
    
    public String CheckName(int teamId)throws IOException{
        TeamDirectory teamDirectory = new TeamDirectory();
        for(Team team: teamDirectory.getTeamDirectory()){
            if(team.getTeamId() == teamId){
                teamName = team.getTeamName();
            }
        }
        return teamName;
    }
}
