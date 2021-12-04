import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1Puzzle2 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day1.txt");
        Scanner s = new Scanner(inputFile);

        int output = 0;
        int window1 = 1;
        int window2 = 1;
        int window3 = 1;
        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;
        ArrayList<Integer> depth = new ArrayList<>();
        int n = 0;
        while (s.hasNextLine()) {
            n++;
            int currLine = Integer.parseInt(s.nextLine());

            sum1 += currLine;
            window1++;
            if (window1 == 4) {
                depth.add(sum1);
                sum1 = 0;
                window1 = 1;
            }
            if (n > 1) {
                sum2 += currLine;
                window2++;
                if (window2 == 4) {
                    depth.add(sum2);
                    sum2 = 0;
                    window2 = 1;
                }
            }
            if (n > 2) {
                sum3 += currLine;
                window3++;
                if (window3 == 4) {
                    depth.add(sum3);
                    sum3 = 0;
                    window3 = 1;
                }
            }
        }
        int lastDepth = 0;
        boolean first = true;
        for (int i : depth) {
            if (first) {
                lastDepth = i;
                first = false;
            } else {
                if (i > lastDepth) {
                    output++;
                }
                lastDepth = i;
            }
        }
        System.out.println(depth);
        System.out.println(output);
    }
}