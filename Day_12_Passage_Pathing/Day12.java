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

public class Day12 {
    ArrayList<Cave> caveList;
    ArrayList<String[]> report;

    public Day12 (String fileName) {
        importFile(fileName);
        setNeighbours();
        findPaths();
    }
    
    public void importFile(String fileName) {
        this.caveList = new ArrayList<>();
        this.report = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(new File(fileName));
            while(myReader.hasNextLine()) {
                String[] reportLine = myReader.nextLine().split("\\-");
                for (String cave : reportLine) {
                    if (!doesThisCaveExit(cave)) {
                        caveList.add(new Cave(cave));
                    }
                }
                this.report.add(reportLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    public boolean doesThisCaveExit(String name) {
        for (Cave cave : caveList) {
            if (cave.caveName.equals(name)) {
                return true;
            }
        } 
        return false;    
    }

    public void setNeighbours() {
        for (String[] cavePair : report) {
            Cave cave1 = getCave(cavePair[0]);
            Cave cave2 = getCave(cavePair[1]);
            cave1.setNeighbour(cave2);
            cave2.setNeighbour(cave1);
        }
    }

    public void findPaths() {
        ArrayList<ArrayList<Cave>> paths = new ArrayList<>();
        ArrayList<Cave> path1 = new ArrayList<>();
        path1.add(getCave("Start"));
        paths.add(path1);
        setPaths(paths);
    }

    public void setPaths(Cave cave) {

        do {

        } while (!cave.caveName.contains("end"));
    }

    public Cave getCave(String name) {
        for (Cave cave : caveList) {
            if (cave.caveName.equals(name)) {
                return cave;
            }
        }
        return null;
    }

    public void getCaveList() {
        for (Cave cave : caveList) {
            System.out.println(cave.caveName);
        }
    }


    public static void main (String[] args) {
        // Test part 1
        Day12 caves1 = new Day12("testinput.txt");
        caves1.getCaveList();
        caves1.caveList.get(1).getNeighbours();

    }
}
