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

public class Day6 {
    long[] fishies = new long[9];
    long days;
    long score;
    long lastStageFishies;

    public Day6(String fileName, long days) {
        this.days = days;
        Arrays.fill(this.fishies, 0);
        importFile(fileName);
        justKeepSwimming();
        calcScore();
    }

    public void importFile(String fileName) {
        try {
            Scanner myReader = new Scanner(new File(fileName));
            while(myReader.hasNextLine()) {
                String[] fishieS = myReader.nextLine().split(",");
                for (String fish : fishieS) {
                    int fishNr = Integer.parseInt(fish);
                    fishies[fishNr]++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    public void justKeepSwimming() {
        long day = 1;
        while (day < this.days+1) {
            newDay();
            day++;
        }
    }

    public void newDay() {
        for (int gen=8; gen>=0; gen--) {
            long tempFish = this.lastStageFishies;
            this.lastStageFishies = fishies[gen];
            if (gen == 8) {
                fishies[gen] = fishies[0];
            } else if (gen == 6) {
                fishies[gen] = tempFish + fishies[0];
            } else {
                fishies[gen] = tempFish;
            }
        }
    }

    public void calcScore() {
        for (long fish : fishies) {
            this.score += fish;
        }
        System.out.println("After " + this.days + " days there are " + this.score + " fishies");
    }

    public void getFishies() {
        System.out.println(Arrays.toString(this.fishies));
    }
    
    public static void main (String[] args) {
        // Test part 1
        new Day6("testinput.txt", 18);

        // Part 1
        new Day6("input.txt", 80);

        // Test part 2
        new Day6("testinput.txt", 256);

        // Part 2
        new Day6("input.txt", 256);

    }
}
