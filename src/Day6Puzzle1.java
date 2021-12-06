import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6Puzzle1 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day6.txt");
        Scanner s = new Scanner(inputFile);

        String[] input = s.nextLine().trim().split(",");
        ArrayList<Integer> ages = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            ages.add(Integer.parseInt(input[i]));
        }

        for (int i = 0; i < 80; i++) {
            /*
            System.out.print("Ages: ");
            for (int j = 0; j < ages.size(); j++) {
                System.out.print(ages.get(j)+" ");
            }
            System.out.print("\n");*/
            int length = ages.size();
            for (int j = 0; j < length; j++) {
                if (ages.get(j) == 0) {
                    ages.add(8);
                    ages.set(j, 6);
                } else {
                    ages.set(j, ages.get(j)-1);
                }
            }
        }
        /*
        System.out.print("Ages: ");
        for (int j = 0; j < ages.size(); j++) {
            System.out.print(ages.get(j)+" ");
        }
        System.out.print("\n");
        System.out.print("\n");*/
        System.out.println("Total fish: "+ages.size());
    }
}
