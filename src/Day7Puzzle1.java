import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day7Puzzle1 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day7.txt");
        Scanner s = new Scanner(inputFile);

        String[] input = s.nextLine().trim().split(",");
        int[] crabs = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            crabs[i] = Integer.parseInt(input[i]);
        }

        int max = 0;
        for (int i = 0; i < crabs.length; i++) {
            if (crabs[i] > max) {
                max = crabs[i];
            }
        }

        int minFuel = Integer.MAX_VALUE;
        int pos = 0;
        for (int i = 0; i <= max; i++) {
            int fuel = 0;
            for (int j = 0; j < crabs.length; j++) {
                fuel += Math.abs(crabs[j] - i);
            }
            if (minFuel > fuel) {
                minFuel = fuel;
                pos = i;
            }
        }

        System.out.printf("Position: %d, Fuel: %d%n",pos,minFuel);
    }
}
