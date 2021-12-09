import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day8Puzzle2 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day8.txt");
        Scanner s = new Scanner(inputFile);

        String[] digits;
        String[] output;
        int counter = 0;
        while (s.hasNextLine()) {
            String[] input = s.nextLine().split("\\|");
            digits = input[0].trim().split(" ");
            output = input[1].trim().split(" ");
            char[] segments = getSegments(digits);
            int value = getValues(segments, output);
            System.out.printf("%s,%s,%s,%s: %d%n", output[0],output[1],output[2],output[3],value);
            counter += value;
        }

        System.out.println("total: " + counter);
    }

    //Returns segment letters in order
    static char[] getSegments(String[] digits) {
        char[] segments = new char[7];
        String[] unknown = new String[6];
        // 1 needs 2, 7 needs 3, 4 needs 4, 235 need 5, 069 need 6, 8 needs 7
        char[] one = new char[2];
        char[] four = new char[4];
        char[] six = new char[6];
        char[] seven = new char[3];
        char[] eight = new char[7];
        int i = 0;
        for (String s : digits) {
            if (s.length() == 2) {
                one = s.toCharArray();
            } else if (s.length() == 3) {
                seven = s.toCharArray();
            } else if (s.length() == 4) {
                four = s.toCharArray();
            } else if (s.length() == 7) {
                eight = s.toCharArray();
            } else {
                unknown[i] = s;
                i++;
            }
        }

        //Finds the first segment
        for (char c : seven) {
            boolean check = false;
            for (char d : one) {
                if (c == d) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                segments[0] = c;
                break;
            }
        }

        //Finds the 7th segment by comparing 3/5 to 4
        seventh:
        for (String s : unknown) {
            if (s.length() == 5) {
                char[] uniques = getUniques(s.toCharArray(), four);
                if (uniques.length != 2) {
                    continue;
                }
                for (char c : uniques) {
                    if (c != segments[0]) {
                        segments[6] = c;
                        break seventh;
                    }
                }
            }
        }

        //Finds the 4th segment by comparing 3 to (1+known segments)
        char[] fourthArray = new char[4];
        fourthArray[0] = one[0];
        fourthArray[1] = one[1];
        fourthArray[2] = segments[0];
        fourthArray[3] = segments[6];
        for (String s : unknown) {
            if (s.length() == 5) {
                char[] uniques4 = getUniques(s.toCharArray(), fourthArray);
                if (uniques4.length != 1) {
                    continue;
                }
                segments[3] = uniques4[0];
            }
        }

        //Finds the 2nd segment by comparing 4 to (1+known segments)
        char[] secondArray = new char[3];
        secondArray[0] = one[0];
        secondArray[1] = one[1];
        secondArray[2] = segments[3];
        char[] uniques2 = getUniques(four, secondArray);
        segments[1] = uniques2[0];

        //Finds the 5th segment by comparing 8 to (4+known segments)
        char[] fifthArray = new char[6];
        fifthArray[0] = four[0];
        fifthArray[1] = four[1];
        fifthArray[2] = four[2];
        fifthArray[3] = four[3];
        fifthArray[4] = segments[0];
        fifthArray[5] = segments[6];
        char[] uniques5 = getUniques(eight, fifthArray);
        segments[4] = uniques5[0];

        //Finds the 3rd segment by finding 6 and comparing it to 8
        char[] customArray = new char[5];
        customArray[0] = segments[0];
        customArray[1] = segments[1];
        customArray[2] = segments[3];
        customArray[3] = segments[4];
        customArray[4] = segments[6];
        for (String s : unknown) {
            if (s.length() == 6) {
                char[] uniques = getUniques(s.toCharArray(), customArray);
                if (uniques.length != 1) {
                    continue;
                }
                six = s.toCharArray();
            }
        }
        char[] uniques3 = getUniques(eight, six);
        segments[2] = uniques3[0];

        //Find the 6th segment by checking which one is not yet listed
        eighth:
        for (char c : eight) {
            boolean check1 = false;
            for (char d : segments) {
                if (c == d) {
                    check1 = true;
                    break;
                }
            }
            if (!check1) {
                segments[5] = c;
                break;
            }
        }

        return segments;
    }

    static int getValues(char[] segments, String[] output) {
        String solution = "";
        for (String o : output) {
            if (o.length() == 2) {
                solution += "1";
            } else if (o.length() == 3) {
                solution += "7";
            } else if (o.length() == 4) {
                solution += "4";
            } else if (o.length() == 7) {
                solution += "8";
            } else {
                solution += getNumber(segments, o);
            }
        }
        return Integer.parseInt(solution);
    }

    static String getNumber(char[] segments, String number) {
        char[] num = number.toCharArray();
        if (number.length() == 5) { //235 need 5, 069 need 6
            for (char n : num) {
                if (n == segments[1]) {
                    return "5";
                }
            }
            for (char n : num) {
                if (n == segments[4]) {
                    return "2";
                }
            }
            return "3";
        } else {
            boolean zeroCheck = true;
            for (char n : num) {
                if (n == segments[3]) {
                    zeroCheck = false;
                }
            }
            if (zeroCheck) {
                return "0";
            }
            for (char n : num) {
                if (n == segments[2]) {
                    return "9";
                }
            }
            return "6";
        }
    }

    static char[] getUniques(char[] active, char[] passive) {
        char[] temp = new char[10];
        int i = 0;
        for (char c : active) {
            boolean check = false;
            for (char d : passive) {
                if (c == d) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                temp[i] = c;
                i++;
            }
        }
        char[] output = new char[i];
        for (int j = 0; j < i; j++) {
            output[j] = temp[j];
        }
        return output;
    }
}
