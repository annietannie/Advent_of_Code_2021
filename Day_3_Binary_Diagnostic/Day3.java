import java.util.Scanner;
import java.util.stream.IntStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.stream.Collectors;
import java.util.List;

public class Day3 {
    /* public static Object readFile(String fileName) throws IOException {
        var report = Files
                .lines(Paths.get(fileName))
                .collect(Collectors.toList());
        return report;
    }; */

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

    /* public static int getPowerConsumption(List<String> report) {
        ArrayList<Integer> gammaListTemp = constructGammaListTemp(report);
        ArrayList<Integer> gammaList = getGammaList(gammaListTemp);
        int gammaValue = getGammaValue(gammaList);
        int binMax = getBinMax(report);
        int epsilonValue = getEpsilonValue(gammaValue, binMax);
        int PowerConsumption = setPowerConsumption(gammaValue, epsilonValue);
        return PowerConsumption;
    } */

    public static ArrayList<ArrayList<Integer>> constructGammaListTemp(ArrayList<String> report) {
        ArrayList<ArrayList<Integer>> gammaListTemp = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> listInList = new ArrayList<Integer>();
        listInList.add(0);
        listInList.add(0);
        for ( int i=0; i<report.get(1).length(); i++) {
            gammaListTemp.add(listInList);
        }
        return gammaListTemp;
    }

    public static ArrayList<ArrayList<Integer>> getGammaList(ArrayList<ArrayList<Integer>> gammaList, ArrayList<String> report) {
        int dataLine;
        int data;
        int zeroValue;
        int oneValue;
        for (int i=0; i<report.size(); i++) {
            for (int j=0; j<report.get(i).length(); j++) {
                zeroValue = gammaList.get(j).get(0);
                oneValue = gammaList.get(j).get(1);
                dataLine = report.get(i).charAt(j);
                System.out.println(dataLine);
                //dataLine = Integer.parseInt.report.get(i).charAt(j);
                //data = (dataLine == 0) ? zeroValue++ : oneValue++;
                //gammaList.get(j).set(0, dataLine);
            }
        }
        return gammaList;
    }
/*
    public static int getGammaValue(ArrayList<Integer> gammaList) {
        int[] gamma;
        gamma[0] = gammaList.get(0).get(0) > gammaList.get(0).get(1) ? 0 : 1;
        gamma[1] = gammaList.get(1).get(0) > gammaList.get(1).get(1) ? 0 : 1;
        gamma[2] = gammaList.get(2).get(0) > gammaList.get(2).get(1) ? 0 : 1;
        gamma[3] = gammaList.get(3).get(0) > gammaList.get(3).get(1) ? 0 : 1;
        gamma[4] = gammaList.get(4).get(0) > gammaList.get(4).get(1) ? 0 : 1;
        int gammaValue = Integer.parseInt(Arrays.toString(gamma), 2);
        System.out.println("Gamma: " + gammaValue);
        return gammaValue;
    }

    public static int getBinMax(List<String> report) {
        return Integer.parseInt(report.size());
    }

    public static int getEpsilonValue (int gammaValue, int binMax) {
        int epsilonValue = binMax - gammaValue;
        System.out.println("Epsilon: " + epsilonValue);
        return epsilonValue;
    }

    public static int setPowerConsumption (int gammaValue, int epsilonValue) {
        return gammaValue * epsilonValue;
    } */

    public static void main (String[] args) throws IOException  {
        // Test part 1
        ArrayList<String> testReport = importFile("test_input.txt");
        ArrayList<ArrayList<Integer>> testGammaListTemp = constructGammaListTemp(testReport);
        ArrayList<ArrayList<Integer>> testGammaList = getGammaList(testGammaListTemp, testReport);
        //System.out.println(Array.toString(testGammaListTemp));
        //int testPowerConsumption = getPowerConsumption(testReport);
        //System.out.println("Power consumption: " + testPowerConsumption);
    }
}
