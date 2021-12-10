import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Day10Puzzle1 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day10.txt");
        Scanner s = new Scanner(inputFile);
        int sum = 0;

        while (s.hasNextLine()) {
            int n = 0;
            Stack<Character> stack = new Stack();
            String input = s.nextLine();

            while (n < input.length()) {
                if (input.charAt(n) == '(' ||
                        input.charAt(n) == '[' ||
                        input.charAt(n) == '{' ||
                        input.charAt(n) == '<') {
                    stack.push(input.charAt(n));
                } else if (input.charAt(n) == ')' && stack.peek() == '(' ||
                            input.charAt(n) == ']' && stack.peek() == '[' ||
                            input.charAt(n) == '}' && stack.peek() == '{' ||
                            input.charAt(n) == '>' && stack.peek() == '<') {
                        stack.pop();
                } else if (input.charAt(n) == ')') {
                    sum += 3;
                    break;
                } else if (input.charAt(n) == ']') {
                    sum += 57;
                    break;
                } else if (input.charAt(n) == '}') {
                    sum += 1197;
                    break;
                } else if (input.charAt(n) == '>') {
                    sum += 25137;
                    break;
                }
                n++;
            }
        }

        System.out.println(sum);

    }
}
