package Day_2_Dive;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day2 {
    int horizontalPosition;
    int depth;

    public Day2() {
        this.horizontalPosition = 0;
        this.depth = 0;
    }

    public ArrayList<Integer> importFile(String fileName) {
        ArrayList<Integer> coordinates = new ArrayList<Integer>();
        try {
            File myFile = new File(fileName);
            Scanner myReader = new Scanner(myFile);
            String[] data;
            String command;
            int index;
            while(myReader.hasNextLine()) {
                data = myReader.nextLine().split(" ");
                command = data[0];
                index = Integer.parseInt(data[1]);
                //System.out.println(command + ", " + index);
                if (command.equals("forward")) {
                    this.forward(index);
                } else if (command.equals("down")) {
                    this.down(index);
                } else if (command.equals("up")) {
                    this.up(index);
                }
                //System.out.println(this.horizontalPosition + ", " + this.depth);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        coordinates.add(this.horizontalPosition);
        coordinates.add(this.depth);

        return coordinates;
    }

    public void forward(int x) {
        this.horizontalPosition = this.horizontalPosition + x;
    }

    public void down(int x) {
        this.depth = this.depth + x;
    }

    public void up(int x) {
        this.depth = this.depth - x;
    } 

    public int multiply(ArrayList<Integer> coordinates) {
        int multiplicate = coordinates.get(0) * coordinates.get(1);
        return multiplicate;
    }

    public static void main(String args[]) {
        Day2 testShip = new Day2();
        ArrayList<Integer> testCoordinates = testShip.importFile("testinput.txt");
        int testAnswer = testShip.multiply(testCoordinates);
        System.out.println(testCoordinates);
        System.out.println(testAnswer);

        Day2 ship = new Day2();
        ArrayList<Integer> coordinates = ship.importFile("input.txt");
        int answer = ship.multiply(coordinates);
        System.out.println(coordinates);
        System.out.println(answer);
    }
}
