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
public class TeamCompetitionResultDirectory {
    ArrayList<TeamCompetitionResult> teamCompetitionResultDirectory;
    
    public TeamCompetitionResultDirectory()throws IOException{
        teamCompetitionResultDirectory = new ArrayList<TeamCompetitionResult>();
        
        for(int i = 1; i <= 20; i++){
            for(int j = 1; j <= 20; j++){
                if(i != j){
                    TeamCompetitionResult teamCompetitionResult = new TeamCompetitionResult(i, j);
                    teamCompetitionResultDirectory.add(teamCompetitionResult);
                }
            }
        }

        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\n");
        System.out.println("Competition Probability Distribution:");
        
        for(TeamCompetitionResult teamCompetitionResult: teamCompetitionResultDirectory){
            System.out.println(teamCompetitionResult);
        }
    }

    public ArrayList<TeamCompetitionResult> getTeamCompetitionResultDirectory() {
        return teamCompetitionResultDirectory;
    }
    
    public TeamCompetitionResult addTeamCompetitionResult(TeamCompetitionResult teamCompetitionResult){
        teamCompetitionResultDirectory.add(teamCompetitionResult);
        return teamCompetitionResult;
    }
}
