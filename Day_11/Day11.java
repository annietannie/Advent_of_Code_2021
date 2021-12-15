import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.lang.reflect.Array;
import java.util.stream.Collectors;
import java.util.List;

public class Day11 {

    ArrayList<List<Integer>> dumbos;
    int steps;
    int flashes;
    int stepCounter;

    public Day11(String fileName) {
        this.steps = steps;
        importFile(fileName);
        part2();
    }

    public Day11(String fileName, int steps) {
        this.steps = steps;
        importFile(fileName);
        part1();
        getScore();
    }

    public void importFile(String fileName) {
        dumbos = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(new File(fileName));
            while(myReader.hasNextLine()) {
                List<Integer> reportLine = Arrays
                    .stream(myReader.nextLine().split(""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
                this.dumbos.add(reportLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    public void part1() { 
        flashes = 0;
        for (int i=0; i<steps; i++) {
            goFlashing();
        }
    }

    public void part2() {
        stepCounter = 0;
        do {
            flashes = 0;
            goFlashing();
            stepCounter++;
        } while (flashes < 100);

        System.out.println("Number of steps before full flash: " + stepCounter);
    }

    public void goFlashing() {
            LinkedList<int[]> flashers = new LinkedList<>();
            for (int y=0; y<dumbos.size(); y++) {
                for (int x=0; x<dumbos.get(0).size(); x++) {
                    int dumbo = dumbos.get(y).get(x);
                    if (dumbo != 9) {
                        dumbos.get(y).set(x, dumbo+1);
                    } else {
                        int[] flashy = {x,y};
                        flashers.add(flashy);
                        dumbos.get(y).set(x, 0);
                    }
                }
            }
            flashingDumbos(flashers);
            /* getDumbos();
            System.out.println(); */
    }

    public void flashingDumbos(LinkedList<int[]> flashers) {
        flashes += flashers.size();
        /* System.out.println("Number of new flashers: " + flashers.size());
        for (int[] flash : flashers) {
            System.out.println(Arrays.toString(flash));
        }
        getDumbos();
        System.out.println(); */
        LinkedList<int[]> flashers2 = new LinkedList<>();
        for (int[] flashCoord : flashers) {
            dumbos.get(flashCoord[1]).set(flashCoord[0], 0);
            HashMap<String, Integer> neighbours = getNeighbours(flashCoord[0], flashCoord[1]);
            for (String coord : neighbours.keySet()) {
                int neighbourValue = neighbours.get(coord);
                String[] coordString = coord.split(",");
                int[] coordFlash = new int[2];
                coordFlash[0] = Integer.parseInt(coordString[0]);
                coordFlash[1] = Integer.parseInt(coordString[1]);
                switch (neighbourValue) {
                    case 0:
                        break;
                    case 9:
                        flashers2.add(coordFlash);
                        dumbos.get(coordFlash[1]).set(coordFlash[0], 0);
                        break;
                    default:
                        dumbos.get(coordFlash[1]).set(coordFlash[0], neighbourValue+1);
                        break;
                }
            }
        }
        if (!flashers2.isEmpty()) {
            flashingDumbos(flashers2);
        }
    }

    public HashMap<String, Integer> getNeighbours(int x, int y) {
        HashMap<String, Integer> neighbours = new HashMap<>();
        if (x > 0) {
            // Left neighbour
            neighbours.put((x-1) + "," + y, dumbos.get(y).get(x-1));
            if (y > 0) {
                // Upper left diagonal
                neighbours.put((x-1) + "," + (y-1), dumbos.get(y-1).get(x-1));
            }
        }
        if (y > 0) {
            // Upper neighbour
            neighbours.put(x + "," + (y-1), dumbos.get(y-1).get(x));
            if (x < dumbos.get(0).size()-1) {
                // Upper right diagonal
                neighbours.put((x+1) + "," + (y-1), dumbos.get(y-1).get(x+1));
            }
        }
        if (x < dumbos.get(0).size()-1) {
            // Right neighbour
            neighbours.put((x+1) + "," + y, dumbos.get(y).get(x+1));
            if (y < dumbos.size()-1) {
                // Lower right diagonal
                neighbours.put((x+1) + "," + (y+1), dumbos.get(y+1).get(x+1));
            }
        }
        if (y < dumbos.size()-1) {
            // Under neighbour
            neighbours.put(x + "," + (y+1), dumbos.get(y+1).get(x));
            if (x > 0) {
                // Lower left diagonal
                neighbours.put((x-1) + "," + (y+1), dumbos.get(y+1).get(x-1));
            }
        }
        return neighbours;
    }

    public void getScore() {
        System.out.println("After " + steps + " steps there have been " + flashes + " flashes");
    }

    public void getDumbos() {
        for (List<Integer> dumboLine : dumbos) {
            System.out.println(dumboLine.toString());
        }
    }

    public static void main (String[] args) {
        // Mini test
        //new Day11("minitest.txt", 1);
        
        // Test part 1
        new Day11("testinput.txt", 100);

        // Part 1
        new Day11("input.txt", 100);

        // Test part 2
        new Day11("testinput.txt");

        // Part 2
        new Day11("input.txt");
    }
}
