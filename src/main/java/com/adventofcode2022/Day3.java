package main.java.com.adventofcode2022;

import main.java.utilities.Utility;

import java.io.IOException;
import java.util.List;

public class Day3 {
    public static void main(String[] args) {
        System.out.println("Reading input file");
        List<String> inputList = null;
        try {
            String url = "src/main/resources/day3.txt";
            inputList = Utility.readFromInputStream(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Result to Question 1 = " + getItemsPrioritySumQuestion1(inputList));
        System.out.println("Result to Question 2 = " + getItemsPrioritySumQuestion2(inputList));

    }

    private static Integer getItemsPrioritySumQuestion1(List<String> inputList){
        String alphabets = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int sum = 0;
        for (String rucksack:inputList) {
            Character item = getRepeatingItem(rucksack);
            if(item!=null){
                sum+=alphabets.indexOf(item)+1;
            }
        }
        return sum;
    }

    private static Character getRepeatingItem(String rucksack){
        String firstCompartment = rucksack.substring(0,rucksack.length()/2);
        String secondCompartment = rucksack.substring(rucksack.length()/2,rucksack.length());
        for(int index=0; index<firstCompartment.length(); index++){
            Character item = firstCompartment.charAt(index);
            if(secondCompartment.contains(String.valueOf(item))){
                return item;
            }
        }
        return null;
    }

    private static Integer getItemsPrioritySumQuestion2(List<String> inputList){
        String alphabets = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int sum = 0;
        for (int index=0; index<inputList.size(); index=index+3) {
            Character item = findGroupBadge(inputList.subList(index,index+3));
            if(item!=null){
                sum+=alphabets.indexOf(item)+1;
            }
        }
        return sum;
    }

    private static Character findGroupBadge(List<String> inputList){
        for(int index=0; index<inputList.get(0).length(); index++){
            Character item = inputList.get(0).charAt(index);
            if(inputList.get(1).contains(String.valueOf(item)) &&
                    inputList.get(2).contains(String.valueOf(item))){
                return item;
            }
        }
        return null;
    }


}
