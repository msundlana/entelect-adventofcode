package main.java.com.adventofcode2022;

import main.java.utilities.Utility;

import java.io.IOException;
import java.util.List;

public class Day4 {

    public static void main(String[] args) {
        System.out.println("Reading input file");
        List<String> inputList = null;
        try {
            String url = "src/main/resources/day4.txt";
            inputList = Utility.readFromInputStream(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Number of overlapping pairs is " + getNumberOfAssignmentPairs(inputList));
        System.out.println("Number of overlapping pairs is " + getNumberOverlappingPairs(inputList));
    }

    private static Integer getNumberOfAssignmentPairs(List<String> pairs){
        int count = 0;
        for (String pair: pairs) {
            String [] pairArray = pair.split(",");
            String [] range1 = pairArray[0].split("-");
            String [] range2 = pairArray[1].split("-");

            SectionRange firstElfSectionRange = new SectionRange(Integer.parseInt(range1[0]),Integer.parseInt(range1[1]));
            SectionRange secondElfSectionRange = new SectionRange(Integer.parseInt(range2[0]),Integer.parseInt(range2[1]));

            if((isWithin(secondElfSectionRange.getStart(),firstElfSectionRange.getStart(),firstElfSectionRange.getEnd())
            && isWithin(secondElfSectionRange.getEnd(),firstElfSectionRange.getStart(),firstElfSectionRange.getEnd()))
            || (isWithin(firstElfSectionRange.getStart(),secondElfSectionRange.getStart(),secondElfSectionRange.getEnd())
                    && isWithin(firstElfSectionRange.getEnd(),secondElfSectionRange.getStart(),secondElfSectionRange.getEnd()))){
//                System.out.println(firstElfSectionRange.getStart() + " " + firstElfSectionRange.getEnd());
//                System.out.println(secondElfSectionRange.getStart() + " " + secondElfSectionRange.getEnd());
                count++;
            }


        }
        return count;
    }

    private static Integer getNumberOverlappingPairs(List<String> pairs){
        int count = 0;
        for (String pair: pairs) {
            String [] pairArray = pair.split(",");
            String [] range1 = pairArray[0].split("-");
            String [] range2 = pairArray[1].split("-");

            SectionRange firstElfSectionRange = new SectionRange(Integer.parseInt(range1[0]),Integer.parseInt(range1[1]));
            SectionRange secondElfSectionRange = new SectionRange(Integer.parseInt(range2[0]),Integer.parseInt(range2[1]));

            if((isWithin(secondElfSectionRange.getStart(),firstElfSectionRange.getStart(),firstElfSectionRange.getEnd())
                    || isWithin(secondElfSectionRange.getEnd(),firstElfSectionRange.getStart(),firstElfSectionRange.getEnd()))
                    || (isWithin(firstElfSectionRange.getStart(),secondElfSectionRange.getStart(),secondElfSectionRange.getEnd())
                    || isWithin(firstElfSectionRange.getEnd(),secondElfSectionRange.getStart(),secondElfSectionRange.getEnd()))){
//                System.out.println(firstElfSectionRange.getStart() + " " + firstElfSectionRange.getEnd());
//                System.out.println(secondElfSectionRange.getStart() + " " + secondElfSectionRange.getEnd());
                count++;
            }


        }
        return count;
    }

    public static boolean isWithin(int value, int minimum, int maximum)
    {
        return value >= minimum && value <= maximum;
    }
}

class SectionRange{
    private int start;
    private int end;

    public SectionRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
