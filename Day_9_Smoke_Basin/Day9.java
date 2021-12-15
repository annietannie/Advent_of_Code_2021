import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.lang.reflect.Array;
import java.util.stream.Collectors;
import java.util.List;

public class Day9 {
    ArrayList<List<Integer>> report;
    int pointsPart1;
    ArrayList<Integer> bassins;
    ArrayList<ArrayList<Boolean>> shadowReport;
    ArrayList<ArrayList<Integer>> lowestPoints;

    public Day9(String fileName) {
        pointsPart1 = 0;
        bassins = new ArrayList<>();
        importFile(fileName);
        //getReport();
        findLowestPoints();
        System.out.println("Points for part 1: " + this.pointsPart1);

        findBassins();
        getPoints2();
    }

    public void importFile(String fileName) {
        this.report = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(new File(fileName));
            while(myReader.hasNextLine()) {
                List<Integer> reportLine = Arrays
                    .stream(myReader.nextLine().split(""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
                this.report.add(reportLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    } 

    public void findBassins() {
        bassins = new ArrayList<>();
        /* for (int i=1; i<2; i++){
            ArrayList<Integer> lowestPoint = lowestPoints.get(i); */
        for (ArrayList<Integer> lowestPoint : lowestPoints) {
            ArrayList<ArrayList<Integer>> bassinStart = new ArrayList<>();
            ArrayList<ArrayList<Integer>> checkedForNeighbours = new ArrayList<>();
            ArrayList<ArrayList<Integer>> toCheckForNeighbours = new ArrayList<>();
            toCheckForNeighbours.add(lowestPoint);
            //System.out.println("Lowest point: " +lowestPoint.toString());
            ArrayList<ArrayList<Integer>> bassin = createBassin(bassinStart, checkedForNeighbours, toCheckForNeighbours);
            bassins.add(bassin.size());
            //System.out.println("BASSIN SIZE: " + bassin.size());
            /* // Print bassin
            for (ArrayList<Integer> point : bassin) {
                System.out.println(point.toString());
            } */
            //System.out.println("");
        }
        //System.out.println("Bassin sizes: " + bassins.toString());
    }

    public ArrayList<ArrayList<Integer>> createBassin(ArrayList<ArrayList<Integer>> bassin, ArrayList<ArrayList<Integer>> checkedForNeighbours, ArrayList<ArrayList<Integer>> toCheckForNeighbours) {
        //System.out.println("Creating a bassin");
        ArrayList<Integer> point = toCheckForNeighbours.remove(0);
        int x = point.get(0);
        int y = point.get(1);
        checkedForNeighbours.add(point);
        if (report.get(y).get(x) != 9) {
            bassin.add(point);
            //System.out.println("Added: " + point.toString());
            for (ArrayList<Integer> neighbour : getMyNeighboursCoord(x, y).values()) {
                if (!toCheckForNeighbours.contains(neighbour) && !checkedForNeighbours.contains(neighbour)) {
                    toCheckForNeighbours.add(neighbour);
                }
            }
            if (toCheckForNeighbours.size() > 0) {
                /* // Print toCheckForNeighbours
                System.out.println("TO CHECK FOR NEIGHBOURS");
                for (ArrayList<Integer> neighbour : toCheckForNeighbours) {
                    System.out.println(neighbour.toString());
                }
                // Print checkedNeighbours
                System.out.println("CHECKED FOR NEIGHBOURS");
                for (ArrayList<Integer> neighbour : checkedForNeighbours) {
                    System.out.println(neighbour.toString());
                } */
                // Print next point to check
                //System.out.println("Next point: " + toCheckForNeighbours.get(0).toString());
                
            }
        }
        if (toCheckForNeighbours.size() > 0) {
            createBassin(bassin, checkedForNeighbours, toCheckForNeighbours);
        }
        return bassin;
    }

    public boolean wasIAlreadyInABassin(int x, int y) {
        return shadowReport.get(y).get(x);
    }

    public ArrayList<ArrayList<Boolean>> createShadowReport() {
        shadowReport = new ArrayList<>();
        for (int y=0; y<report.size(); y++) {
            ArrayList<Boolean> line = new ArrayList<>();
            for (int x=0; x<report.get(0).size(); x++) {
                line.add(false);
            }
            shadowReport.add(line);
        }
        return shadowReport;
    }

    public void findLowestPoints() {
        this.lowestPoints = new ArrayList<>();
        for (int y=0; y<report.size(); y++ ) {
            List<Integer> row = report.get(y);
            for (int x=0; x<row.size(); x++ ) {
                int point = row.get(x);
                LinkedHashMap<String, Integer> neighbours = getMyNeighboursValue(x, y);
                if(isThisALocalDale(neighbours, point)) {
                    this.pointsPart1 += 1 + point;
                    ArrayList<Integer> coords = new ArrayList<>();
                    coords.add(x);
                    coords.add(y);
                    this.lowestPoints.add(coords);
                    //System.out.println("Lowest point: " + point + " on " + x + "," + y);
                }
            }
        }
    }

    public boolean isThisALocalDale(LinkedHashMap<String, Integer> neighbours, int point) {
        boolean amIALocalDale = true;
        for (int neighbour : neighbours.values()) {
            if (neighbour <= point) {
                amIALocalDale = false;
            }
        }
        return amIALocalDale;
    }

    public LinkedHashMap<String, Integer> getMyNeighboursValue(int x, int y) {
        LinkedHashMap<String, Integer> neighbours = new LinkedHashMap<>();
        if (x > 0) {
            // Left neighbour
            neighbours.put((x-1) + "," + y, report.get(y).get(x-1));
        }
        if (y > 0) {
            // Upper neighbour
            neighbours.put(x + "," + (y-1), report.get(y-1).get(x));
        }
        if (x < report.get(0).size()-1) {
            // Right neighbour
            neighbours.put((x+1) + "," + y, report.get(y).get(x+1));
        }
        if (y < report.size()-1) {
            // Under neighbour
            neighbours.put(x + "," + (y+1), report.get(y+1).get(x));
        }
        return neighbours;
    }

    public LinkedHashMap<String, ArrayList<Integer>> getMyNeighboursCoord(int x, int y) {
        LinkedHashMap<String, ArrayList<Integer>> neighbours = new LinkedHashMap<>();
        if (x > 0) {
            // Left neighbour
            ArrayList<Integer> coord = new ArrayList<Integer>();
            coord.add(x-1);
            coord.add(y);
            neighbours.put((x-1) + "," + y, coord );
        }
        if (y > 0) {
            // Upper neighbour
            ArrayList<Integer> coord = new ArrayList<Integer>();
            coord.add(x);
            coord.add(y-1);
            neighbours.put(x + "," + (y-1), coord);
        }
        if (x < report.get(0).size()-1) {
            // Right neighbour
            ArrayList<Integer> coord = new ArrayList<Integer>();
            coord.add(x+1);
            coord.add(y);
            neighbours.put((x+1) + "," + y, coord);
        }
        if (y < report.size()-1) {
            // Under neighbour
            ArrayList<Integer> coord = new ArrayList<Integer>();
            coord.add(x);
            coord.add(y+1);
            neighbours.put(x + "," + (y+1), coord);
        }
        //System.out.println(Arrays.asList(neighbours));
        return neighbours;
    }

    public void getPoints2() {
        Collections.sort(bassins);
        Collections.reverse(bassins);
        int score = bassins.get(0) * bassins.get(1) * bassins.get(2);
        //System.out.println("Bassin sizes: " + bassins.toString());
        System.out.println("Points for part 2: " + score);
    }

    public void getBassin(HashMap<String, int[]> bassin) {
        for (int[] point : bassin.values()) {
            System.out.println(Arrays.toString(point));
        }
    }

    public void getReport() {
        for (List<Integer> line : report) {
            System.out.println(line.toString());
        }   
    }

    public static void main (String[] args) {
        // Test part 1
        new Day9("testinput.txt");

        // Part 1
        new Day9("input.txt");
    }
}
