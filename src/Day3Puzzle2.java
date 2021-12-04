import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day3Puzzle2 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day3.txt");

        char[] oxygen = new char[12];
        char[] co2 = new char[12];
        int oxygenZeroes = 0;
        int oxygenOnes = 0;
        int co2Zeroes = 0;
        int co2Ones = 0;

        for (int n = 0; n < 12; n++) {
            Scanner s = new Scanner(inputFile);
            while (s.hasNextLine()) {
                char[] input = s.nextLine().toCharArray();
                if (check(oxygen, input, n)) {
                    if (input[n] == '0') {
                        oxygenZeroes++;
                    } else {
                        oxygenOnes++;
                    }
                }
                if (check(co2, input, n)) {
                    if (input[n] == '0') {
                        co2Zeroes++;
                    } else {
                        co2Ones++;
                    }
                }
            }
            s.close();

            if (oxygenZeroes > oxygenOnes) {
                oxygen[n] = '0';
            } else {
                oxygen[n] = '1';
            }
            if (co2Ones < co2Zeroes) {
                if (co2Ones == 0) {
                    co2[n] = '0';
                } else {
                    co2[n] = '1';
                }
            } else {
                if (co2Zeroes == 0) {
                    co2[n] = '1';
                } else {
                    co2[n] = '0';
                }
            }
            oxygenZeroes = 0;
            oxygenOnes = 0;
            co2Zeroes = 0;
            co2Ones = 0;
        }

        int oxygenInt = Integer.parseInt(String.valueOf(oxygen), 2);
        int co2Int = Integer.parseInt(String.valueOf(co2), 2);
        System.out.println("oxygen: "+oxygenInt);
        System.out.println("co2: "+co2Int);
        System.out.println("final: "+ oxygenInt*co2Int);
    }

    private static boolean check(char[] reference, char[] input, int n) {
        if (n == 0) {
            return true;
        }
        for (int i = 0; i < n; i++) {
            if (input[i] != reference[i]) {
                return false;
            }
        }
        return true;
    }
}
