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
        ArrayList<Integer> gammaArray = new ArrayList<Integer>();
        ArrayList<Integer> epsilonArray = new ArrayList<Integer>();
        for (int i=0; i<gammaList.size(); i++) {
            if (gammaList.get(i)/numberOfNumbers >= (1/2)) {
                gammaArray.add(1);
                epsilonArray.add(0);
            } else {
                gammaArray.add(0);
                epsilonArray.add(1);
            }
        }
        String gammaBin = gammaArray.toString();
        String epsilonBin = epsilonArray.toString();
        System.out.println(gammaBin + " " + epsilonBin);
        /* int gamma = Integer.parseInt(gammaBin, 2);
        int epsilon = Integer.parseInt(epsilonBin, 2);
        int[] gammaEpsilon = {gamma, epsilon}; */
        return null;
    }

    public static void main (String[] args) throws IOException  {
        ArrayList<String> testReport = importFile("test_input.txt");
        ArrayList<Integer> testGammaListTemp = constructGammaList(testReport);
        ArrayList<Integer> testGammaList = countOnes(testReport, testGammaListTemp);
        int[] testGammaEpsilon = createGammaAndEpsiolon(testReport, testGammaList);
        System.out.println(testGammaEpsilon);
    }
}
