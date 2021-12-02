import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.stream.IntStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.io.IOException;

public class Day_1_short {
    public static void main(String[] args) throws IOException {
        var report = Files
            .lines(Paths.get("test_data.txt"))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        System.out.println(
            IntStream
            .range(1,report.size())
            .filter(i -> report.get(i) > report.get(i-1))
            .count()
        );
    }
}