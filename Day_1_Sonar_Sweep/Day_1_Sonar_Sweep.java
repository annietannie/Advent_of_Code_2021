package Day_1_Sonar_Sweep;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day_1_Sonar_Sweep {

    public static ArrayList<Integer> importFile(String fileName) {
        ArrayList<Integer> report = new ArrayList<>();
        try {
            File myFile = new File(fileName);
            Scanner myReader = new Scanner(myFile);
            while(myReader.hasNextLine()) {
                int data = Integer.parseInt(myReader.nextLine());
                report.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        return report;
    } 

    public static int howMuchIncreased(ArrayList<Integer> report) {
        int increased = 0;
        int number;
        int secondNumber;
        for (int i=1; i<report.size(); i++) {
            number = report.get(i);
            secondNumber = report.get(i-1);
            if (number > secondNumber) {
                increased++;
            }
        }
        return increased;
    }

    public static ArrayList<Integer> createSlidingWindow(ArrayList<Integer> report) {
        ArrayList<Integer> slidingWindow = new ArrayList<>();
        int firstNumber;
        int secondNumber;
        int thirdNumber;
        int sliding;

        for (int i=2; i<report.size(); i++) {
            firstNumber = report.get(i);
            secondNumber = report.get(i-1);
            thirdNumber = report.get(i-2);
            sliding = firstNumber + secondNumber + thirdNumber;
            slidingWindow.add(sliding);
        }
        return slidingWindow;
    }
    

    public static void main(String[] args) {
        // Test
        ArrayList<Integer> test_report = importFile("test_data.txt");
        int increasedTestPartOne = howMuchIncreased(test_report);
        ArrayList<Integer> slidingWindow_test = createSlidingWindow(test_report);
        int increasedTestPartTwo = howMuchIncreased(slidingWindow_test);
        String test = (increasedTestPartOne == 7 && increasedTestPartTwo == 5) ? "Test succeeded" : "Test failed";
        System.out.println(test);

        // Part one
        ArrayList<Integer> report = importFile("input.txt");
        int increasedPartOne = howMuchIncreased(report);
        System.out.println("The answer to part 1 is : " + increasedPartOne);

        // Part two
        ArrayList<Integer> slidingWindow = createSlidingWindow(report);
        int increasedPartTwo = howMuchIncreased(slidingWindow);
        System.out.println("The answer to part 2 is : " + increasedPartTwo);
    }
}