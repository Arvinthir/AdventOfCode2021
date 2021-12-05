import java.util.ArrayList;

public class BingoBoard {
    int[] row1 = new int[5];
    int[] row2 = new int[5];
    int[] row3 = new int[5];
    int[] row4 = new int[5];
    int[] row5 = new int[5];
    int[] collumn1 = new int[5];
    int[] collumn2 = new int[5];
    int[] collumn3 = new int[5];
    int[] collumn4 = new int[5];
    int[] collumn5 = new int[5];

    public void setRow(int[] row, int n) {
        switch (n) {
            case 1:
                row1 = row;
                collumn1[0] = row1[0];
                collumn2[0] = row1[1];
                collumn3[0] = row1[2];
                collumn4[0] = row1[3];
                collumn5[0] = row1[4];
                break;
            case 2:
                row2 = row;
                collumn1[1] = row2[0];
                collumn2[1] = row2[1];
                collumn3[1] = row2[2];
                collumn4[1] = row2[3];
                collumn5[1] = row2[4];
                break;
            case 3:
                row3 = row;
                collumn1[2] = row3[0];
                collumn2[2] = row3[1];
                collumn3[2] = row3[2];
                collumn4[2] = row3[3];
                collumn5[2] = row3[4];
                break;
            case 4:
                row4 = row;
                collumn1[3] = row4[0];
                collumn2[3] = row4[1];
                collumn3[3] = row4[2];
                collumn4[3] = row4[3];
                collumn5[3] = row4[4];
                break;
            case 5:
                row5 = row;
                collumn1[4] = row5[0];
                collumn2[4] = row5[1];
                collumn3[4] = row5[2];
                collumn4[4] = row5[3];
                collumn5[4] = row5[4];
                break;
            default:
                System.out.println("Invalid input in setRow.");
        }
    }

    public int getBingo(int[] numbers) {
        for (int i = 5; i <= numbers.length; i++) {
            if (checkArrays(numbers, i)) {
                return i;
            }
        }
        return 0;
    }

    boolean checkArrays(int[] numbers, int n) {
        for (int i = 0; i < n; i++) {
            if (countTiles(row1, numbers, n) == 5 ||
                    countTiles(row2, numbers, n) == 5 ||
                    countTiles(row3, numbers, n) == 5 ||
                    countTiles(row4, numbers, n) == 5 ||
                    countTiles(row5, numbers, n) == 5 ||
                    countTiles(collumn1, numbers, n) == 5 ||
                    countTiles(collumn2, numbers, n) == 5 ||
                    countTiles(collumn3, numbers, n) == 5 ||
                    countTiles(collumn4, numbers, n) == 5 ||
                    countTiles(collumn5, numbers, n) == 5
            ) {
                return true;
            }
        }
        return false;
    }

    int countTiles(int[] array, int[] numbers, int n) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < n; j++) {
                if (array[i] == numbers[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    int getUnmarkedSum(int[] numbers, int n) {
        int result = 0;
        for (int i = 0; i < 5; i++) {
            if (checkTile(row1, i, numbers, n)) {
                result += row1[i];
            }
            if (checkTile(row2, i, numbers, n)) {
                result += row2[i];
            }
            if (checkTile(row3, i, numbers, n)) {
                result += row3[i];
            }
            if (checkTile(row4, i, numbers, n)) {
                result += row4[i];
            }
            if (checkTile(row5, i, numbers, n)) {
                result += row5[i];
            }
        }
        return result;
    }

    boolean checkTile(int[] array, int i, int[] numbers, int n) {
        for (int j = 0; j < n; j++) {
            if (array[i] == numbers[j]) {
                return false;
            }
        }
        return true;
    }
}
