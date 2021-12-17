import java.util.ArrayList;
import java.util.Arrays;

public class Cave {
    String caveName;
    ArrayList<Cave> neighbours;
    boolean bigCave;
    
    public Cave(String name) {
        this.caveName = name;
        this.bigCave = Character.isUpperCase(name.charAt(0));
        this.neighbours = new ArrayList<>();
    }

    public void setNeighbour(Cave neighbour) {
        neighbours.add(neighbour);
    }

    public void printNeighbours() {
        for (Cave neighbour : neighbours) {
            System.out.println(neighbour.caveName);
        }
    }

    public ArrayList<Cave> getNeighbours() {
        return neighbours;
    }

    public String getName() {
        return caveName;
    }

    public Boolean thisCaveIsSmall() {
        return !bigCave;
    }
}
