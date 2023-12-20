package main.java.com.adventofcode2022;

import main.java.utilities.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {
    public static void main(String [] args){
        System.out.println("Reading input file");
        List<String> inputList = null;
        try {
            String url = "src/main/resources/day1.txt";
            inputList = Utility.readFromInputStream(url);
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println(inputList);

        System.out.println("Find the Elf carrying the most Calories");
        System.out.println(findElfCarryingMostCalories(inputList));

        System.out.println("Find the Top Three Elfs carrying the most Calories");
        System.out.println(findTopThreeElfCarryingMostCalories(inputList));
    }

    private static Integer findElfCarryingMostCalories(List<String> inputList){
        int mostCalories = 0;
        int totalCalories = 0;
        for (String calories: inputList) {
            if(calories.isEmpty()){
                mostCalories = Math.max(mostCalories,totalCalories);
                totalCalories = 0;
                continue;
            }
            totalCalories += Integer.parseInt(calories);

        }
        return Math.max(mostCalories,totalCalories);
    }

    private static Integer findTopThreeElfCarryingMostCalories(List<String> inputList){
        int totalCalories = 0;
        List<Integer> totalCaloriesList = new ArrayList<>();
        for (String calories: inputList) {
            if(calories.isEmpty()){
                totalCaloriesList.add(totalCalories);
                totalCalories = 0;
                continue;
            }
            totalCalories += Integer.parseInt(calories);

        }
        totalCaloriesList.add(totalCalories);
        return findTheSum(totalCaloriesList);
    }

    private static Integer findTheSum(List<Integer> totalCaloriesList){
        Collections.sort(totalCaloriesList);
        Collections.reverse(totalCaloriesList);
        return totalCaloriesList.subList(0,3).stream().reduce(0,Integer::sum);
    }


}
