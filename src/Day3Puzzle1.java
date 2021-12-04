import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day3Puzzle1 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day3.txt");
        Scanner s = new Scanner(inputFile);

        int[] zeroes = new int[12];
        int[] ones = new int[12];
        String gamma = "";
        String epsilon = "";
        while (s.hasNextLine()) {
            char[] input = s.nextLine().toCharArray();
            for (int i = 0; i < input.length; i++) {
                if (input[i] == '0') {
                    zeroes[i]++;
                } else {
                    ones[i]++;
                }
            }
        }
        for (int i = 0; i < 12; i++) {
            if (zeroes[i] > ones[i]) {
                gamma = gamma.concat("0");
                epsilon = epsilon.concat("1");
            } else {
                gamma = gamma.concat("1");
                epsilon = epsilon.concat("0");
            }
        }
        int gammaDec = Integer.parseInt(gamma,2);
        int epsilonDec = Integer.parseInt(epsilon,2);
        System.out.println("gamma: "+gamma+" = "+gammaDec);
        System.out.println("epsilon: "+epsilon+" = "+epsilonDec);
        System.out.println("final: "+ gammaDec*epsilonDec);
    }
}
