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

public class Day5 {
    int part;
    ArrayList<ArrayList<int[]>> report = new ArrayList<>();
    int[][] grid;
    int gridSizeX = 0;
    int gridSizeY = 0;
    int score = 0;

    public Day5 (String fileName, int part) {
        this.part = part;
        importFile(fileName);
        setGrid();
        fillGrid();
        calcScore();
    }

    public void importFile(String fileName) {
        try {
            Scanner myReader = new Scanner(new File(fileName));
            while(myReader.hasNextLine()) {
                String dataLine = myReader.nextLine();
                String[] dataLineSplit = dataLine.split(" -> ");
                ArrayList<int[]> vents = new ArrayList<>();
                for (String vent : dataLineSplit) {
                    String[] coordinatesStr = vent.split(",");
                    int[] coordinates = {Integer.parseInt(coordinatesStr[0]), Integer.parseInt(coordinatesStr[1])};
                    vents.add(coordinates);
                }
                this.report.add(vents);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    public void setGrid() {
        setGridSize();
        initializeGrid();
    }

    public void setGridSize() {
        for (ArrayList<int[]> vents : this.report) {
            for (int[] vent : vents) {
                if (vent[0] > this.gridSizeX) {
                    this.gridSizeX = vent[0];
                }
                if (vent[1] > this.gridSizeY) {
                    this.gridSizeY = vent[1];
                }
            }
        }
        this.gridSizeX += 1;
        this.gridSizeY += 1;
        System.out.println("Gridsize: " + this.gridSizeX + "," + gridSizeY);

    }

    public void initializeGrid() {
        grid = new int[gridSizeY][gridSizeX];
        for (int[] yLine : grid) {
            for (int xCoord : yLine) {
                xCoord = 0;
            }
        }
    }

    public void fillGrid() {
        for (ArrayList<int[]> vents : this.report) {
            int vent1Y = vents.get(0)[1];
            int vent2Y = vents.get(1)[1];
            int vent1X = vents.get(0)[0];
            int vent2X = vents.get(1)[0];
            if (vent1Y == vent2Y) {
                // y coordinates are the same so it is a horizontal line
                fillHorizontals(vents);
            } else if (vent1X == vent2X) {
                // x coordinates are the same so it is a vertical line
                fillVerticals(vents);

            } else if (this.part == 2) {
                fillDiagnoals(vents);
            }
        }

    }

    public void fillHorizontals(List<int[]> vents) {
        int yCoord = vents.get(0)[1];
        int xCoordLength = Math.abs(vents.get(0)[0] - vents.get(1)[0]) + 1;
        int[][] xCoords = new int[xCoordLength][2];
        int firstXCoord = vents.get(0)[0] < vents.get(1)[0] ? vents.get(0)[0] : vents.get(1)[0];
        xCoords[0][0] = firstXCoord;
        xCoords[0][1] = yCoord;
        for (int i=1; i<xCoordLength; i++) {
            xCoords[i][0] = xCoords[i-1][0] + 1;
            xCoords[i][1] = yCoord;
        }
        /* System.out.println("Horizontal vents line: ");
        for (int[] vent : xCoords) {
             System.out.println(Arrays.toString(vent));
        } */
        fillVents(xCoords);
        
    }

    public void fillVerticals(List<int[]> vents) {
        int xCoord = vents.get(0)[0];
        int yCoordLength = Math.abs(vents.get(0)[1] - vents.get(1)[1]) + 1;
        int[][] yCoords = new int[yCoordLength][2];
        int firstYCoord = vents.get(0)[1] < vents.get(1)[1] ? vents.get(0)[1] : vents.get(1)[1];
        yCoords[0][1] = firstYCoord;
        yCoords[0][0] = xCoord;
        for (int i=1; i<yCoordLength; i++) {
            yCoords[i][1] = yCoords[i-1][1] + 1;
            yCoords[i][0] = xCoord;
        }
        /* System.out.println("Vertical vents line: ");
        for (int[] vent : yCoords) {
            System.out.println(Arrays.toString(vent));
        } */
        fillVents(yCoords);
    }

    public void fillDiagnoals(List<int[]> vents) {
        
    }

    public void fillVents(int[][] ventsLine) {
        for (int[] vent : ventsLine) {
            int xCoord = vent[0];
            int yCoord = vent[1];
            //System.out.println("X coord: " + xCoord + " Y coord: " + yCoord);
            this.grid[yCoord][xCoord] = this.grid[yCoord][xCoord] + 1;
        }
        //getGrid();
        
    }

    public void calcScore() {
        for (int [] yLine : this.grid) {
            for (int value : yLine) {
                if (value > 1) {
                    this.score++;
                }
            }
        }
        System.out.println(this.score);
    }
    
    public void getReport() {
        for (ArrayList<int[]> vents : this.report) {
            for (int[] vent : vents) {
                System.out.print(Arrays.toString(vent));
            }
            System.out.println("");
        }
    }

    public void getGrid() {
        for (int[] line : this.grid) {
            System.out.println(Arrays.toString(line));
        }
    }

    public static void main (String[] args) {
        // Test part 1
        Day5 test1 = new Day5("testinput.txt", 1);
        test1.getReport();

        // Part 1
        new Day5("input.txt", 1);
    }
}