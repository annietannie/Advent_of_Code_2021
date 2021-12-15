import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Array;
import java.lang.Long;

public class Day14 {

    ArrayList<String> report;
    ArrayList<String> polymer;
    HashMap<String, String> instructions;
    int loops;
    HashMap<String, Long> dimers;
   
    public Day14 (String fileName, int loops) {
        this.loops = loops;
        importFile(fileName);
        setUp();
        makeDimers();
        makePolymer();
        getPoints();
    }

    public void importFile(String fileName) {
        report = new ArrayList<>();
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

    public void setUp() {
        // Setup the polymer
        polymer = new ArrayList<>();
        for (String c : report.get(0).split("|")) {
            polymer.add(c);
        }

        // Setup the instructions
        instructions = new HashMap<>();
        for (int i=2; i<report.size(); i++) {
            String[] instStrSplit = report.get(i).split(" -> ");
            instructions.put(instStrSplit[0], instStrSplit[1]);
        }
    }

    public void makeDimers() {
        this.dimers = new HashMap<>();
        for (int i=1; i<polymer.size(); i++) {
            String dimer = String.valueOf(polymer.get(i-1)) + String.valueOf(polymer.get(i));
            if (this.dimers.containsKey(dimer)) {
                this.dimers.put(dimer, this.dimers.get(dimer)+1L);
            } else {
                this.dimers.put(dimer, 1L);
            }
        }
    }

    public HashMap<String, Long> makeTempDimers() {
        HashMap<String, Long> tempDimers = new HashMap<>();
        for (String dimer : instructions.keySet()) {
            tempDimers.put(dimer, Long.valueOf(0));
        }
        return tempDimers;
    }

    public void polymeriseDimers() {
        HashMap<String, Long> tempDimers = makeTempDimers();

        for (String dimer : dimers.keySet()) {
            String monomer = instructions.get(dimer);
            String[] dimerSplit = dimer.split("|");
            String dimer1 = dimerSplit[0] + monomer;
            String dimer2 = monomer + dimerSplit[1];
            Long value = Long.valueOf(dimers.get(dimer));
            tempDimers.put(dimer1, tempDimers.get(dimer1)+value);
            tempDimers.put(dimer2, tempDimers.get(dimer2)+value);
        }
        dimers = tempDimers;
    }

    public void makePolymer() {
        for (int i=0; i<this.loops; i++) {
            polymeriseDimers();
        }
    }

    public HashMap<String, Long> setMonomerValues() {
        HashMap<String, Long> monomerValues = new HashMap<>();
        for (String monomer : instructions.values()) {
            if (!monomerValues.containsKey(monomer)) {
                monomerValues.put(monomer, 0L);
            }
        }
        return monomerValues;
    }

    public void getPoints() {
        HashMap<String, Long> monomerValues = setMonomerValues();
        for (String dimer : dimers.keySet()) {
            String[] monomers = dimer.split("|");
            for (String monomer : monomers) {
                monomerValues.put(monomer, monomerValues.get(monomer)+dimers.get(dimer));
            }
        }

        Long max = 0L;
        Long min = 0L;

        for (String monomer : monomerValues.keySet()) {
            Long monomerValue = monomerValues.get(monomer) / 2L;
            if (polymer.get(0) == monomer || polymer.get(polymer.size()-1) == monomer) {
                monomerValue = monomerValue+1L;
            }
            if (min == 0L || monomerValue < min) {
                min = monomerValue;
            } 
            if (monomerValue > max) {
                max = monomerValue;
            }
            monomerValues.put(monomer, monomerValue);
        }

        System.out.println(monomerValues);
        
        Long points = max - min;
        System.out.println("Points: " + points);
    }

    public void getPolymer() {
        System.out.println("Polymer: " + polymer.toString());
    }

    public void getInstructions() {
        System.out.println("Instructions:" + instructions);
    }

    public static void main (String[] args) {
        // Test part 1
        new Day14("testinput.txt", 10);

        // Part 1
        new Day14("input.txt", 10);

        // Test part 2
        new Day14("testinput.txt", 40);

        // Part 2
        new Day14("input.txt", 40);
    }
}
