import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

class BingoBoard {
    List<String> report;
    int[] numbersList;
    ArrayList<Bingo> bingoBoards;
    int score;

    public BingoBoard(String fileName) {
        importFile(fileName);
        createNumbersList();
        //System.out.println(Arrays.toString(this.numbersList));
        createBingoBoards();
        playBingo();
        System.out.println("Score: " + this.score);
    }

    public void importFile(String fileName) {
        this.report = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(new File(fileName));
            while(myReader.hasNextLine()) {
                this.report.add(myReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    public void createNumbersList() {
        ArrayList<String> numbersListString = new ArrayList<>(Arrays.asList(report.get(0).split(",")));
        this.numbersList = new int[numbersListString.size()];
        for (int i=0; i<numbersListString.size(); i++) {
            this.numbersList[i] = Integer.parseInt(numbersListString.get(i));
        }
    }

    public void createBingoBoards() {
        List<String> bingoList;
        this.bingoBoards = new ArrayList<>();
        for (int i=2; i< this.report.size(); i=i+6) {
            bingoList = this.report.subList(i, i+5);
            //System.out.println(bingoList);
            this.bingoBoards.add(new Bingo(bingoList));
        }
    }

    public void playBingo() {
        int roundNr = 1;
        for (int num : this.numbersList) {
            //System.out.println("Round: " + roundNr + ", number: " + num);
            roundNr++;
            for (Bingo bingoBoard : this.bingoBoards) {
                this.score = bingoBoard.playNumber(num);
                if (this.score != 0) {
                    break;
                }
            }
            if (this.score != 0) {
                System.out.println("Sum: " + this.score);
                System.out.println("Num: " + num);
                this.score = this.score * num;
                break;
            }
        }  
    }

}
