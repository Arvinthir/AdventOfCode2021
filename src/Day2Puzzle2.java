import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day2Puzzle2 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day2.txt");
        Scanner s = new Scanner(inputFile);

        int position = 0;
        int depth = 0;
        int aim = 0;
        while (s.hasNextLine()) {
            String[] data = s.nextLine().split(" ");
            int n = Integer.parseInt(data[1]);
            switch (data[0]) {
                case "forward":
                    position += n;
                    depth += aim * n;
                    break;
                case "up":
                    aim -= n;
                    break;
                case "down":
                    aim += n;
            }
        }
        System.out.println("position: " + position);
        System.out.println("depth: " + depth);
        System.out.println("output: " + position*depth);
    }
}
