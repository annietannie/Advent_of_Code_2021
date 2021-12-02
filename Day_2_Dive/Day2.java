import java.util.ArrayList;

class Day2 {
    public static void main(String args[]) {
        // Part 1
        Submarine testShip = new Submarine();
        testShip.importFile("testinput.txt");
        ArrayList<Integer> testCoordinates = testShip.getCoordinates();
        int testAnswer = testShip.multiply();
        System.out.println("Coordinates test part 1: " + testCoordinates);
        System.out.println("Answer test part 1: " + testAnswer);

        Submarine ship = new Submarine();
        ship.importFile("input.txt");
        ArrayList<Integer> coordinates = ship.getCoordinates();
        int answer = ship.multiply();
        System.out.println("Coordinates part 1: " + coordinates);
        System.out.println("Answer part 1: " + answer);

        // Part 2
        Submarine2 testShip2 = new Submarine2();
        testShip2.importFile("testinput.txt");
        ArrayList<Integer> testCoordinates2 = testShip2.getCoordinates();
        int testAnswer2 = testShip2.multiply();
        System.out.println("Coordinates test part 2: " + testCoordinates2);
        System.out.println("Answer test part 2: " + testAnswer2);

        Submarine2 Submarine = new Submarine2();
        Submarine.importFile("input.txt");
        ArrayList<Integer> coordinatesSub = Submarine.getCoordinates();
        int answerSub = Submarine.multiply();
        System.out.println("Coordinates part 2: " + coordinatesSub);
        System.out.println("Answer part 2: " + answerSub);
    }
}
