import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Submarine2 extends Submarine{
    int aim;

    public Submarine2() {
        super();
        this.aim = 0;
    }

    @Override
    public void forward(int x) {
        this.horizontalPosition += x;
        this.depth += this.aim * x;
    }

    @Override
    public void down(int x) {
        this.aim += x;
    }

    @Override
    public void up(int x) {
        this.aim -= x;
    } 
}
