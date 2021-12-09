import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Day9Puzzle2 {
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

        ArrayList<Integer> basins = new ArrayList<>();
        ArrayList<int[]> visited = new ArrayList<>();
        for (int i = 0; i < heightmap.length; i++) {
            for (int j = 0; j < heightmap[0].length; j++) {
                if (i == 0) {
                    if (j == 0) {
                        if (heightmap[i][j] < heightmap[i+1][j] &&
                                heightmap[i][j] < heightmap[i][j+1]
                        ) {
                            System.out.println(heightmap[i][j]);
                            basins.add(getBasin(i, j, heightmap, visited));
                            visited.clear();
                        }
                    } else if (j == heightmap[0].length-1) {
                        if (heightmap[i][j] < heightmap[i+1][j] &&
                                heightmap[i][j] < heightmap[i][j-1]
                        ) {
                            System.out.println(heightmap[i][j]);
                            basins.add(getBasin(i, j, heightmap, visited));
                            visited.clear();
                        }
                    } else {
                        if (heightmap[i][j] < heightmap[i][j+1] &&
                                heightmap[i][j] < heightmap[i][j-1] &&
                                heightmap[i][j] < heightmap[i+1][j]
                        ) {
                            System.out.println(heightmap[i][j]);
                            basins.add(getBasin(i, j, heightmap, visited));
                            visited.clear();
                        }
                    }
                } else if (i == heightmap.length-1) {
                    if (j == 0) {
                        if (heightmap[i][j] < heightmap[i][j+1] &&
                                heightmap[i][j] < heightmap[i-1][j]
                        ) {
                            System.out.println(heightmap[i][j]);
                            basins.add(getBasin(i, j, heightmap, visited));
                            visited.clear();
                        }
                    } else if (j == heightmap[0].length-1) {
                        if (heightmap[i][j] < heightmap[i][j-1] &&
                                heightmap[i][j] < heightmap[i-1][j]
                        ) {
                            System.out.println(heightmap[i][j]);
                            basins.add(getBasin(i, j, heightmap, visited));
                            visited.clear();
                        }
                    } else {
                        if (heightmap[i][j] < heightmap[i][j+1] &&
                                heightmap[i][j] < heightmap[i][j-1] &&
                                heightmap[i][j] < heightmap[i-1][j]
                        ) {
                            System.out.println(heightmap[i][j]);
                            basins.add(getBasin(i, j, heightmap, visited));
                            visited.clear();
                        }
                    }
                } else if (j == 0) {
                    if (heightmap[i][j] < heightmap[i][j+1] &&
                            heightmap[i][j] < heightmap[i+1][j] &&
                            heightmap[i][j] < heightmap[i-1][j]
                    ) {
                        System.out.println(heightmap[i][j]);
                        basins.add(getBasin(i, j, heightmap, visited));
                        visited.clear();
                    }
                }
                else if (j == heightmap[0].length-1) {
                    if (heightmap[i][j] < heightmap[i][j-1] &&
                            heightmap[i][j] < heightmap[i+1][j] &&
                            heightmap[i][j] < heightmap[i-1][j]
                    ) {
                        System.out.println(heightmap[i][j]);
                        basins.add(getBasin(i, j, heightmap, visited));
                        visited.clear();
                    }
                } else {
                    if (heightmap[i][j] < heightmap[i][j+1] &&
                            heightmap[i][j] < heightmap[i][j-1] &&
                            heightmap[i][j] < heightmap[i+1][j] &&
                            heightmap[i][j] < heightmap[i-1][j]
                    ) {
                        System.out.println(heightmap[i][j]);
                        basins.add(getBasin(i, j, heightmap, visited));
                        visited.clear();
                    }
                }
            }
        }

        int[] largestBasins = new int[3];
        for (int i : basins) {
            if (i > largestBasins[0]) {
                largestBasins[2] = largestBasins[1];
                largestBasins[1] = largestBasins[0];
                largestBasins[0] = i;
            } else if (i > largestBasins[1]) {
                largestBasins[2] = largestBasins[1];
                largestBasins[1] = i;
            } else if (i > largestBasins[2]) {
                largestBasins[2] = i;
            }
        }

        System.out.printf("%d * %d * %d = %d", largestBasins[0], largestBasins[1], largestBasins[2], largestBasins[0]*largestBasins[1]*largestBasins[2]);
    }

    static int getBasin(int i, int j, int[][] map, ArrayList<int[]> visited) {
        int sum = 0;
        if (map[i][j] == 9) {
            return sum;
        }
        visited.add(new int[]{i,j});
        if (i > 0 && !checkVisited(i-1, j, visited)) {
            sum += getBasin(i-1, j, map, visited);
        }
        if (i < map.length-1 && !checkVisited(i+1, j, visited)) {
            sum += getBasin(i+1, j, map, visited);
        }
        if (j > 0 && !checkVisited(i, j-1, visited)) {
            sum += getBasin(i, j-1, map, visited);
        }
        if (j < map[0].length-1 && !checkVisited(i, j+1, visited)) {
            sum += getBasin(i, j+1, map, visited);
        }
        return sum + 1;
    }

    static boolean checkVisited(int i, int j, ArrayList<int[]> visited) {
        for (int[] v : visited) {
            if (i == v[0] && j == v[1]) {
                return true;
            }
        }
        return false;
    }
}
