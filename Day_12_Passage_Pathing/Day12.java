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
    HashMap<String, Cave> caveList;
    ArrayList<String[]> report;
    int pathsNumb;

    public Day12 (String fileName) {
        importFile(fileName);
        setNeighbours();
        findPaths();
    }
    
    public void importFile(String fileName) {
        this.caveList = new HashMap<>();
        this.report = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(new File(fileName));
            while(myReader.hasNextLine()) {
                String[] reportLine = myReader.nextLine().split("\\-");
                for (String cave : reportLine) {
                    if (!caveList.containsKey(cave)) {
                        caveList.put(cave, new Cave(cave));
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
        return caveList.containsKey(name);    
    }

    public void setNeighbours() {
        for (String[] cavePair : report) {
            Cave cave1 = getCave(cavePair[0]);
            Cave cave2 = getCave(cavePair[1]);
            cave1.setNeighbour(cave2);
            cave2.setNeighbour(cave1);
        }
        System.out.println("Neighbours");
        for (Cave cave : caveList.values()) {
            for (Cave neighbour : cave.getNeighbours()) {
                System.out.println(cave.getName() + " has neighbour " + neighbour.getName());
            }
        }
    }

    public void findPaths() {
        ArrayList<Cave> path = new ArrayList<>();
        path.add(getCave("start"));
        pathsNumb = 0;
        setPath(path);
        System.out.println("\nNumber of paths: " + pathsNumb);
    }

    public void setPath(ArrayList<Cave> path) {
        Cave currentCave = path.get(path.size()-1);
        System.out.println("Current cave: " + currentCave.getName());
        currentCave.printNeighbours();
        if (currentCave.getName().equals("end")) {
            pathsNumb++;
            System.out.println();
            printPath(path);
        } else {
            for (Cave neighbour : currentCave.getNeighbours()) {
                if (neighbour.thisCaveIsSmall() && path.contains(neighbour)) {
                    continue;
                }
                path.add(neighbour);
                ArrayList<Cave> pathClone = new ArrayList<>();
                for (Cave c : path) pathClone.add(c);
                setPath(pathClone);
            }
        }
    }

    public void printPath(ArrayList<Cave> path) {
        for (Cave cave : path) {
            System.out.print(cave.getName() + " ");
        }
    }

    public Cave getCave(String name) {
        for (Cave cave : caveList.values()) {
            if (cave.getName().equals(name)) {
                return cave;
            }
        }
        return null;
    }

    public void getCaveList() {
        for (Cave cave : caveList.values()) {
            System.out.println(cave.getName());
        }
    }


    public static void main (String[] args) {
        // Test part 1
        Day12 caves1 = new Day12("testinput.txt");
        //caves1.getCaveList();
        //caves1.caveList.get(1).printNeighbours();

    }
}
