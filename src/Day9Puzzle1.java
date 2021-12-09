import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day9Puzzle1 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day9.txt");
        Scanner s = new Scanner(inputFile);
        int[][] heightmap = new int[100][100];

        int line = 0;
        while (s.hasNextLine()) {
            String[] input = s.nextLine().split("");
            for (int i = 0; i < input.length; i++) {
                heightmap[line][i] = Integer.parseInt(input[i]);
            }
            line++;
        }

        int sum = 0;
        for (int i = 0; i < heightmap.length; i++) {
            for (int j = 0; j < heightmap[0].length; j++) {
                if (i == 0) {
                    if (j == 0) {
                        if (heightmap[i][j] < heightmap[i+1][j] &&
                                heightmap[i][j] < heightmap[i][j+1]
                        ) {
                           //System.out.println(heightmap[i][j]);
                           sum += heightmap[i][j]+1;
                        }
                    } else if (j == heightmap[0].length-1) {
                        if (heightmap[i][j] < heightmap[i+1][j] &&
                                heightmap[i][j] < heightmap[i][j-1]
                        ) {
                            //System.out.println(heightmap[i][j]);
                            sum += heightmap[i][j]+1;
                        }
                    } else {
                        if (heightmap[i][j] < heightmap[i][j+1] &&
                                heightmap[i][j] < heightmap[i][j-1] &&
                                heightmap[i][j] < heightmap[i+1][j]
                        ) {
                            //System.out.println(heightmap[i][j]);
                            sum += heightmap[i][j]+1;
                        }
                    }
                } else if (i == heightmap.length-1) {
                    if (j == 0) {
                        if (heightmap[i][j] < heightmap[i][j+1] &&
                                heightmap[i][j] < heightmap[i-1][j]
                        ) {
                            //System.out.println(heightmap[i][j]);
                            sum += heightmap[i][j]+1;
                        }
                    } else if (j == heightmap[0].length-1) {
                        if (heightmap[i][j] < heightmap[i][j-1] &&
                                heightmap[i][j] < heightmap[i-1][j]
                        ) {
                            //System.out.println(heightmap[i][j]);
                            sum += heightmap[i][j]+1;
                        }
                    } else {
                        if (heightmap[i][j] < heightmap[i][j+1] &&
                                heightmap[i][j] < heightmap[i][j-1] &&
                                heightmap[i][j] < heightmap[i-1][j]
                        ) {
                            //System.out.println(heightmap[i][j]);
                            sum += heightmap[i][j]+1;
                        }
                    }
                } else if (j == 0) {
                    if (heightmap[i][j] < heightmap[i][j+1] &&
                            heightmap[i][j] < heightmap[i+1][j] &&
                            heightmap[i][j] < heightmap[i-1][j]
                    ) {
                        //System.out.println(heightmap[i][j]);
                        sum += heightmap[i][j]+1;
                    }
                }
                else if (j == heightmap[0].length-1) {
                    if (heightmap[i][j] < heightmap[i][j-1] &&
                            heightmap[i][j] < heightmap[i+1][j] &&
                            heightmap[i][j] < heightmap[i-1][j]
                    ) {
                        //System.out.println(heightmap[i][j]);
                        sum += heightmap[i][j]+1;
                    }
                } else {
                    if (heightmap[i][j] < heightmap[i][j+1] &&
                            heightmap[i][j] < heightmap[i][j-1] &&
                            heightmap[i][j] < heightmap[i+1][j] &&
                            heightmap[i][j] < heightmap[i-1][j]
                    ) {
                        //System.out.println(heightmap[i][j]);
                        sum += heightmap[i][j]+1;
                    }
                }
            }
        }

        System.out.println(sum);
    }
}
