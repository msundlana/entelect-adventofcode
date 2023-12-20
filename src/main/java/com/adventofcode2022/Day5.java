package main.java.com.adventofcode2022;

import main.java.utilities.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {
    public static void main(String[] args) {
        System.out.println("Reading input file");
        List<String> inputList = null;
        try {
            String url = "src/main/resources/day5.txt";
            inputList = Utility.readFromInputStream(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<List<Character>> stacks1 = getStacks();
        List<List<Character>> stacks2 = getStacks();
        getRearrangedStacks(inputList,stacks1);
        getRearrangedStacksMultiple(inputList,stacks2);
        System.out.println("Top crates Q!"+getTopCrates(stacks1));
        System.out.println("Top crates Q2" + getTopCrates(stacks2));
    }

    private static  List<List<Character>>  getStacks(){
        List<List<Character>> listOfStacks = new ArrayList<>();
        listOfStacks.add(Arrays.asList('T','F','V','Z','C','W','S','Q'));
        listOfStacks.add(Arrays.asList('B','R','Q'));
        listOfStacks.add(Arrays.asList('S','M','P','Q','T','Z','B'));
        listOfStacks.add(Arrays.asList('H','Q','R','F','V','D'));
        listOfStacks.add(Arrays.asList('P','T','S','B','D','L','G','J'));
        listOfStacks.add(Arrays.asList('Z','T','R','W'));
        listOfStacks.add(Arrays.asList('J','R','F','S','N','M','Q','H'));
        listOfStacks.add(Arrays.asList('W','H','F','N','R'));
        listOfStacks.add(Arrays.asList('B','R','P','Q','T','Z','J'));
        return listOfStacks;
    }

    private static void getRearrangedStacks(List<String> inputList, List<List<Character>> listOfStacks) {
        for (String step : inputList) {
            System.out.println(step);
            String[] stepArray = step.split(" ");
            int move = Integer.parseInt(stepArray[1]);
            int from = Integer.parseInt(stepArray[3])-1;
            int to = Integer.parseInt(stepArray[5])-1;
            List<Character> sublist = listOfStacks.get(from).subList(0, move);
            List addToList = new ArrayList();
            List moveFromList =  new ArrayList(listOfStacks.get(from));
            addToList.addAll(listOfStacks.get(to));
            for(int index=0; index<sublist.size(); index++){
                addToList.add(0,moveFromList.remove(0));
            }

            listOfStacks.remove(to);
            listOfStacks.add(to,addToList);
            System.out.println("Move from " + moveFromList);
            System.out.println("Add to " + addToList);
            listOfStacks.remove(from);
            listOfStacks.add(from,moveFromList);
            System.out.println(listOfStacks);

        }

    }

    private static void getRearrangedStacksMultiple(List<String> inputList, List<List<Character>> listOfStacks) {
        for (String step : inputList) {
            System.out.println(step);
            String[] stepArray = step.split(" ");
            int move = Integer.parseInt(stepArray[1]);
            int from = Integer.parseInt(stepArray[3])-1;
            int to = Integer.parseInt(stepArray[5])-1;
            List<Character> sublist = listOfStacks.get(from).subList(0, move);
            List addToList = new ArrayList(sublist);
            addToList.addAll(listOfStacks.get(to));
            System.out.println("Add to " + addToList);
            listOfStacks.remove(to);
            listOfStacks.add(to,addToList);
            List moveFromList =  new ArrayList(listOfStacks.get(from));
            for(int index=0; index<sublist.size(); index++){
                moveFromList.remove(0);
            }
            System.out.println("Move from " + moveFromList);
            listOfStacks.remove(from);
            listOfStacks.add(from,moveFromList);
            System.out.println(listOfStacks);

        }

    }

    private static List<Character> getTopCrates(List<List<Character>> listOfStacks) {
        return listOfStacks.stream().map(stack -> {
            return stack.get(0);
        }).collect(Collectors.toList());
    }

}
