import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Submarine {
    int horizontalPosition;
    int depth;
    int aim;

    public Submarine() {
        this.horizontalPosition = 0;
        this.depth = 0;
        this.aim = 0;
    }

    public void importFile(String fileName) {
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
                switch (command) {
                    case "forward":
                        this.forward(index);
                        break;
                    case "down":
                        this.down(index);
                        break;
                    case "up":
                        this.up(index);
                        break;
                }
                //System.out.println(this.horizontalPosition + ", " + this.depth);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    public void forward(int x) {
        this.horizontalPosition += x;
    }

    public void down(int x) {
        this.depth += x;
    }

    public void up(int x) {
        this.depth -= x;
    } 

    public int multiply() {
        return this.horizontalPosition * this.depth;
    }

    public ArrayList<Integer> getCoordinates() {
        ArrayList<Integer> coordinates = new ArrayList<Integer>();
        coordinates.add(this.horizontalPosition);
        coordinates.add(this.depth);
        return coordinates;
    }
}
