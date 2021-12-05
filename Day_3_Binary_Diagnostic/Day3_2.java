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

    public static ArrayList<Integer> constructGammaList(ArrayList<String> report) {
        ArrayList<Integer> gammaList = new ArrayList<Integer>();
        for (int i=0; i<report.get(0).length(); i++) {
            gammaList.add(0);
        }
        return gammaList;
    }

    public static ArrayList<Integer> countOnes(ArrayList<String> report, ArrayList<Integer> gammaList) {
        int dataLine;
        for (int i=0; i<report.size(); i++) {
            for (int j=0; j<gammaList.size(); j++) {
                dataLine = Character.getNumericValue(report.get(i).charAt(j));
                if (dataLine == 1) {
                    gammaList.set(j,gammaList.get(j)+1);
                }
            }
        }
        return gammaList;
    }

    public static int[] createGammaAndEpsiolon(ArrayList<String> report, ArrayList<Integer> gammaList) {
        int numberOfNumbers = report.size();
        StringBuilder gammaBinStr = new StringBuilder();
        StringBuilder epsilonBinStr = new StringBuilder();
        for (int i=0; i<gammaList.size(); i++) {
            if ((gammaList.get(i)*2) >= numberOfNumbers) {
                gammaBinStr.append(1);
                epsilonBinStr.append(0);
            } else {
                gammaBinStr.append(0);
                epsilonBinStr.append(1);
            }
        }
        //String gammaBin = String.join("", gammaArray.toString());
        String gammaBin = gammaBinStr.toString();
        String epsilonBin = epsilonBinStr.toString();
        int gamma = Integer.parseInt(gammaBin, 2);
        int epsilon = Integer.parseInt(epsilonBin, 2);
        int[] gammaEpsilon = {gamma, epsilon};
        return gammaEpsilon;
    }

    public static int multiplyGammaAndEpsilon(int[] gammaEpsilon) {
        return gammaEpsilon[0] * gammaEpsilon[1];
    }

    public static void main (String[] args) throws IOException  {
        // Part 1 Test
        ArrayList<String> testReport = importFile("test_input.txt");
        ArrayList<Integer> testGammaListTemp = constructGammaList(testReport);
        ArrayList<Integer> testGammaList = countOnes(testReport, testGammaListTemp);
        int[] testGammaEpsilon = createGammaAndEpsiolon(testReport, testGammaList);
        System.out.println("Gamma: " + String.valueOf(testGammaEpsilon[0]));
        int testAnswer = multiplyGammaAndEpsilon(testGammaEpsilon);
        System.out.println(testAnswer);

        // Part 1
        ArrayList<String> Report = importFile("input.txt");
        ArrayList<Integer> GammaListTemp = constructGammaList(Report);
        ArrayList<Integer> GammaList = countOnes(Report, GammaListTemp);
        int[] GammaEpsilon = createGammaAndEpsiolon(Report, GammaList);
        System.out.println("Gamma: " + String.valueOf(GammaEpsilon[0]));
        int Answer = multiplyGammaAndEpsilon(GammaEpsilon);
        System.out.println(Answer);

    }
}
