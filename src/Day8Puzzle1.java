import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day8Puzzle1 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day8.txt");
        Scanner s = new Scanner(inputFile);

        int counter = 0;
        while (s.hasNextLine()) {
            String[] input = s.nextLine().split("\\|");
            String[] digits = input[1].trim().split(" ");
            for (int i = 0; i < digits.length; i++) {
                if (digits[i].length() == 2 || digits[i].length() == 3 || digits[i].length() == 4 || digits[i].length() == 7) {
                    counter++;
                }
            }
        }

        System.out.println(counter);
    }
}
