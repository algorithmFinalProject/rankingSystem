/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RankingSystemTest;

import RankingSystem.CompetitionDirectory;
import RankingSystem.ForecastForThisSeason;
import RankingSystem.TeamCompetitionResultDirectory;
import RankingSystem.TeamDirectory;
import java.io.IOException;

/**
 *
 * @author betterjing
 */
public class RankingSystemTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException {
        CompetitionDirectory competitionDirectory=new CompetitionDirectory();
        TeamDirectory teamDirectory = new TeamDirectory();
        teamDirectory.getTeamRanking();
        TeamCompetitionResultDirectory teamCompetitionResults = new TeamCompetitionResultDirectory();
        ForecastForThisSeason f = new ForecastForThisSeason(competitionDirectory,teamCompetitionResults, teamDirectory);
    }
    
}
