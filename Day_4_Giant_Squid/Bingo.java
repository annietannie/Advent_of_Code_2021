import java.util.List;

import javax.crypto.spec.GCMParameterSpec;

import java.util.ArrayList;
import java.util.Arrays;

class Bingo {
    ArrayList<Integer> bingoBoardInt = new ArrayList<>();
    ArrayList<Boolean> bingoBoardBool = new ArrayList<>();
    int score;

    public Bingo(List<String> bingoList) {
        String[] bingoLineStr;
        this.score = 0;
        for (int i=0; i<bingoList.size(); i++) {
            bingoLineStr = bingoList.get(i).split("(?<=\\G.{3})");
            for (int j=0; j<bingoLineStr.length; j++) {
                bingoLineStr[j] = bingoLineStr[j].replaceAll("\\s+", "");
                this.bingoBoardInt.add(Integer.parseInt(bingoLineStr[j]));
                this.bingoBoardBool.add(false);
            }
            //System.out.println(Arrays.toString(bingoLineStr));
        }
        //System.out.println(this.bingoBoardInt.toString());
    }
    
    public int playNumber(int number) {
        for (int i=0; i<this.bingoBoardInt.size(); i++) {
            if (bingoBoardInt.get(i) == number) {
                bingoBoardBool.set(i, true);
                System.out.println("Found one on position " + i);
            }
        }
        //System.out.println(this.bingoBoardInt.toString());
        //System.out.println(this.bingoBoardBool.toString());
        if (checkIfGameOver()) {
            System.out.println("Game is over");
            return calculateSum();
        }
        return 0;

    }

    public Boolean checkIfGameOver() {
        return checkVertical() || checkHorizontal();
    }

    public boolean checkVertical() {
       List<Boolean> subList;
        for (int i=0; i<21; i=i+5) {
            subList = this.bingoBoardBool.subList(i, i+5);
            System.out.println("Sublist: " + subList.toString());
            if (checkForGameOver(subList)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkHorizontal() {
        List<Boolean> subList = new ArrayList<>();
        for (int i=0; i<5; i++) {
            for (int j=0; j<20; j=j+5) {
                subList.add(this.bingoBoardBool.get(i+j));
            }
            if (checkForGameOver(subList)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkForGameOver(List<Boolean> subList) {
        for (boolean gameState : subList) {
            if (!gameState) {
                return false;
            }
        }
        return true;
    }

    public int calculateSum() {
        for (int i=0; i<this.bingoBoardBool.size(); i++) {
            if (!bingoBoardBool.get(i)) {
                this.score += bingoBoardInt.get(i);
            }
        }
        System.out.println("My score is: " + this.score);
        return this.score;
    }
}
