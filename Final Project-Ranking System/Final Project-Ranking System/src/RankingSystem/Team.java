/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RankingSystem;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author betterjing
 */
public class Team {
    private int teamId;
    private String teamName;
    private int pointsExpectation = 0;
    private int winsCount = 0;
    private int failuresCount = 0;
    private int drawCount = 0;
   
    
    
    public Team(int teamId, String teamName)throws IOException{
        this.teamId = teamId;
        this.teamName = teamName;
        getTeamResult(CompetitionDirectory.competitionDirectory, teamId);
    }

    public int getTeamId() {
        return teamId;
    }


    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPointsExpectation() {
        return pointsExpectation;
    }

    public int getWinsCount() {
        return winsCount;
    }

    public int getFailuresCount() {
        return failuresCount;
    }

    public  int getDrawCount() {
        return drawCount;
    }
    
    
    
    public void getTeamResult(ArrayList<CompetitionInfo> competitionDirectory, int teamId){
        for(CompetitionInfo competitionInfo: competitionDirectory){
            if(competitionInfo.getHomeTeamId() == teamId){
                if(competitionInfo.getGoalDifference() > 0){
                    this.winsCount = this.winsCount + 1;
                }
                else if(competitionInfo.getGoalDifference() == 0){
                    this.drawCount = this.drawCount + 1;
                }
                else{
                    this.failuresCount = this.failuresCount + 1;
                }
            }
        }
        
        for(CompetitionInfo competitionInfo: competitionDirectory){
            if(competitionInfo.getAwayTeamId() == teamId){
                if(competitionInfo.getGoalDifference() < 0){
                    this.winsCount = this.winsCount + 1;
                }
                else if(competitionInfo.getGoalDifference() == 0){
                    this.drawCount = this.drawCount + 1;
                }
                else{
                    this.failuresCount = this.failuresCount + 1;
                }
            }
        }
        
        this.pointsExpectation = 3 * winsCount + 1 * drawCount;
    }
    
    @Override
    public String toString() {
        return "Team{"+ "Team Id : " + teamId + ", Team Name : " + teamName + ", Wins = " + winsCount + ", Draw = " + drawCount + ", Failures = " + failuresCount + ", Total Points = " + pointsExpectation + '}';
    }
}
