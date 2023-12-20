package main.java.adventofcode2023;

import main.java.utilities.Utility;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day1 {
    public static void main(String [] args){
        System.out.println("Reading input file");
        List<String> inputList = null;
        try {
            String url = "src/main/resources/year2023/day1.txt";
            inputList = Utility.readFromInputStream(url);
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println(getSumOfAllCalibrationValues(inputList));

    }


    static int getSumOfAllCalibrationValues(List<String> inputList){
        int sum = 0;
        for (String input:inputList) {
            sum+=getFirstAndLastDigit2(input);
        }

        return sum;
    }
    static Integer getFirstAndLastDigit(String input) {

        String digits = "";
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                digits += c;
            }
        }

        return digits.length()>0?Integer.parseInt(digits.charAt(0)+""+digits.charAt(digits.length()-1)):0;
    }

    static Integer getFirstAndLastDigit2(String input){
        String first = "";
        String last = "";
        List<String> numberWords = numberWords();
        for (String number: numberWords) {
            if(input.indexOf(number)!=-1){
                if(first.length()==0){
                    first = number;
                    last = number;
                }
                if(input.indexOf(first)>input.indexOf(number) ) {
                   first = number;
                } else if(input.indexOf(last)<input.indexOf(number) ) {
                    last = number;
                }
            }

        }

        if(first.length()>1){
            first = numberWords.indexOf(first) + "";
        }

        if(last.length()>1){
            last = numberWords.indexOf(last) + "";
        }

        System.out.println(Integer.parseInt(first+last));

        return Integer.parseInt(first+last);
    }

    static List<String> numberWords(){
        List<String>  arrayList = Arrays.asList("zero","one", "two", "three", "four", "five",
                "six", "seven", "eight", "nine","0","1","2","3","4","5","6","7","8","9");
        return arrayList;
    }


}
