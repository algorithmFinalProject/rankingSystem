/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RankingSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author betterjing
 */
public class TeamDirectory {
    ArrayList<Team> teamDirectory;
    
    public TeamDirectory()throws IOException{
        teamDirectory = new ArrayList<Team>();
        
        Team team1 = new Team(1, "Arsenal");
        teamDirectory.add(team1);
        
        Team team2 = new Team(2, "Aston Villa");
        teamDirectory.add(team2);
        
        Team team3 = new Team(3, "Bournemouth");
        teamDirectory.add(team3);
        
        Team team4 = new Team(4, "Brighton");
        teamDirectory.add(team4);
        
        Team team5 = new Team(5, "Burnley");
        teamDirectory.add(team5);
        
        Team team6 = new Team(6, "Chelsea");
        teamDirectory.add(team6);
        
        Team team7 = new Team(7, "Crystal Palace");
        teamDirectory.add(team7);
        
        Team team8 = new Team(8, "Everton");
        teamDirectory.add(team8);
        
        Team team9 = new Team(9, "Leicester");
        teamDirectory.add(team9);
        
        Team team10 = new Team(10, "Liverpool");
        teamDirectory.add(team10);
        
        Team team11 = new Team(11, "Man City");
        teamDirectory.add(team11);
        
        Team team12 = new Team(12, "Man United");
        teamDirectory.add(team12);
        
        Team team13 = new Team(13, "Newcastle");
        teamDirectory.add(team13);
        
        Team team14 = new Team(14, "Norwich");
        teamDirectory.add(team14);
        
        Team team15 = new Team(15, "Sheffield United");
        teamDirectory.add(team15);
        
        Team team16 = new Team(16, "Southampton");
        teamDirectory.add(team16);
        
        Team team17 = new Team(17, "Tottenham");
        teamDirectory.add(team17);
        
        Team team18 = new Team(18, "Watford");
        teamDirectory.add(team18);
        
        Team team19 = new Team(19, "West Ham");
        teamDirectory.add(team19);
        
        Team team20 = new Team(20, "Wolves");
        teamDirectory.add(team20);
    }

    public ArrayList<Team> getTeamDirectory() {
        return teamDirectory;
    }
    
    public Team addTeam(Team team){
        teamDirectory.add(team);
        return team;
    }
    
    public void getTeamRanking(){
        Comparator<Team> teamRanking = new Comparator<Team>(){
            public int compare(Team team1, Team team2){
                return team2.getPointsExpectation() - team1.getPointsExpectation();
            }
        };
        
        Collections.sort(teamDirectory, teamRanking);
        
        System.out.println("The Ranking Of ALL Teams Based On Their Points Accumulated Over Five Years:");
        
        for(int j=0;j<teamDirectory.size();j++){
            System.out.println(teamDirectory.get(j));
        }
            System.out.println("\n");
    }
}
