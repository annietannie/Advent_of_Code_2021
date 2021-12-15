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

    public void getNeighbours() {
        for (Cave neighbour : neighbours) {
            System.out.println(neighbour.caveName);
        }
    }
}
