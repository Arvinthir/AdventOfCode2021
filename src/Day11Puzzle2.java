import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day11Puzzle2 {
    static int flashes = 0;
    static int[][] map = new int[10][10];
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day11.txt");
        Scanner s = new Scanner(inputFile);

        int line = 0;
        while (s.hasNextLine()) {
            String[] input = s.nextLine().split("");
            for (int i = 0; i < input.length; i++) {
                map[line][i] = Integer.parseInt(input[i]);
            }
            line++;
        }

        int n = 0;
        while(true) {
            flashes = 0;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    map[i][j]++;
                }
            }
            while (hasFlashes()) {
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[0].length; j++) {
                        if (map[i][j] > 9) {
                            flash(i, j);
                            i = 0;
                            j = -1;
                        }
                    }
                }
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[0].length; j++) {
                        if (map[i][j] < 0) {
                            map[i][j] = 0;
                        }
                    }
                }
            }
            /*
            if (n >= 190) {
                System.out.println("After "+(n+1));
                printMap();
            }*/
            if (flashes == 100) {
                System.out.println(n+1);
                break;
            }
            n++;
        }

    }

    static boolean hasFlashes() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 9) {
                    return true;
                }
            }
        }
        return false;
    }

    static void flash(int i, int j) {
        map[i][j] = Integer.MIN_VALUE;
        flashes++;

        if (i == 0) {
            if (j == 0) {
                //top left corner
                map[i+1][j] += 1;
                map[i+1][j+1] += 1;
                map[i][j+1] += 1;
            } else if (j == map[0].length-1) {
                //top right corner
                map[i+1][j] += 1;
                map[i+1][j-1] += 1;
                map[i][j-1] += 1;
            } else {
                //top row
                map[i+1][j-1] += 1;
                map[i+1][j] += 1;
                map[i+1][j+1] += 1;
                map[i][j-1] += 1;
                map[i][j+1] += 1;
            }
        } else if (i == map.length-1) {
            if (j == 0) {
                //bottom left corner
                map[i][j+1] += 1;
                map[i-1][j] += 1;
                map[i-1][j+1] += 1;
            } else if (j == map[0].length-1) {
                //bottom right corner
                map[i][j-1] += 1;
                map[i-1][j] += 1;
                map[i-1][j-1] += 1;
            } else {
                //bottom row
                map[i][j-1] += 1;
                map[i][j+1] += 1;
                map[i-1][j-1] += 1;
                map[i-1][j] += 1;
                map[i-1][j+1] += 1;
            }
        } else if (j == 0) {
            //left row
            map[i+1][j] += 1;
            map[i+1][j+1] += 1;
            map[i][j+1] += 1;
            map[i-1][j] += 1;
            map[i-1][j+1] += 1;
        }
        else if (j == map[0].length-1) {
            //right row
            map[i+1][j] += 1;
            map[i+1][j-1] += 1;
            map[i][j-1] += 1;
            map[i-1][j] += 1;
            map[i-1][j-1] += 1;
        } else {
            //middle
            map[i+1][j+1] += 1;
            map[i+1][j] += 1;
            map[i+1][j-1] += 1;
            map[i][j-1] += 1;
            map[i][j+1] += 1;
            map[i-1][j+1] += 1;
            map[i-1][j] += 1;
            map[i-1][j-1] += 1;
        }
    }

    static void printMap() {
        for (int j = 0; j < map.length; j++) {
            for (int i = 0; i < map.length; i++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
