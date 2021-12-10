import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Day10Puzzle2 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day10.txt");
        Scanner s = new Scanner(inputFile);
        ArrayList<Long> scores = new ArrayList<>();

        while (s.hasNextLine()) {
            int n = 0;
            long sum = 0;
            Stack<Character> stack = new Stack();
            String input = s.nextLine();

            while (n < input.length()) {
                char currChar = input.charAt(n);
                char stackTop = Character.MIN_VALUE;
                if (!stack.empty()) {
                    stackTop = stack.peek();
                }
                if (currChar == '(' ||
                        currChar == '[' ||
                        currChar == '{' ||
                        currChar == '<') {
                    stack.push(currChar);
                } else if (currChar == ')' && stackTop == '(' ||
                        currChar == ']' && stackTop == '[' ||
                        currChar == '}' && stackTop == '{' ||
                        currChar == '>' && stackTop == '<') {
                    stack.pop();
                } else {
                    break;
                }
                n++;
            }

            if (n == input.length()) {
                while (!stack.empty()) {
                    char stackTop = stack.peek();
                    sum *= 5;
                    if (stackTop == '(') {
                        sum += 1;
                        stack.pop();
                    } else if (stackTop == '[') {
                        sum += 2;
                        stack.pop();
                    } else if (stackTop == '{') {
                        sum += 3;
                        stack.pop();
                    } else if (stackTop == '<') {
                        sum += 4;
                        stack.pop();
                    }
                }
                scores.add(sum);
            }

        }

        Collections.sort(scores);
        System.out.println("Scores:" + scores.size());
        for (long i : scores) {
            System.out.println(i);
        }
        System.out.println("Middle score:" + scores.get(scores.size()/2));

    }
}
