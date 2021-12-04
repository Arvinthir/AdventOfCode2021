import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day2Puzzle1 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day2.txt");
        Scanner s = new Scanner(inputFile);

        int position = 0;
        int depth = 0;
        while (s.hasNextLine()) {
            String[] data = s.nextLine().split(" ");

            switch (data[0]) {
                case "forward":
                    position += Integer.parseInt(data[1]);
                    break;
                case "up":
                    depth -= Integer.parseInt(data[1]);
                    break;
                case "down":
                    depth += Integer.parseInt(data[1]);
            }
        }
        System.out.println("position: " + position);
        System.out.println("depth: " + depth);
        System.out.println("output: " + position*depth);
    }
}
