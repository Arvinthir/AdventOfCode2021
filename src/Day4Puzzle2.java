import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4Puzzle2 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day4.txt");
        Scanner s = new Scanner(inputFile);

        //Get drawn numbers
        String[] data = s.nextLine().split(",");
        int[] numbers = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            numbers[i] = Integer.parseInt(data[i]);
        }

        ArrayList<BingoBoard> boards = new ArrayList<>();
        //Fill bingo boards
        BingoBoard currBoard = new BingoBoard();
        int line = 0;
        while(s.hasNextLine()) {
            String in = s.nextLine().trim();
            if (line == 0) {
                line = 1;
            } else {
                String[] input = in.split("  | ");
                int[] inputRow = new int[input.length];
                for (int i = 0; i < input.length; i++) {
                    inputRow[i] = Integer.parseInt(input[i]);
                }
                currBoard.setRow(inputRow, line);
                line++;
                if (line == 6) {
                    boards.add(currBoard);
                    currBoard = new BingoBoard();
                    line = 0;
                }
            }
        }

        int n = 0;
        int maxBingo = 0;
        int maxIndex = 0;
        for (BingoBoard i : boards) {
            if (maxBingo == 0) {
                maxBingo = i.getBingo(numbers);
                maxIndex = n;
            } else {
                int value = i.getBingo(numbers);
                if (value > maxBingo) {
                    maxBingo = value;
                    maxIndex = n;
                }
            }
            n++;
        }
        BingoBoard winner = boards.get(maxIndex);
        int sum = winner.getUnmarkedSum(numbers, maxBingo);
        int bingoNum = numbers[maxBingo-1];
        System.out.println("sum = "+sum);
        System.out.println("bingoNum = "+bingoNum);
        System.out.println(sum*bingoNum);
    }
}
