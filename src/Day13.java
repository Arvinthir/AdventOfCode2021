import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day13 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day13.txt");
        Scanner s = new Scanner(inputFile);

        boolean[][] paper = new boolean[10][10];
        boolean firstPart = true;
        while(s.hasNextLine()) {
            String line = s.nextLine();
            if (line.equals("")) {
                firstPart = false;
                continue;
            }
            if (firstPart) {
                String[] input = line.split(",");
                int[] in = new int[2];
                in[0] = Integer.parseInt(input[0]);
                in[1] = Integer.parseInt(input[1]);
                if (in[0] >= paper.length) {
                    paper = resizeTable(paper, in[0], 'x');
                }
                if (in[1] >= paper[0].length) {
                    paper = resizeTable(paper, in[1], 'y');
                }
                paper[in[0]][in[1]] = true;
            } else {
                String[] input = line.split(" ");
                String[] in = input[2].split("=");
                paper = foldTable(paper, Integer.parseInt(in[1]),in[0]);
                //uncomment next line for part 1
                //break;
            }
        }

        //comment next line to avoid large console outputs for part 1
        printTable(paper);
        System.out.println("Dots: "+countDots(paper));

    }

    static boolean[][] resizeTable(boolean[][] table, int size, char dimension) {
        boolean[][] newTable;
        if (dimension == 'x') {
            newTable = new boolean[size+1][table[0].length];
        } else {
            newTable = new boolean[table.length][size+1];
        }
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                newTable[i][j] = table[i][j];
            }
        }
        return newTable;
    }

    static boolean[][] foldTable(boolean[][] table, int location, String dimension) {
        boolean[][] newTable;
        if (dimension.equals("y")) {
            newTable = new boolean[table.length][location];
            for (int i = 0; i < newTable.length; i++) {
                for (int j = 0; j < newTable[0].length; j++) {
                    if(table[i][j]) {
                        newTable[i][j] = true;
                    }
                }
                int n = location-1;
                for (int j = location+1; j < table[0].length; j++) {
                    if(table[i][j]) {
                        newTable[i][n] = true;
                    }
                    n--;
                }
            }
        } else {
            newTable = new boolean[location][table[0].length];
            for (int j = 0; j < newTable[0].length; j++) {
                for (int i = 0; i < newTable.length; i++) {
                    if(table[i][j]) {
                        newTable[i][j] = true;
                    }
                }
                int n = location-1;
                for (int i = location+1; i < table.length; i++) {
                    if(table[i][j]) {
                        newTable[n][j] = true;
                    }
                    n--;
                }
            }
        }
        return newTable;
    }

    static int countDots(boolean[][] table) {
        int sum = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j]) {
                    sum++;
                }
            }
        }
        return sum;
    }

    static void printTable(boolean[][] table) {
        for (int j = 0; j < table[0].length; j++) {
            for (int i = 0; i < table.length; i++) {
                if (table[i][j]) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
