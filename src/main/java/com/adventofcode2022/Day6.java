package main.java.com.adventofcode2022;

import main.java.utilities.Utility;

import java.io.IOException;
import java.util.List;

public class Day6 {
    public static void main(String[] args) {
        System.out.println("Reading input file");
        List<String> inputList = null;
        try {
            String url = "src/main/resources/day6.txt";
            inputList = Utility.readFromInputStream(url);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("How many characters need to be processed before the first start-of-packet marker is detected? " + numberOfCharacters(inputList.get(0),4));
        System.out.println("How many characters need to be processed before the first start-of-message marker is detected? " + numberOfCharacters(inputList.get(0),14));
    }

    private static int numberOfCharacters(String dataStream,int numOfDistinctChars){
        for (int i = 0; i < dataStream.length(); i++) {
            if(i+numOfDistinctChars<=dataStream.length()){
                String substring = dataStream.substring(i,i+numOfDistinctChars);
                //System.out.println(substring + " index " + i);
                int count = 0;
                for(int j = 0; j < substring.length(); j++){
                    if(countOccurrencesOfACharInAString(substring.charAt(j), substring)==1){
                        count++;
                    }
                }
                if(count==numOfDistinctChars){
                    return i+numOfDistinctChars;
                }

            }
        }
        return 0;
    }

    private static int countOccurrencesOfACharInAString(Character character, String string){
        int count = 0;
        for (int i = 0; i < string.length() ; i++) {
            if(string.charAt(i)==character){
                count++;
            }
        }
        return count;
    }
}
