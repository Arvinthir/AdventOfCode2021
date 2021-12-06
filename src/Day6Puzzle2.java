import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Day6Puzzle2 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day6.txt");
        Scanner s = new Scanner(inputFile);

        String[] input = s.nextLine().trim().split(",");
        BigInteger[] fish = new BigInteger[9];
        for (int i = 0; i < 9; i++) {
            fish[i] = BigInteger.valueOf(0);
            for (int j = 0; j < input.length; j++) {
                if (Integer.parseInt(input[j]) == i) {
                    fish[i] = fish[i].add(BigInteger.valueOf(1));
                }
            }
        }

        for (int i = 0; i < 256; i++) {
            BigInteger tmp = fish[0];
            for (int j = 0; j < 8; j++) {
                fish[j] = fish[j+1];
            }
            fish[6] = fish[6].add(tmp);
            fish[8] = tmp;
        }

        BigInteger result = BigInteger.valueOf(0);
        for (int i = 0; i < 9; i++) {
            result = result.add(fish[i]);
        }
        System.out.println("Total fish: "+result);
    }
}
