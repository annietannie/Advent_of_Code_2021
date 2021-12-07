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

    public static String createOxygen(ArrayList<String> oldReport) {
        ArrayList<String> selectionOxygen = new ArrayList<String>();
        ArrayList<String> dataSubset = oldReport;
        int index = 0;
        while (dataSubset.size() != 1) {
            int oneCounter = 0;
            int zeroCounter = 0;
            for (int i=0; i<dataSubset.size(); i++) {
                int dataLine = Character.getNumericValue(dataSubset.get(i).charAt(index));
                if (dataLine == 1) {
                    oneCounter++;
                } else {
                    zeroCounter++;
                }
            }
            char bit = '1';
            if (oneCounter != zeroCounter) {
                if (zeroCounter > oneCounter) { 
                    bit = '0';
                }
            }

            char bit2 = bit;
            int index2 = index;
            dataSubset = dataSubset.stream()
                .filter(v -> v.charAt(index2) == bit2)
                .collect(Collectors.toCollection(ArrayList::new));
            index++;
        }
        return dataSubset.get(0);
    }

    public static String createCO2(ArrayList<String> oldReport) {
        ArrayList<String> selectionOxygen = new ArrayList<String>();
        ArrayList<String> dataSubset = oldReport;
        int index = 0;
        while (dataSubset.size() != 1) {
            int oneCounter = 0;
            int zeroCounter = 0;
            for (int i=0; i<dataSubset.size(); i++) {
                int dataLine = Character.getNumericValue(dataSubset.get(i).charAt(index));
                if (dataLine == 1) {
                    oneCounter++;
                } else {
                    zeroCounter++;
                }
            }
            char bit = '0';
            if (oneCounter != zeroCounter) {
                if (zeroCounter > oneCounter) { 
                    bit = '1';
                }
            }

            char bit2 = bit;
            int index2 = index;
            dataSubset = dataSubset.stream()
                .filter(v -> v.charAt(index2) == bit2)
                .collect(Collectors.toCollection(ArrayList::new));
            index++;
        }
        return dataSubset.get(0);
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
        String testOxygen = createOxygen(testReport);
        String testCO2 = createCO2(testReport);
        System.out.println(testOxygen);
        int testOxygenValue = getDecimal(testOxygen);
        int testCO2Value = getDecimal(testCO2);
        System.out.println(testOxygenValue);
        System.out.println(testCO2Value);
        //System.out.println(String.valueOf(testOxygenCO2[0]));

        // Part 2
        String Oxygen = createOxygen(Report);
        String CO2 = createCO2(Report);
        System.out.println(Oxygen);
        int OxygenValue = getDecimal(Oxygen);
        int CO2Value = getDecimal(CO2);
        System.out.println(OxygenValue);
        System.out.println(CO2Value);
        System.out.println(OxygenValue * CO2Value);
    }
}
