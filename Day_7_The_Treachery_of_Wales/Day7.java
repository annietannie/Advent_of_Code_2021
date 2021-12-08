import java.util.Scanner;
import java.io.File;
import java.util.stream.IntStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.stream.Collectors;
import java.util.List;

public class Day7 {
    int[] crabs;
    int numberCrabs;
    int minCrab = 0;
    int maxCrab = 0;
    int part;

    public Day7(String fileName, int part) {
        this.part = part;
        importFile(fileName);
        //System.out.println(Arrays.toString(crabs));
        if (this.part == 1) {
            findBestPosition();
        } else {
            findBestPosition2();
        }
        
    }
    
    public void importFile(String fileName) {
        try {
            Scanner myReader = new Scanner(new File(fileName));
            while(myReader.hasNextLine()) {
                String[] dataLines = myReader.nextLine().split(",");
                numberCrabs = dataLines.length;
                crabs = new int[numberCrabs];
                for (int i= 0; i<numberCrabs; i++) {
                    crabs[i] = Integer.parseInt(dataLines[i]);
                    if (crabs[i] > this.maxCrab) {
                        this.maxCrab = crabs[i];
                    }
                    if (crabs[i] < this.minCrab) {
                        this.minCrab = crabs[i];
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    public void findBestPosition() {
        int lastDistance = createDistanceArray(this.minCrab);
        for (int i=this.minCrab+1; i<this.maxCrab; i++) {
            int distance = createDistanceArray(i);
            //System.out.println("Last position and fuel consumption: " + (i-1) + ", " + lastDistance);
            //System.out.println("Current position and fuel consumption: " + i + ", " + distance);
            if (distance > lastDistance) {
                System.out.println("Optimal position is: " + (i-1) + " with fuel usage: " + lastDistance);
                break;
            }
            lastDistance = distance;
        }
    }

    public int createDistanceArray(int position) {
        int distance = 0;
        for (int crab : crabs) {
            distance += Math.abs(crab - position);
        }
        return distance;
    }
    
    public void findBestPosition2() {
        int lastDistance = createDistanceArray2(this.minCrab);
        for (int i=this.minCrab+1; i<this.maxCrab; i++) {
            int distance = createDistanceArray2(i);
            //System.out.println("Last position and fuel consumption: " + (i-1) + ", " + lastDistance);
            //System.out.println("Current position and fuel consumption: " + i + ", " + distance);
            if (distance > lastDistance) {
                System.out.println("Optimal position is: " + (i-1) + " with fuel usage: " + lastDistance);
                break;
            }
            lastDistance = distance;
        }
    }

    public int createDistanceArray2(int position) {
        int distance = 0;
        for (int crab : crabs) {
            int n = Math.abs(crab - position);
            distance += n*(n+1)/2;
        }
        return distance;
    }

    public static void main (String[] args) {
        // Test part 1
        new Day7("testinput.txt",1);

        // part 1
        new Day7("input.txt",1);

        // Test part 2
        new Day7("testinput.txt",2);

        // part 2
        new Day7("input.txt",2);
    }
}
