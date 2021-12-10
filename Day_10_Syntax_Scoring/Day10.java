import java.util.Scanner;
import java.io.File;
import java.util.stream.IntStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.stream.Collectors;
import java.util.List;

public class Day10 {
    ArrayList<char[]> report = new ArrayList<>();
    int score;

    public Day10(String fileName) {
        this.score = 0;
        importFile(fileName);
        findCorruptedLines();
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

    public void findCorruptedLines() {
        for (char[] line : report) {
            ArrayList<Character> chunks = new ArrayList<>();
            lineloop:
            for (char chunk : line) {
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
                        switch(chunk) {
                            case ')':
                                this.score += 3;
                                break;
                            case ']':
                                this.score += 57;
                                break;
                            case '}':
                                this.score += 1197;
                                break;
                            case '>':
                                this.score += 25137;
                        }
                        break lineloop;
                    }
                }

            }
        }
        System.out.println("Score for part 1: " + this.score);
    }

    public static void main (String[] args){
        // Test part 1
        new Day10("testinput.txt");

        // part 1
        new Day10("input.txt");
    }
}
