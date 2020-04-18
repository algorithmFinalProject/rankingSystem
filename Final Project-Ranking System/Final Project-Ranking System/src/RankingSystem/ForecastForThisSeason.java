package RankingSystem;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ForecastForThisSeason {

    private CompetitionDirectory cd;
    private TeamCompetitionResultDirectory td;
    String teamName;

    public ForecastForThisSeason(CompetitionDirectory cd, TeamCompetitionResultDirectory td, TeamDirectory teamDirectory) {
        this.cd = cd;
        this.td = td;
        HashMap<Integer, Double> resultMap = new HashMap<Integer, Double>();
        //use resultMap to calculate total points for each team, key is teamID, value is points
        HashMap<Integer, Boolean> RecordMap = new HashMap<Integer, Boolean>();
        //use RecordMap to distinguish finished game and future game , key is GameID. 
        //If this game is finished , then value is true, else value is false
        //GameID = HomeTeamID*20 +AwayTeamID, there is only one game ID for a pair of specific HomeTeam and AwayTeam
        //HomeTeamID = GameID/20, AwayTeamID = GameID%20
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 20; j++) {
                if (i != j) {
                    RecordMap.put(i * 20 + j, false);
                }
            }
        }
        for (int i = 1; i <= 20; i++) {            //initialize resultMap
            resultMap.put(i, 0.00);
        }
        for (CompetitionInfo c : cd.getCompetitionDirectory()) {
            if (c.getCompetiYear() == 2019) {
                int gameID = c.getHomeTeamId() * 20 + c.getAwayTeamId();
                RecordMap.put(gameID, true);//update recordMap
                if (c.getGoalDifference() > 0) // HomeTeam Win
                {
                    resultMap.put(c.getHomeTeamId(), 3.00 + resultMap.get(c.getHomeTeamId()));
                } else if (c.getGoalDifference() == 0) {// Draw
                    resultMap.put(c.getHomeTeamId(), 1.00 + resultMap.get(c.getHomeTeamId()));
                    resultMap.put(c.getAwayTeamId(), 1.00 + resultMap.get(c.getAwayTeamId()));
                } else {//HomeTeam Fail 
                    resultMap.put(c.getAwayTeamId(), 3.00 + resultMap.get(c.getAwayTeamId()));
                }
            }
        } 
        for (TeamCompetitionResult t : td.getTeamCompetitionResultDirectory()) {// input the expectation of future games
            if (!RecordMap.get(t.getHomeTeamId() * 20 + t.getAwayTeamId())) {// if the game is in future
                resultMap.put(t.getHomeTeamId(), t.getPointsExpectation() + resultMap.get(t.getHomeTeamId()));
                //add our expectation of this game in HomeTeam's total points
                resultMap.put(t.getAwayTeamId(), t.getPointsExpectationForAwayTeam() + resultMap.get(t.getAwayTeamId()));
                // add our expectation of this game in AwayTeam's total points
            }
        }

        //
        System.out.println("\n");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\n");
        System.out.println("Ranking of The Forcast for This Season:");

        DecimalFormat df = new DecimalFormat("0.0000");

        List<Map.Entry<Integer, Double>> results = new ArrayList<Map.Entry<Integer, Double>>(resultMap.entrySet());
        Collections.sort(results, new Comparator<Map.Entry<Integer, Double>>() {
            public int compare(Map.Entry<Integer, Double> result1,
                    Map.Entry<Integer, Double> result2) {
                return (result2.getValue()).toString().compareTo(result1.getValue().toString());
            }
        });

        for (int i = 0; i < results.size(); i++) {
            int teamId = results.get(i).getKey();
            double points = results.get(i).getValue();
            for (Team team : teamDirectory.getTeamDirectory()) {
                if (team.getTeamId() == teamId) {
                    teamName = team.getTeamName();
                }
            }
            System.out.print("Team{" + "Team Id : " + teamId + ", Team Name : " + teamName + ", Total Points Forecast : " + df.format(points) + '}' + "\n");
//                    System.out.println("\n");
        }
        System.out.println("\n");
//		 Set<Integer> keySet = resultMap.keySet();
//		 Iterator<Integer> it1 = keySet.iterator();
//		 while(it1.hasNext()){
//				int ID = it1.next();
//				double points = resultMap.get(ID);
//				System.out.println("TeamId: "+ ID +" Total Points Forecast:"+ df.format(points));
//			}

    }
}
