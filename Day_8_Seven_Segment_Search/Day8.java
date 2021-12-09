import java.util.Scanner;
import java.io.File;
import java.util.stream.IntStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.stream.Collectors;
import java.util.List;

public class Day8 {
    int answer;
    ArrayList<String[]> uniqueSignal = new ArrayList<>();
    ArrayList<String[]> digitOutput = new ArrayList<>();
    ArrayList<char[]> numbers = new ArrayList<>();
        // To store the characters of all the numbers in order (0 first)
    char[] numberSegments;
        /*
        Number build:

         0000
        3    5
        3    5
         1111
        4    6
        4    6
         2222

        */

    HashMap<Integer, ArrayList<char[]>> numberSort = new HashMap<>();
        /*
        key
        5   : 2, 3, 5 
        6   : 0, 6, 9
        */

    public Day8(String fileName) {
        importFile(fileName);
        calculate1478();
        calculatePart2();
    }

    public void importFile(String fileName) {
        try {
            Scanner myReader = new Scanner(new File(fileName));
            while(myReader.hasNextLine()) {
                String[] dataLine = myReader.nextLine().split(" \\| ");
                this.uniqueSignal.add(dataLine[0].split(" "));
                this.digitOutput.add(dataLine[1].split(" "));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    public void calculate1478() {
        int total = 0;
        for (String[] dataLine : digitOutput) {
            for (String digit : dataLine) {
                int digitLength = digit.length();
                if (digitLength == 2 || digitLength == 3 ||digitLength == 4 || digitLength == 7) {
                    total++;
                }
            }
        }
        System.out.println("Number of 1, 4, 7 and 8: " + total);
    }

    public void calculatePart2() {
        for (int i=0; i<this.uniqueSignal.size(); i++) {
            detectSegments(this.uniqueSignal.get(i));
            this.answer += deductNumbers(this.digitOutput.get(i));
        }
        System.out.println("The answer for part 2 is: " + this.answer);
    }

    public int deductNumbers(String[] digitOutputLine) {
        String subAnswer = "";
        for (int i=0; i<digitOutputLine.length; i++) {
            subAnswer += whatsMyNumber(digitOutputLine[i]);
        }
        return Integer.parseInt(subAnswer);
    }

    public int whatsMyNumber(String numberString) {
        int digitLength = numberString.length();
        switch(digitLength) {
            case 2:
                return 1;
            case 3:
                return 7;
            case 4:
                return 4;
            case 5:
                return what5DigitNumberAmI(numberString);
            case 6:
                return what6DigitNumberAmI(numberString);
            case 7:
                return 8;
        }
        return 0;
    }

    public int what5DigitNumberAmI(String numString) {
        char[] number = numString.toCharArray();
        if (checkIfTheseBitsContainSegment(number, 4)) {
            return 2;
        }
        if (checkIfTheseBitsContainSegment(number, 5)) {
            return 3;
        }
        return 5;
    }

    public int what6DigitNumberAmI(String numString) {
        char[] number = numString.toCharArray();
        if (checkIfTheseBitsContainSegment(number, 5)) {
            if (checkIfTheseBitsContainSegment(number, 4)) {
                return 0;
            }
            return 9;
        }
        return 6;
    }

    public void detectSegments(String[] uniqueSignalLine) {
        createNumbers();    
        initializeNumbers(uniqueSignalLine);    

        // Find numbers and segments
        findSegment0();
        findNumber3();
        findSegments1and2();
        findSegment3();
        findSegment4();
        findNumber5();
        findSegment5();

        // Printing, only for test purposes
        /* System.out.println("Segment 0 is: " + this.numberSegments[0]);
        System.out.println("Segment 1 is: " + this.numberSegments[1]);
        System.out.println("Segment 2 is: " + this.numberSegments[2]);
        System.out.println("Segment 3 is: " + this.numberSegments[3]);
        System.out.println("Segment 4 is: " + this.numberSegments[4]);
        System.out.println("Segment 5 is: " + this.numberSegments[5]);
        System.out.println("Segment 5 is: " + this.numberSegments[6]); */
    }

    public void findSegment5() {
        for (char bit1 : this.numbers.get(1)) {
            if (isThisBitin(bit1, numbers.get(5))) {
                this.numberSegments[6] = bit1;
            } else {
                this.numberSegments[5] = bit1;
            }
        }
    }

    public void findNumber5() {
        for (char[] number : numberSort.get(5)) {
            if (checkIfTheseBitsContainSegment(number, 3)) {
                this.numbers.set(5, number);
                break;
            }
        }
    }

    public boolean checkIfTheseBitsContainSegment(char[] number, int segmentNr) {
        for (char bit : number) {
            if (bit == this.numberSegments[segmentNr]) {
                return true;
            }
        }
        return false;
    }

    public void findSegment4() {
        // only segment that is nog in 3, 4, 7;
        for (char bit8 : this.numbers.get(8)) {
            if (!isThisBitin(bit8, numbers.get(3)) && !isThisBitin(bit8, numbers.get(4)) && !isThisBitin(bit8, numbers.get(7))) {
                this.numberSegments[4] = bit8;
            }
        }
    }

    public void findSegment3() {
        // only segment 4 has and 1, 3, 7 not
        for (char bit4 : this.numbers.get(4)) {
            if (!isThisBitin(bit4, numbers.get(3)) && !isThisBitin(bit4, numbers.get(7))) {
                this.numberSegments[3] = bit4;
            }
        }
    }

    public void findSegments1and2() {
        int segmentCounter = 0;
        boolean isThisIn4 = false;
        for (char bit3 : this.numbers.get(3)) {
            // Is it in 4?
            if(!isThisBitin(bit3, numbers.get(4)) && !isThisBitin(bit3, numbers.get(7))) {
                this.numberSegments[2] = bit3;
            } else if (!isThisBitin(bit3, numbers.get(7))) {
                this.numberSegments[1] = bit3;
            }
        }
    }

    public void findSegment0() {
        // Comparing 7 with 1 to find segment 0

        for (char bit7 : this.numbers.get(7)) {
            if (!isThisBitin(bit7, numbers.get(1))) {
                this.numberSegments[0] = bit7;
            }
        }
    }

    public void findNumber3() {
        /* Finding number 3 to find bits 1, 2, 3, and 4
        To find number 3: take the 5 segment number which contains all the segments of 7.*/
        for (char[] number : numberSort.get(5)) {
            if (checkIfTheseBitsContainBitsOf(number, 7, 3)) {
                this.numbers.set(3, number);
                break;
            }
        }
    }

    public boolean checkIfTheseBitsContainBitsOf(char[] number, int refNum, int count) {
        int segmentCounter = 0;
        for (char segment : number) {
            if (isThisBitin(segment, numbers.get(refNum))) {
                segmentCounter++;
            }
        }
        return (segmentCounter == count);
    }

    public Boolean isThisBitin (char bit, char[] number) {
        for (char segment : number) {
            if (segment == bit) {
                return true;
            }
        }
        return false;
    }

    public void createNumbers() {
        this.numbers = new ArrayList<>(); 
        int number = 0;
        char[] emptyChar = {'z'};
        while (number < 10) {
            this.numbers.add(emptyChar);
            number++;
        }

        this.numberSegments = new char[7];
    } 

    public void initializeNumbers(String[] uniqueSignalLine) {
        ArrayList<char[]> fives = new ArrayList<>();
        ArrayList<char[]> sixes = new ArrayList<>();
        for (String digit : uniqueSignalLine) {
            int digitLength = digit.length();
            char[] digitChars = digit.toCharArray();
            switch(digitLength) {
                case 2:
                    // It's a 1
                    this.numbers.set(1, digitChars);
                    break;
                case 3:
                    // It's a 7
                    this.numbers.set(7, digitChars);
                    break;
                case 4:
                    // It's a 4
                    this.numbers.set(4, digitChars);
                    break;
                case 5:
                    fives.add(digitChars);
                    break;
                case 6:
                    sixes.add(digitChars);
                    break;
                case 7:
                    // It's a 8
                    this.numbers.set(8, digitChars);
                    break;
            }
        }
        this.numberSort.put(5, fives);
        this.numberSort.put(6, sixes);
    }
    public void getReport() {
        for (int i=0; i<uniqueSignal.size(); i++) {
            System.out.println(Arrays.toString(uniqueSignal.get(i)) + " | " + Arrays.toString(digitOutput.get(i)));
        }
    }
    
    public static void main (String[] args) {
        // Test part 1
        Day8 test1 = new Day8("testinput.txt");

        // Part 1
        new Day8("input.txt");
    }
}
