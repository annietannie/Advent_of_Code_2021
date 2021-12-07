import java.util.Scanner;
import java.util.stream.IntStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.stream.Collectors;
import java.util.List;

public class Day3_2 {
    public static ArrayList<String> importFile(String fileName) {
        ArrayList<String> report = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(new File(fileName));
            while(myReader.hasNextLine()) {
                report.add(myReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        return report;
    }

    public static int[] countOnes(ArrayList<String> report) {
        int dataLine;
        int[] gammaList = new int[report.get(0).length()];
        for (int i=0; i<report.size(); i++) {
            for (int j=0; j<gammaList.length; j++) {
                dataLine = Character.getNumericValue(report.get(i).charAt(j));
                if (dataLine == 1) {
                    gammaList[j] = gammaList[j]+1;
                }
            }
        }
        return gammaList;
    }

    public static String[] createGammaAndEpsiolon(ArrayList<String> report, int[] gammaList) {
        int numberOfNumbers = report.size();
        StringBuilder gammaBinStr = new StringBuilder();
        StringBuilder epsilonBinStr = new StringBuilder();
        for (int i=0; i<gammaList.length; i++) {
            if ((gammaList[i]*2) >= numberOfNumbers) {
                gammaBinStr.append(1);
                epsilonBinStr.append(0);
            } else {
                gammaBinStr.append(0);
                epsilonBinStr.append(1);
            }
        }
        String[] gammaEpsilon = {gammaBinStr.toString(), epsilonBinStr.toString()};
        return gammaEpsilon;
    }

    public static int getDecimal(String binNumber) {
        return Integer.parseInt(binNumber, 2);
    }

    public static int multiplyGammaAndEpsilon(int[] gammaEpsilon) {
        return gammaEpsilon[0] * gammaEpsilon[1];
    }

    public static int[] createOxygenAndCO2(ArrayList<String> oldReport, String[] oneCounter) {
        String dataPoint;
        ArrayList<String> selectionOxygen = new ArrayList<String>();
        for (int i=0; i<oneCounter.length; i++) {
            for (int j=0; j<oldReport.size(); j++) {
                dataPoint = String.valueOf(oldReport.get(j).charAt(i));
                if (oneCounter[i].equals(dataPoint)) {
                    selectionOxygen.add(oldReport.get(j));
                }
            }
            if (selectionOxygen.size() == 1) {
                break;
            }
            System.out.println("Oxygen list: " + selectionOxygen.toString());
            oldReport = selectionOxygen;
            selectionOxygen.clear();
        }

        ArrayList<String> selectionCO2 = new ArrayList<String>();
        for (int i=0; i<oneCounter.length; i++) {
            for (int j=0; j<oldReport.size(); j++) {
                dataPoint = String.valueOf(oldReport.get(j).charAt(i));
                if (oneCounter[i].equals(dataPoint)) {
                    selectionCO2.add(oldReport.get(j));
                }
            }
            if (selectionCO2.size() == 1) {
                break;
            }
            oldReport = selectionCO2;
            selectionCO2.clear();
        }
        int oxygen = Integer.parseInt(selectionOxygen.toString(), 2);
        int CO2 = Integer.parseInt(selectionCO2.toString(), 2);
        System.out.println("Oxygen: " + oxygen);
        int[] oxygenCO2 = {oxygen, CO2};
        return oxygenCO2;
    }

    public static void main (String[] args) throws IOException  {
        // Part 1 Test
        ArrayList<String> testReport = importFile("test_input.txt");
        int[] testGammaList = countOnes(testReport);
        String[] testGammaEpsilon = createGammaAndEpsiolon(testReport, testGammaList);
        int[] testGammaEpsilonInt = {getDecimal(testGammaEpsilon[0]), getDecimal(testGammaEpsilon[1])};
        System.out.println("Test gamma: " + String.valueOf(testGammaEpsilon[0]));
        int testAnswer = multiplyGammaAndEpsilon(testGammaEpsilonInt);
        System.out.println("Test power consumption is: " + testAnswer);

        // Part 1
        ArrayList<String> Report = importFile("input.txt");
        int[] GammaList = countOnes(Report);
        String[] GammaEpsilon = createGammaAndEpsiolon(Report, GammaList);
        int[] GammaEpsilonInt = {getDecimal(GammaEpsilon[0]), getDecimal(GammaEpsilon[1])};
        System.out.println("Gamma: " + String.valueOf(GammaEpsilonInt[0]));
        int Answer = multiplyGammaAndEpsilon(GammaEpsilonInt);
        System.out.println(Answer);

        // Part 2 Test
        int[] testOxygenCO2 = createOxygenAndCO2(testReport, testGammaEpsilon);
        //System.out.println(String.valueOf(testOxygenCO2[0]));
    }
}
