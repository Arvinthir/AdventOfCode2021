import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day5Puzzle1 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day5.txt");
        Scanner s = new Scanner(inputFile);

        int[][] map = new int[1000][1000];
        while(s.hasNextLine()) {
            String input = s.nextLine();
            String startVent = input.split(" -> ")[0];
            String endVent = input.split(" -> ")[1];
            int startX = Integer.parseInt(startVent.split(",")[0]);
            int startY = Integer.parseInt(startVent.split(",")[1]);
            int endX = Integer.parseInt(endVent.split(",")[0]);
            int endY = Integer.parseInt(endVent.split(",")[1]);
            if (startX == endX) {
                if (startY <= endY) {
                    for (int i = startY; i <= endY; i++) {
                        map[startX][i]++;
                    }
                } else {
                    for (int i = endY; i <= startY; i++) {
                        map[startX][i]++;
                    }
                }
            } else if (startY == endY) {
                if (startX <= endX) {
                    for (int i = startX; i <= endX; i++) {
                        map[i][startY]++;
                    }
                } else {
                    for (int i = endX; i <= startX; i++) {
                        map[i][startY]++;
                    }
                }
            }
        }

        int counter = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 1) {
                    counter++;
                }
            }
        }
        /*
        for (int j = 0; j < map[0].length; j++) {
            for (int i = 0; i < map.length; i++) {
                if (map[i][j] == 0) {
                    System.out.print(".");
                } else {
                    System.out.print(map[i][j]);
                }
            }
            System.out.print("\n");
        }*/

        System.out.println("counter: "+counter);
    }
}
