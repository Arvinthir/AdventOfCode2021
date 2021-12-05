import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day5Puzzle2 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day5.txt");
        Scanner s = new Scanner(inputFile);

        int[][] map = new int[1000][1000];
        while(s.hasNextLine()) {
            String input = s.nextLine();
            String startVent = input.split(" -> ")[0];
            String endVent = input.split(" -> ")[1];
            int startY = Integer.parseInt(startVent.split(",")[0]);
            int startX = Integer.parseInt(startVent.split(",")[1]);
            int endY = Integer.parseInt(endVent.split(",")[0]);
            int endX = Integer.parseInt(endVent.split(",")[1]);

            int x = startX;
            int y = startY;

            if (startX == endX) {
                if (startY <= endY) {
                    while (y <= endY) {
                        map[x][y]++;
                        y++;
                    }
                } else {
                    while (y >= endY) {
                        map[x][y]++;
                        y--;
                    }
                }
            } else if (startY == endY) {
                if (startX <= endX) {
                    while (x <= endX) {
                        map[x][y]++;
                        x++;
                    }
                } else {
                    while (x >= endX) {
                        map[x][y]++;
                        x--;
                    }
                }
            } else if (startX < endX) {
                if (startY < endY) {
                    while (x <= endX) {
                        map[x][y]++;
                        x++;
                        y++;
                    }
                } else {
                    while (x <= endX) {
                        map[x][y]++;
                        x++;
                        y--;
                    }
                }
            } else if (startX > endX) {
                if (startY < endY) {
                    while (x >= endX) {
                        map[x][y]++;
                        x--;
                        y++;
                    }
                } else {
                    while (x >= endX) {
                        map[x][y]++;
                        x--;
                        y--;
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
        for (int i = 0; i < map[0].length; i++) {
            for (int j = 0; j < map[0].length; j++) {
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
