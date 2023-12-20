package main.java.com.adventofcode2022;

import main.java.utilities.Utility;

import java.io.IOException;
import java.util.*;

public class Day2 {

    public static void main(String [] args) {
        System.out.println("Reading input file");
        List<String> inputList = null;
        try {
            String url = "src/main/resources/day2.txt";
            inputList = Utility.readFromInputStream(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("First answer is " + getTotalScore(inputList));
        System.out.println("Second answer is "+ getTotalScore2(inputList));
    }

    private static Map<String,List<String>> initializeScoreBoard(){
        Map<String,List<String>> listMap = new HashMap<>();
        List<String> drawList = Arrays.asList("A X","B Y","C Z");
        List<String> wonList = Arrays.asList("C X","A Y","B Z");
        List<String> lostList = Arrays.asList("A Z","B X","C Y");
        listMap.put("won",wonList);
        listMap.put("draw",drawList);
        listMap.put("lost",lostList);
        return listMap;
    }

    private static Integer getTotalScore(List<String> playList){
        int totalScore = 0;
        Map<String,List<String>> rockPaperScissorsGame = initializeScoreBoard();
        List<String> drawList = rockPaperScissorsGame.get("draw");
        List<String> wonList = rockPaperScissorsGame.get("won");
        List<String> lostList = rockPaperScissorsGame.get("lost");
        Map<String,Integer> pointsMap = new HashMap<>();
        pointsMap.put("X",1);
        pointsMap.put("Y",2);
        pointsMap.put("Z",3);
        for (String play: playList) {
            String [] plays = play.split(" ");
            if (drawList.contains(play)){
                totalScore+=pointsMap.get(plays[1]) + 3;
            }else if (wonList.contains(play)){
                totalScore+=pointsMap.get(plays[1]) + 6;
            }else if (lostList.contains(play)){
                totalScore+=pointsMap.get(plays[1]) + 0;
            }
        }
        return totalScore;
    }

    private static Integer getTotalScore2(List<String> playList){
        int totalScore = 0;
        Map<String,Integer> drawMap = new HashMap<>();
        drawMap.put("A",1);
        drawMap.put("B",2);
        drawMap.put("C",3) ;
        Map<String,Integer> wonMap = new HashMap<>();
        wonMap.put("C",1);
        wonMap.put("A",2);
        wonMap.put("B",3);
        Map<String,Integer> lostMap = new HashMap<>();
        lostMap.put("A",3);
        lostMap.put("B",1);
        lostMap.put("C",2);
        for (String play: playList) {
            String [] plays = play.split(" ");
            if(plays[1].equals("X")){
                totalScore+=lostMap.get(plays[0]) + 0;
            }else if(plays[1].equals("Y")){
                totalScore+=drawMap.get(plays[0]) + 3;
            }else if(plays[1].equals("Z")){
                totalScore+=wonMap.get(plays[0]) + 6;
            }
        }
        return totalScore;
    }
}
