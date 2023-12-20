package main.java.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Utility {
    public static List<String> readFromInputStream(String url)
            throws IOException {
        try (FileReader fileReader = new FileReader(url)){
            BufferedReader br
                    = new BufferedReader(fileReader);
            return br.lines().collect(Collectors.toList());
        }
    }
}
