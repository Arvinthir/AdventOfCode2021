import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day1Puzzle1 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day1.txt");
        Scanner s = new Scanner(inputFile);

        int output = 0;
        int lastDepth = Integer.parseInt(s.nextLine());
        int currDepth;
        while (s.hasNextLine()) {
            currDepth = Integer.parseInt(s.nextLine());
            if (currDepth > lastDepth) {
                output++;
            }
            lastDepth = currDepth;
        }
        System.out.println(output);
    }
}