import java.util.Scanner;
import java.io.File;
import java.util.stream.IntStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.stream.Collectors;
import java.util.List;

public class Day13 {
    ArrayList<String> report;
    ArrayList<int[]> dotList;
    ArrayList<String[]> foldInstructions;
    ArrayList<ArrayList<Character>> dots;
    int xDim;
    int yDim;
    
    public Day13(String fileName) {
        importFile(fileName);
        createDotList();
        createFoldInstructions();
        getXandY();
        createDotsTemp();
        createDots();
        System.out.println();
        //folding1();
        folding2();
        getDots();
        calculateScore();
    }

    public void importFile(String fileName) {
        this.report = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(new File(fileName));
            while(myReader.hasNextLine()) {
                String reportLine = myReader.nextLine();
                this.report.add(reportLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    public void createDotList() {
        this.dotList = new ArrayList<>();
        int i = 0;
        while (!report.get(i).isEmpty()) {
            String[] dotLineStr = this.report.get(i).split(",");
            int[] dotLine = new int[2];
            dotLine[0] = Integer.parseInt(dotLineStr[0]);
            dotLine[1] = Integer.parseInt(dotLineStr[1]);
            this.dotList.add(dotLine);
            i++;
        }
    }

    public void createFoldInstructions() {
        this.foldInstructions = new ArrayList<>();
        for (int i=dotList.size()+1; i<report.size(); i++) {
            String[] foldLine1 = report.get(i).split("=");
            String[] foldLineDirection = foldLine1[0].split(" ");
            String[] foldLine = new String[2];
            foldLine[0] = foldLineDirection[2];
            foldLine[1] = foldLine1[1];
            this.foldInstructions.add(foldLine);
        }
    }

    public void getXandY() {
        int length1 = (Integer.parseInt(this.foldInstructions.get(0)[1])*2) + 1;
        int length2 = (Integer.parseInt(this.foldInstructions.get(1)[1])*2) + 1;
        if (this.foldInstructions.get(0)[0].equals("x")) {
            this.xDim = length1;
            this.yDim = length2;
        } else {
            this.xDim = length2;
            this.yDim = length1;
        }
    }

    public void createDotsTemp() {
        dots = new ArrayList<>();
        
        for (int y=0; y<yDim; y++) {
            ArrayList<Character> dotLine = new ArrayList<>();
            for (int x=0; x<xDim; x++) {
                dotLine.add(' ');
            }
            dots.add(dotLine);
        }
    }

    public void createDots() {
        for (int[] dot : dotList) {
            dots.get(dot[1]).set(dot[0], '#');
        }
    }

    public void folding1() {
        int foldCoord = Integer.parseInt(foldInstructions.get(0)[1]);
        if (foldInstructions.get(0)[0].equals("y")) {
            foldingY(foldCoord);
        } else {
            foldingX(foldCoord);
        }
    }

    public void folding2() {
        for (String[] foldLine : this.foldInstructions) {
            int foldCoord = Integer.parseInt(foldLine[1]);
            if (foldLine[0].equals("x")) {
                foldingX(foldCoord);
            } else {
                foldingY(foldCoord);
            }
        }
    }

    public void foldingX(int foldCoord) {
        for (int y=0; y<dots.size(); y++) {
            for (int x=0; x<foldCoord; x++) {
                if (dots.get(y).get(dots.get(0).size()-1-x) == '#') {
                    this.dots.get(y).set(x, '#');
                }
            }
        }

        int xLength = dots.get(0).size();
        for (int y=0; y<dots.size(); y++) {
            for (int x=foldCoord; x<xLength; x++) {
                this.dots.get(y).remove(foldCoord);
            }
        }
    }

    public void foldingY(int foldCoord) {
        for (int y=0; y<foldCoord; y++) {
            for (int x=0; x<dots.get(0).size(); x++) {
                if (dots.get(dots.size()-1-y).get(x) == '#') {
                    this.dots.get(y).set(x, '#');
                }
            }
        }
        for (int i=0; i<=foldCoord; i++) {
            this.dots.remove(this.dots.size()-1);
        }
    }

    public void calculateScore() {
        int score = 0;
        for (ArrayList<Character> dotsLine : dots) {
            for (char dot : dotsLine) {
                if (dot == '#') {
                    score++;
                }
            }
        }
        System.out.println("Your score is: " + score);
    }

    public void getDots() {
        for (ArrayList<Character> dotsLine : dots) {
            System.out.println(dotsLine.toString());
        }
    }

    public static void main (String[] args) {
        // Test part 1
        new Day13("testinput.txt");

        // Part 1
        new Day13("input.txt");
    }
    
}
