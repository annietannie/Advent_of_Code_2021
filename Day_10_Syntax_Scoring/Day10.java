import java.util.Scanner;
import java.io.File;
import java.util.stream.IntStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.stream.Collectors;
import java.util.List;

public class Day10 {
    ArrayList<char[]> report = new ArrayList<>();
    ArrayList<Long> scoreListPart2 = new ArrayList<>();
    int scorePart1;
    long scorePart2;

    public Day10(String fileName) {
        this.scorePart1 = 0;
        this.scorePart2 = 0;
        importFile(fileName);
        scanReport();
        //getReport();
        calculateIncompletionPoints();
 
        System.out.println("Score for corrupted lines: " + this.scorePart1);
        System.out.println("Score for incomplete lines: " + this.scorePart2);
    }

    public void importFile(String fileName) {
        try {
            Scanner myReader = new Scanner(new File(fileName));
            while(myReader.hasNextLine()) {
                char[] reportLine = myReader.nextLine().toCharArray();
                this.report.add(reportLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    } 

    public void scanReport() {
        ArrayList<Character> chunks = new ArrayList<>();
        for (int i=0; i<report.size(); i++) {
            if (chunks.size() > 0) {
                scoreListPart2.add(setPointsForIncompletion(chunks));
            }
            chunks.clear();
            lineloop:
            for (char chunk : report.get(i)) {
                if (chunk == '(' || chunk == '[' || chunk == '{' || chunk == '<') {
                    chunks.add(chunk);
                }
                if (chunk == ')' || chunk == ']' || chunk == '}' || chunk == '>') {
                    char lastChunk = chunks.get(chunks.size()-1);
                    if (
                        (chunk == ')' && lastChunk == '(') ||
                        (chunk == ']' && lastChunk == '[') ||
                        (chunk == '}' && lastChunk == '{') ||
                        (chunk == '>' && lastChunk == '<')
                    ) {
                        chunks.remove(chunks.size()-1);
                    } else {
                        setPointsForCorruption(chunk);
                        chunks.clear();
                        report.remove(i);
                        i--;
                        break lineloop;
                    }
                }
                
            }
        }
        scoreListPart2.add(setPointsForIncompletion(chunks));  
    }

    public void calculateIncompletionPoints() {
        Collections.sort(scoreListPart2);
        //System.out.println("Incompletion scores: " + scoreListPart2.toString());
        this.scorePart2 = scoreListPart2.get(scoreListPart2.size()/2);
    }

    public long setPointsForIncompletion(ArrayList<Character> chunks) {
        long scoringPart2 = 0;
        for (int i=chunks.size()-1 ; i>=0; i--) {
            char chunk = chunks.get(i);
            scoringPart2 *= 5;
            switch(chunk) {
                case '(':
                    scoringPart2 += 1;
                    break;
                case '[':
                    scoringPart2 += 2;
                    break;
                case '{':
                    scoringPart2 += 3;
                    break;
                case '<':
                    scoringPart2 += 4;
                    break;
            }
        }
        return scoringPart2;
    }

    public void setPointsForCorruption(char chunk) {
        switch(chunk) {
            case ')':
                this.scorePart1 += 3;
                break;
            case ']':
                this.scorePart1 += 57;
                break;
            case '}':
                this.scorePart1 += 1197;
                break;
            case '>':
                this.scorePart1 += 25137;
        }
    }

    public void getReport() {
        for (char[] chunks : report) {
            System.out.println(Arrays.toString(chunks));
        }
    }

    public static void main (String[] args){
        // Test part 1
        new Day10("testinput.txt");

        // part 1
        new Day10("input.txt");
    }
}
